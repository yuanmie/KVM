package instructions.references;

import instructions.base.Index16Instruction;
import rtda.JVMFrame;
import rtda.heap.JVMClass;
import rtda.heap.JVMClassRef;
import rtda.heap.JVMConstantPool;
import rtda.heap.JVMObject;
import tool.Tool;

public class JVMNEW extends Index16Instruction{
    @Override
    public void execute(JVMFrame frame) {
        JVMConstantPool cp = frame.getMethod().klass.getCp();
        JVMClassRef classRef = cp.getContant(this.index).getClassRef();
        JVMClass klass = classRef.resolvedClass();
        if(klass.IsInterface() || klass.IsAbstract()){
            Tool.panic("java.lang.InstantiationError");
        }
        JVMObject ref = klass.newObject();
        frame.getOperandStack().pushRef(ref);
    }
}
