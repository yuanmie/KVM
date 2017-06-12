package instructions.control;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;
import rtda.JVMThread;

public class IRETURN extends NoOperandsInstruction{
    @Override
    public void execute(JVMFrame frame) {
        JVMThread thread = frame.getThread();
        JVMFrame currentFrame = thread.popFrame();
        JVMFrame invokerFrame = thread.currentFrame();
        int retVal = currentFrame.getOperandStack().popInt();
        invokerFrame.getOperandStack().pushInt(retVal);
    }
}
