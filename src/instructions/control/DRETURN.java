package instructions.control;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;
import rtda.JVMThread;

public class DRETURN extends NoOperandsInstruction{
    @Override
    public void execute(JVMFrame frame) {
        JVMThread thread = frame.getThread();
        JVMFrame currentFrame = thread.popFrame();
        JVMFrame invokerFrame = thread.currentFrame();
        double retVal = currentFrame.getOperandStack().popDouble();
        invokerFrame.getOperandStack().pushDouble(retVal);
    }
}
