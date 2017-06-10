package classfile;

public class ConstantNameAndTypeInfo extends ConstantInfo {
    public int nameIndex;
    public int descriptorIndex;

    @Override
    public void readInfo(ClassReader cr) {
        this.nameIndex = Short.toUnsignedInt(cr.readUint16());
        this.descriptorIndex = Short.toUnsignedInt(cr.readUint16());
    }
}
