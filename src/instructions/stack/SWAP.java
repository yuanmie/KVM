package instructions.stack;

import instructions.base.Index8Instruction;
import rtda.JVMFrame;
import rtda.OperandStack;
import rtda.Slot;

public class SWAP extends Index8Instruction{
    @Override
    public void execute(JVMFrame frame) {
        OperandStack stack = frame.getOperandStack();
        Slot s1 = stack.popSlot();
        Slot s2 = stack.popSlot();
        stack.pushSlot(s1);
        stack.pushSlot(s2);
    }
}
