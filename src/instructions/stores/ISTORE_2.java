package instructions.stores;

import instructions.base.ByteCodeReader;
import rtda.JVMFrame;

public class ISTORE_2 extends ISTORE{
    @Override
    public void fetchOperands(ByteCodeReader reader) {

    }
    @Override
    public void execute(JVMFrame frame) {
        super._istore(frame, 2);
    }
}
