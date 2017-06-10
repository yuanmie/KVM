package instructions.loads;

import instructions.base.ByteCodeReader;
import rtda.JVMFrame;

public class ILOAD_2 extends ILOAD{
    @Override
    public void execute(JVMFrame frame) {
        super._iload(frame, 2);
    }
    @Override
    public void fetchOperands(ByteCodeReader reader) {

    }
}
