package instructions.stores;

import instructions.base.ByteCodeReader;
import rtda.JVMFrame;

public class DSTORE_1 extends DSTORE{
    @Override
    public void fetchOperands(ByteCodeReader reader) {

    }
    @Override
    public void execute(JVMFrame frame) {
        _dstore(frame, 1);
    }
}
