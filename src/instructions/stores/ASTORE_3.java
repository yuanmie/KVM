package instructions.stores;

import instructions.base.ByteCodeReader;
import rtda.JVMFrame;

public class ASTORE_3 extends ASTORE{

    @Override
    public void fetchOperands(ByteCodeReader reader) {

    }
    @Override
    public void execute(JVMFrame frame) {
        _astore(frame, 3);
    }
}
