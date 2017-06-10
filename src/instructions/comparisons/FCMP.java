package instructions.comparisons;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;
import rtda.OperandStack;

public class FCMP extends NoOperandsInstruction{

    public void _fmcp(JVMFrame frame, boolean flag) {
        OperandStack stack = frame.getOperandStack();
        float v2 = stack.popFloat();
        float v1 = stack.popFloat();
        if (v1 > v2){
            stack.pushInt(1);
        }else if(v1 == v2){
            stack.pushInt(0);
        }else if(v1 < v2){
            stack.pushInt(-1);
        }else if(flag){
            stack.pushInt(1);
        }else{
            stack.pushInt(-1);
        }
    }
}
