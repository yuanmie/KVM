package instructions.comparisons;

import instructions.base.BranchInstruction;
import rtda.JVMFrame;
import rtda.OperandStack;

public class IF_ICMPLT extends BranchInstruction{
    @Override
    public void execute(JVMFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int v2 = stack.popInt();
        int v1 = stack.popInt();
        if(v1 < v2){
            branch(frame, this.offset);
        }
    }
}
