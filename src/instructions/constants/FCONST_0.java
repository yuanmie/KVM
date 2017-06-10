package instructions.constants;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;

public class FCONST_0 extends NoOperandsInstruction {
    public void execute(JVMFrame frame){
        frame.getOperandStack().pushFloat(0.0f);
    }
}
