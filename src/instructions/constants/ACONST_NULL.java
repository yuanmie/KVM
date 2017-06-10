package instructions.constants;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;

public class ACONST_NULL extends NoOperandsInstruction {
    public void execute(JVMFrame frame){
        frame.getOperandStack().pushRef(null);
    }
}
