package instructions.constants;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;

public class DCONST_1 extends NoOperandsInstruction {
    public void execute(JVMFrame frame){
        frame.getOperandStack().pushDouble(1.0);
    }
}
