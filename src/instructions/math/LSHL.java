package instructions.math;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;
import rtda.OperandStack;
import tool.Tool;

public class LSHL extends NoOperandsInstruction{
    @Override
    public void execute(JVMFrame frame) {
        OperandStack stack = frame.getOperandStack();
        long v2 = stack.popInt();
        long v1 = stack.popLong();

        long result = v1 << v2;
        stack.pushLong(result);
    }
}
