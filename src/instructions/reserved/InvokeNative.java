package instructions.reserved;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;
import rtda.heap.JVMMethod;
import rtda.heap.JVMMethodDescriptor;
import Native.Native;
import tool.Tool;

import java.util.function.Consumer;

public class InvokeNative extends NoOperandsInstruction{
    @Override
    public void execute(JVMFrame frame) {
        JVMMethod method = frame.getMethod();
        String className = method.klass.getName();
        String methodName = method.name;
        String md = method.descriptor;
        Consumer<JVMFrame> ci = Native.findNativeMethod(className,
                methodName, md);
        if(ci == null){
            String methodInfo = className + "." + methodName
                    + md;
            Tool.panic("java.lang.UnsatisfiedLinkError: " + methodInfo);
        }
        ci.accept(frame);
    }
}
