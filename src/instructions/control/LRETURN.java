package instructions.control;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;
import rtda.JVMThread;

public class LRETURN extends NoOperandsInstruction{
    @Override
    public void execute(JVMFrame frame) {
        JVMThread thread = frame.getThread();
        JVMFrame currentFrame = thread.popFrame();
        JVMFrame invokerFrame = thread.currentFrame();
        long retVal = currentFrame.getOperandStack().popLong();
        invokerFrame.getOperandStack().pushLong(retVal);
    }
}
