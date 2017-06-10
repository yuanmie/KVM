package instructions.math;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;
import rtda.OperandStack;

public class LNEG extends NoOperandsInstruction{
    @Override
    public void execute(JVMFrame frame) {
        OperandStack stack = frame.getOperandStack();
        long v = stack.popLong();


        long r = -v;
        stack.pushLong(r);
    }
}
