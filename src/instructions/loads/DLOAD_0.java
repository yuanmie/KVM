package instructions.loads;

import instructions.base.ByteCodeReader;
import instructions.base.Index8Instruction;
import rtda.JVMFrame;

public class DLOAD_0 extends DLOAD{
    @Override
    public void fetchOperands(ByteCodeReader reader) {

    }
    @Override
    public void execute(JVMFrame frame) {
        _dload(frame, 0);
    }
}
