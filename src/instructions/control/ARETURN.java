package instructions.control;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;
import rtda.JVMThread;
import rtda.heap.JVMObject;

public class ARETURN extends NoOperandsInstruction{
    @Override
    public void execute(JVMFrame frame) {
        JVMThread thread = frame.getThread();
        JVMFrame currentFrame = thread.popFrame();
        JVMFrame invokerFrame = thread.currentFrame();
        JVMObject retVal = currentFrame.getOperandStack().popRef();
        invokerFrame.getOperandStack().pushRef(retVal);
    }
}
