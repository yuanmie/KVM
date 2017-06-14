package instructions.references;

import instructions.base.Index16Instruction;
import instructions.base.Method_invoke_logic;
import rtda.JVMFrame;
import rtda.heap.*;
import tool.Tool;

public class InvokeStatic extends Index16Instruction{
    @Override
    public void execute(JVMFrame frame) {
        JVMConstantPool cp = frame.getMethod().klass.getCp();
        JVMMethodRef methodRef = cp.getContant(this.index).getMethodRef();
        JVMMethod method = methodRef.resolvedMethod();

        JVMClass klass = method.klass;
        if(!klass.InitStarted()){
            frame.revertNextPc();
            JVMClass.initClass(frame.getThread(), klass);
            return;
        }
        if(!method.IsStatic()){
            Tool.panic("java.lang.IncompatibleClassChangeError");
        }
        Method_invoke_logic.InvokeMethod(frame, method);
    }
}
