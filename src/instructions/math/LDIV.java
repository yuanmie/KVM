package instructions.math;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;
import rtda.OperandStack;
import tool.Tool;

public class LDIV extends NoOperandsInstruction{
    @Override
    public void execute(JVMFrame frame) {
        OperandStack stack = frame.getOperandStack();
        long v2 = stack.popLong();
        long v1 = stack.popLong();
        if(v2 == 0){
            Tool.panic("java.lang.ArithmticException: / by zero");
        }

        long result = v1 / v2;
        stack.pushLong(result);
    }
}
