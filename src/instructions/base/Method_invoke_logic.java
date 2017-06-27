package instructions.base;

import rtda.JVMFrame;
import rtda.JVMThread;
import rtda.Slot;
import rtda.heap.JVMField;
import rtda.heap.JVMMethod;
import tool.Tool;

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

        //hack
//        if(method.IsNative()){
//            if(method.name.equals("registerNatives")){
//                thread.popFrame();
//            }else{
//                Tool.panic(String.format("native method: %s.%s%s\n", method.klass.getName(),
//                        method.name, method.descriptor));
//            }
//        }
    }
}
