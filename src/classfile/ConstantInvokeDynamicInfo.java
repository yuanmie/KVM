package classfile;

public class ConstantInvokeDynamicInfo extends ConstantInfo {
    int bootstrapMethodAttrIndex;
    int nameAndTypeIndex;

    @Override
    public void readInfo(ClassReader cr) {
        this.bootstrapMethodAttrIndex = Short.toUnsignedInt(cr.readUint16());
        this.nameAndTypeIndex = Short.toUnsignedInt(cr.readUint16());
    }
}
