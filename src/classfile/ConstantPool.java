package classfile;

import tool.Tool;

public class ConstantPool extends ConstantInfo{
    ConstantInfo[] cis;
    public ConstantPool(ConstantInfo[] cis){
        this.cis = cis;
    }

    public static ConstantPool readConstantPool(ClassReader reader){
        short cpCount = reader.readUint16();
        int cpCount_U = Short.toUnsignedInt(cpCount);
        ConstantPool cp = new ConstantPool(new ConstantInfo[cpCount_U]);

        for(int i = 1; i < cpCount_U; i++){
            ConstantInfo ci = cp.readConstantInfo(reader, cp);
            cp.setConstantInfo(i, ci);
            if(ci instanceof ConstantLongInfo || ci instanceof ConstantDoubleInfo){
                i++; //long or double skip occur two byte
            }
        }

        return cp;
    }

    public ConstantInfo getConstantInfo(int index){
        ConstantInfo ci = this.cis[index];
        if(ci != null){
            return ci;
        }
        Tool.panic(String.format("Invalid constant pool index: %d", index));
        return null;
    }

    public String[] getNameAndType(int index){
        ConstantNameAndTypeInfo nt = (ConstantNameAndTypeInfo)this.getConstantInfo(index);
        String name = this.getUtf8(nt.nameIndex);
        String type = this.getUtf8(nt.descriptorIndex);
        return new String[]{name, type};
    }

    public String getClassName(int index){
        ConstantClassInfo ci = (ConstantClassInfo) this.getConstantInfo(index);
        return this.getUtf8(ci.nameIndex);
    }

    public String getUtf8(int index){
        ConstantUtf8Info ci = (ConstantUtf8Info) this.getConstantInfo(index);
        return ci.toString();
    }
    @Override
    public void readInfo(ClassReader cr) {

    }

    private void setConstantInfo(int index, ConstantInfo ci){
        this.cis[index] = ci;
    }

    public int length() {
        return cis.length;
    }
}
