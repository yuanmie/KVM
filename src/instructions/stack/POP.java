package instructions.stack;

import instructions.base.Index8Instruction;
import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;

public class POP extends NoOperandsInstruction {
    @Override
    public void execute(JVMFrame frame) {
        frame.getOperandStack().popSlot();
    }
}
