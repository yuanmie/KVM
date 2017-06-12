package instructions.control;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;

public class RETURN extends NoOperandsInstruction{
    @Override
    public void execute(JVMFrame frame) {
        frame.getThread().popFrame();
    }
}
