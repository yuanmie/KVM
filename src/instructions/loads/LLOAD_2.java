package instructions.loads;

import instructions.base.ByteCodeReader;
import rtda.JVMFrame;

public class LLOAD_2 extends LLOAD{


    @Override
    public void execute(JVMFrame frame) {
        _lload(frame, 2);
    }
    @Override
    public void fetchOperands(ByteCodeReader reader) {

    }
}
