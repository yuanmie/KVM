package instructions.constants;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;

public class FCONST_2 extends NoOperandsInstruction {
    public void execute(JVMFrame frame){
        frame.getOperandStack().pushFloat(2.0f);
    }
}
