package classfile;

public class ConstantMethodHandleInfo extends ConstantInfo {

    short referenceKind;
    int referenceIndex;
    @Override
    public void readInfo(ClassReader cr) {
        this.referenceKind = (short)Byte.toUnsignedInt(cr.readUint8());
        this.referenceIndex = Short.toUnsignedInt(cr.readUint16());
    }
}
