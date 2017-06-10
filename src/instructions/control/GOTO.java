package instructions.control;

import instructions.base.BranchInstruction;
import rtda.JVMFrame;

public class GOTO extends BranchInstruction{
    @Override
    public void execute(JVMFrame frame) {
        branch(frame, this.offset);
    }
}
