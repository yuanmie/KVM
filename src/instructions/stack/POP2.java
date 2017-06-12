package instructions.stack;

import instructions.base.Index8Instruction;
import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;

public class POP2 extends NoOperandsInstruction {
    @Override
    public void execute(JVMFrame frame) {
        frame.getOperandStack().popSlot();
        frame.getOperandStack().popSlot();
    }
}
