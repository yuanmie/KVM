package instructions.stores;

import instructions.base.ByteCodeReader;
import rtda.JVMFrame;

public class FSTORE_0 extends FSTORE{
    @Override
    public void fetchOperands(ByteCodeReader reader) {

    }
    @Override
    public void execute(JVMFrame frame) {
        _fstore(frame, 0);
    }
}
