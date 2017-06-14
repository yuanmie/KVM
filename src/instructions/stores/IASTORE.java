package instructions.stores;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;
import rtda.OperandStack;
import rtda.heap.JVMObject;
import tool.Tool;

public class IASTORE extends NoOperandsInstruction{
    @Override
    public void execute(JVMFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int val = stack.popInt();
        int index = stack.popInt();
        JVMObject arrRef = stack.popRef();
        checkNotNull(arrRef);
        int[] ints = arrRef.Ints();
        checkIndex(ints.length, index);
        ints[index] = val;
    }

    private void checkIndex(int length, int index) {
        if(index < 0 || index > length){
            Tool.panic("ArrayIndexOutOfBoundsException");
        }
    }

    private void checkNotNull(JVMObject arrRef) {
        if(arrRef == null){
            Tool.panic("java.lang.NullPointerException");
        }
    }
}
