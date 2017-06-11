package instructions.references;

import instructions.base.Index16Instruction;
import rtda.JVMFrame;

public class INVOKE_SPECIAL extends Index16Instruction {
    @Override
    public void execute(JVMFrame frame) {
        frame.getOperandStack().popRef();
    }
}
