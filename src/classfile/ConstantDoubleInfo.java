package classfile;

public class ConstantDoubleInfo extends ConstantInfo {
    double val;
    @Override
    public void readInfo(ClassReader cr) {
        this.val = Double.longBitsToDouble(cr.readUint64());
    }

    public double value() {
        return val;
    }
}
