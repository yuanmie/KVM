package instructions.stores;

import instructions.base.ByteCodeReader;
import rtda.JVMFrame;

public class ASTORE_1 extends ASTORE{

    @Override
    public void fetchOperands(ByteCodeReader reader) {

    }
    @Override
    public void execute(JVMFrame frame) {
        _astore(frame, 1);
    }
}
