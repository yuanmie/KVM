package instructions.loads;

import instructions.base.ByteCodeReader;
import instructions.base.Index8Instruction;
import rtda.JVMFrame;

public class LLOAD_0 extends LLOAD{


    @Override
    public void execute(JVMFrame frame) {
        _lload(frame, 0);
    }

    @Override
    public void fetchOperands(ByteCodeReader reader) {

    }
}
