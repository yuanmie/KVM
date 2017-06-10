package instructions.stores;

import instructions.base.ByteCodeReader;
import rtda.JVMFrame;

public class LSTORE_0 extends LSTORE{
    @Override
    public void fetchOperands(ByteCodeReader reader) {

    }
    @Override
    public void execute(JVMFrame frame) {
        super._lstore(frame, 0);
    }
}
