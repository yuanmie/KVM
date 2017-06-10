package instructions.stores;

import instructions.base.ByteCodeReader;
import instructions.base.Index8Instruction;
import rtda.JVMFrame;

public class ISTORE_3 extends ISTORE{
    @Override
    public void fetchOperands(ByteCodeReader reader) {

    }
    @Override
    public void execute(JVMFrame frame) {
        super._istore(frame, 3);
    }
}
