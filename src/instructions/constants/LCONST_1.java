package instructions.constants;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;

public class LCONST_1 extends NoOperandsInstruction {
    public void execute(JVMFrame frame){
        frame.getOperandStack().pushLong(1);
    }
}
