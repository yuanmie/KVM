package instructions.comparisons;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;
import rtda.OperandStack;

public class DCMP extends NoOperandsInstruction{

    public void _dmcp(JVMFrame frame, boolean flag) {
        OperandStack stack = frame.getOperandStack();
        double v2 = stack.popDouble();
        double v1 = stack.popDouble();
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
