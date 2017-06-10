package instructions.constants;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;

public class ICONST_4 extends NoOperandsInstruction {
    public void execute(JVMFrame frame){
        frame.getOperandStack().pushInt(4);
    }
}
