package instructions.math;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;
import rtda.OperandStack;

public class ISHR extends NoOperandsInstruction{
    public void execute(JVMFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int v2 = stack.popInt();
        int v1 = stack.popInt();

        int result = v1 >> v2;
        stack.pushInt(result);
    }
}
