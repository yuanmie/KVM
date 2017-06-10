package instructions.loads;

import instructions.base.ByteCodeReader;
import rtda.JVMFrame;

public class ALOAD_0 extends ALOAD{
    @Override
    public void fetchOperands(ByteCodeReader reader) {

    }
    @Override
    public void execute(JVMFrame frame) {
        super._aload(frame, 0);
    }
}
