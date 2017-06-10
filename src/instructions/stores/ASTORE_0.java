package instructions.stores;

import instructions.base.ByteCodeReader;
import instructions.base.Index8Instruction;
import rtda.JVMFrame;
import rtda.JVMObject;

public class ASTORE_0 extends ASTORE{

    @Override
    public void fetchOperands(ByteCodeReader reader) {

    }
    @Override
    public void execute(JVMFrame frame) {
        _astore(frame, 0);
    }
}
