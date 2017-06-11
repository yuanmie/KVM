package classfile;

import rtda.heap.JVMConstant;

public class ConstantFloatInfo extends ConstantInfo {
    float val;
    @Override
    public void readInfo(ClassReader cr) {
        this.val = Float.intBitsToFloat(cr.readUint32());
    }

    public float value() {
        return val;
    }
}
