package classfile;

public class ConstantValueAttribute implements AttributeInfo {
    int constantValueIndex;

    @Override
    public void readInfo(ClassReader cr) {
        this.constantValueIndex = Short.toUnsignedInt(cr.readUint16());
    }

    public int ConstantValueIndex(){
        return this.constantValueIndex;
    }
}
