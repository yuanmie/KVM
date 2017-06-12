package instructions.base;

import rtda.JVMFrame;
import rtda.JVMThread;
import rtda.Slot;
import rtda.heap.JVMField;
import rtda.heap.JVMMethod;

public class Method_invoke_logic {
    public static void InvokeMethod(JVMFrame invokerFrame, JVMMethod method){
        JVMThread thread = invokerFrame.getThread();
        JVMFrame newFrame = thread.newJVMFrame(method);
        thread.pushFrame(newFrame);
        int argSlot = method.argSlotCount();
        if(argSlot > 0){
            for(int i = argSlot - 1; i >= 0; i--){
                Slot slot = invokerFrame.getOperandStack().popSlot();
                newFrame.getLocalVars().setSlot(i, slot);
            }
        }
    }
}
