package instructions.control;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;
import rtda.JVMThread;

public class FRETURN extends NoOperandsInstruction{
    @Override
    public void execute(JVMFrame frame) {
        JVMThread thread = frame.getThread();
        JVMFrame currentFrame = thread.popFrame();
        JVMFrame invokerFrame = thread.currentFrame();
        float retVal = currentFrame.getOperandStack().popFloat();
        invokerFrame.getOperandStack().pushFloat(retVal);
    }
}
