package instructions.conversions;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;
import rtda.OperandStack;

public class I2F extends NoOperandsInstruction{
    @Override
    public void execute(JVMFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int d = stack.popInt();
        float i = (float)d;
        stack.pushFloat(i);
    }
}
