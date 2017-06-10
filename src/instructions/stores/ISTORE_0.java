package instructions.stores;

import instructions.base.ByteCodeReader;
import instructions.base.Index8Instruction;
import rtda.JVMFrame;

public class ISTORE_0 extends ISTORE{
    @Override
    public void fetchOperands(ByteCodeReader reader) {

    }
    @Override
    public void execute(JVMFrame frame) {
        _istore(frame, 0);
    }
}
