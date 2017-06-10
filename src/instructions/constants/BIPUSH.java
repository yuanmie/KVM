package instructions.constants;

import instructions.base.ByteCodeReader;
import instructions.base.Instruction;
import rtda.JVMFrame;

public class BIPUSH implements Instruction{
    int val;
    @Override
    public void fetchOperands(ByteCodeReader reader) {
        this.val = reader.readInt8();
    }

    @Override
    public void execute(JVMFrame frame) {
        frame.getOperandStack().pushInt(this.val);
    }
}
