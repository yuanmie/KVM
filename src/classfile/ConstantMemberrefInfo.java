package classfile;

public class ConstantMemberrefInfo extends ConstantInfo{


    ConstantPool cp;
    int classIndex;
    int nameAndTypeIndex;


    public ConstantMemberrefInfo(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader cr) {
        this.classIndex = Short.toUnsignedInt(cr.readUint16());
        this.nameAndTypeIndex = Short.toUnsignedInt(cr.readUint16());
    }

    public String ClassName(){
        return this.cp.getClassName(this.classIndex);
    }

    public String[] NameAndDescriptor(){
        return this.cp.getNameAndType(this.nameAndTypeIndex);
    }
}
