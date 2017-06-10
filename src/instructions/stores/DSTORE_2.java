package instructions.stores;

import instructions.base.ByteCodeReader;
import rtda.JVMFrame;

public class DSTORE_2 extends DSTORE{
    @Override
    public void fetchOperands(ByteCodeReader reader) {

    }
    @Override
    public void execute(JVMFrame frame) {
        _dstore(frame, 2);
    }
}
