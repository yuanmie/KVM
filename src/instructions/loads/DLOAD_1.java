package instructions.loads;

import instructions.base.ByteCodeReader;
import rtda.JVMFrame;

public class DLOAD_1 extends DLOAD{
    @Override
    public void fetchOperands(ByteCodeReader reader) {

    }
    @Override
    public void execute(JVMFrame frame) {
        _dload(frame, 1);
    }
}
