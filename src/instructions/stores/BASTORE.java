package instructions.stores;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;
import rtda.OperandStack;
import rtda.heap.JVMObject;
import tool.Tool;

public class BASTORE extends NoOperandsInstruction{
    @Override
    public void execute(JVMFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int val = stack.popInt();
        int index = stack.popInt();
        JVMObject arrRef = stack.popRef();
        checkNotNull(arrRef);
        byte[] ints = arrRef.Bytes();
        checkIndex(ints.length, index);
        ints[index] = (byte)val;
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
