package classfile;

public class ConstantLongInfo extends ConstantInfo {
    long val;
    @Override
    public void readInfo(ClassReader cr) {
        this.val = cr.readUint64();
    }

    public long value() {
        return val;
    }
}
