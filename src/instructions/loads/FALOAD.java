package instructions.loads;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;
import rtda.OperandStack;
import rtda.heap.JVMObject;
import tool.Tool;

public class FALOAD extends NoOperandsInstruction{
    @Override
    public void execute(JVMFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        JVMObject arrRef = stack.popRef();
        checkNotNull(arrRef);
        float[] ref = arrRef.Floats();
        checkIndex(ref.length, index);
        stack.pushFloat(ref[index]);
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
