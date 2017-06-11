package instructions.references;

import instructions.base.Index16Instruction;
import rtda.JVMFrame;

public class LDCW extends Index16Instruction{
    @Override
    public void execute(JVMFrame frame) {
        LDC._ldc(frame, this.index);
    }
}
