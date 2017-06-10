package instructions.comparisons;

import instructions.base.BranchInstruction;
import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;
import rtda.JVMObject;
import rtda.OperandStack;

public class IF_ACMP extends BranchInstruction{

    public boolean _acmp(JVMFrame frame) {
        OperandStack stack = frame.getOperandStack();
        JVMObject ref2 = stack.popRef();
        JVMObject ref1 = stack.popRef();
        return ref1 == ref2;
    }
}
