package instructions.conversions;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;
import rtda.OperandStack;

public class F2L extends NoOperandsInstruction{
    @Override
    public void execute(JVMFrame frame) {
        OperandStack stack = frame.getOperandStack();
        float d = stack.popFloat();
        long i = (long)d;
        stack.pushLong(i);
    }
}
