package instructions.math;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;
import rtda.OperandStack;

public class INEG extends NoOperandsInstruction{
    @Override
    public void execute(JVMFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int v1 = stack.popInt();

        int r = -v1;
        stack.pushInt(r);
    }
}
