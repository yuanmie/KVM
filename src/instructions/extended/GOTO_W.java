package instructions.extended;

import instructions.base.BranchInstruction;
import instructions.base.ByteCodeReader;
import rtda.JVMFrame;

public class GOTO_W extends BranchInstruction{

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        this.offset = reader.readInt32();
    }

    @Override
    public void execute(JVMFrame frame) {
        branch(frame, this.offset);
    }
}
