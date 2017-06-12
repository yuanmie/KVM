package instructions.references;

import instructions.base.Index16Instruction;
import instructions.base.Method_invoke_logic;
import rtda.JVMFrame;
import rtda.heap.JVMConstantPool;
import rtda.heap.JVMMemberRef;
import rtda.heap.JVMMethod;
import rtda.heap.JVMMethodRef;
import tool.Tool;

public class InvokeStatic extends Index16Instruction{
    @Override
    public void execute(JVMFrame frame) {
        JVMConstantPool cp = frame.getMethod().klass.getCp();
        JVMMethodRef methodRef = cp.getContant(this.index).getMethodRef();
        JVMMethod method = methodRef.resolvedMethod();
        if(!method.IsStatic()){
            Tool.panic("java.lang.IncompatibleClassChangeError");
        }
        Method_invoke_logic.InvokeMethod(frame, method);
    }
}
