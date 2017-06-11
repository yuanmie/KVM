package instructions.references;

import instructions.base.Index16Instruction;
import rtda.JVMFrame;
import rtda.OperandStack;
import rtda.heap.JVMClass;
import rtda.heap.JVMClassRef;
import rtda.heap.JVMConstantPool;
import rtda.heap.JVMObject;

public class JVMInstanceof extends Index16Instruction{
    @Override
    public void execute(JVMFrame frame) {
        OperandStack stack = frame.getOperandStack();
        JVMObject ref = stack.popRef();
        if(ref == null){
            stack.pushInt(0);
            return;
        }

        JVMConstantPool cp = frame.getMethod().klass.getCp();
        JVMClassRef classRef = cp.getContant(this.index).getClassRef();
        JVMClass klass = classRef.resolvedClass();
        if(ref.isInstanceOf(klass)){
            stack.pushInt(1);
        }else{
            stack.pushInt(0);
        }
    }
}
