package instructions.comparisons;

import instructions.base.BranchInstruction;
import rtda.JVMFrame;
import rtda.OperandStack;

public class IF_GT extends BranchInstruction{
    @Override
    public void execute(JVMFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int v = stack.popInt();

        if(v > 0){
            branch(frame, this.offset);
        }
    }
}
