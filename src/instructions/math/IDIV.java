package instructions.math;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;
import rtda.OperandStack;
import tool.Tool;

public class IDIV extends NoOperandsInstruction{
    @Override
    public void execute(JVMFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int v2 = stack.popInt();
        int v1 = stack.popInt();
        if(v2 == 0){
            Tool.panic("java.lang.ArithmticException: / by zero");
        }

        int result = v1 / v2;
        stack.pushInt(result);
    }
}
