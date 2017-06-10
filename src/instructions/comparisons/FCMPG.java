package instructions.comparisons;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;
import rtda.OperandStack;

public class FCMPG extends FCMP{
    @Override
    public void execute(JVMFrame frame) {
        super._fmcp(frame, true);
    }
}
