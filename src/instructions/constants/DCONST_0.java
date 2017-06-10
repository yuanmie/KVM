package instructions.constants;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;

public class DCONST_0 extends NoOperandsInstruction {
    public void execute(JVMFrame frame){
        frame.getOperandStack().pushDouble(0.0);
    }
}
