package classfile;

public class ConstantStringInfo extends ConstantInfo {
    ConstantPool cp;
    int stringIndex;

    public ConstantStringInfo(ConstantPool cp) {
        super();
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader cr) {
        this.stringIndex = Short.toUnsignedInt(cr.readUint16());
    }

    public String string(){
        return this.cp.getUtf8(this.stringIndex);
    }

}
