package classfile;

public class ConstantClassInfo extends ConstantInfo {
    public int nameIndex;
    ConstantPool cp;
    public ConstantClassInfo(ConstantPool cp) {
        super();
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader cr) {
        this.nameIndex = Short.toUnsignedInt(cr.readUint16());
    }

    public String Name(){
        return this.cp.getUtf8(this.nameIndex);
    }
}
