package classfile;

public class ConstantIntegerInfo extends ConstantInfo {
    int val;
    public ConstantIntegerInfo() {
    }

    @Override
    public void readInfo(ClassReader cr) {
        this.val = cr.readUint32();
    }

    public int value() {
        return val;
    }
}
