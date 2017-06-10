package instructions.stack;

import instructions.base.Index8Instruction;
import rtda.JVMFrame;

public class POP extends Index8Instruction{
    @Override
    public void execute(JVMFrame frame) {
        frame.getOperandStack().popSlot();
    }
}
