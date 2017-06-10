package instructions.conversions;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;
import rtda.OperandStack;

public class I2C extends NoOperandsInstruction{
    @Override
    public void execute(JVMFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int d = stack.popInt();
        char i = (char)d;
        stack.pushInt(i);
    }
}
