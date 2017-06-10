package classfile;

public class ConstantMethodTypeInfo extends ConstantInfo {
    int descriptorIndex;

    @Override
    public void readInfo(ClassReader cr) {
        this.descriptorIndex = Short.toUnsignedInt(cr.readUint16());
    }
}
