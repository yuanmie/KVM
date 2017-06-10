package instructions.stack;

import instructions.base.Index8Instruction;
import rtda.JVMFrame;
import rtda.OperandStack;
import rtda.Slot;

public class DUP extends Index8Instruction{
    @Override
    public void execute(JVMFrame frame) {
        OperandStack stack = frame.getOperandStack();
        Slot s = stack.popSlot();
        stack.pushSlot(s);
        stack.pushSlot(s);
    }
}
