package instructions.references;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;
import rtda.OperandStack;
import rtda.heap.JVMClassRef;
import rtda.heap.JVMObject;
import tool.Tool;

public class Array_length extends NoOperandsInstruction{
    @Override
    public void execute(JVMFrame frame) {
        OperandStack stack = frame.getOperandStack();
        JVMObject arrRef = stack.popRef();
        if(arrRef == null){
            Tool.panic("java.lang.NullPointerException");
        }
        int arrLen = arrRef.arrayLength();
        stack.pushInt(arrLen);
    }
}
