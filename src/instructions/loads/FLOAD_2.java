package instructions.loads;

import instructions.base.ByteCodeReader;
import rtda.JVMFrame;

public class FLOAD_2 extends FLOAD{
    @Override
    public void fetchOperands(ByteCodeReader reader) {

    }
    @Override
    public void execute(JVMFrame frame) {
        super._fload(frame, 2);
    }
}
