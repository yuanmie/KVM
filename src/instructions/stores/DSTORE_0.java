package instructions.stores;

import instructions.base.ByteCodeReader;
import instructions.base.Index8Instruction;
import rtda.JVMFrame;

public class DSTORE_0 extends DSTORE{
    @Override
    public void fetchOperands(ByteCodeReader reader) {

    }
    @Override
    public void execute(JVMFrame frame) {
        _dstore(frame, 0);
    }
}
