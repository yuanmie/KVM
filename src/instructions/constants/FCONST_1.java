package instructions.constants;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;

public class FCONST_1 extends NoOperandsInstruction {
    public void execute(JVMFrame frame){
        frame.getOperandStack().pushFloat(1.0f);
    }
}
