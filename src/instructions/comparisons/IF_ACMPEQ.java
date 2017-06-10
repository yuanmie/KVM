package instructions.comparisons;

import instructions.base.BranchInstruction;
import rtda.JVMFrame;
import rtda.JVMObject;
import rtda.OperandStack;

public class IF_ACMPEQ extends IF_ACMP{

    @Override
    public void execute(JVMFrame frame) {
        if (super._acmp(frame)){
            branch(frame, this.offset);
        }
    }
}
