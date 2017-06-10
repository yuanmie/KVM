package instructions.constants;

import instructions.base.ByteCodeReader;
import instructions.base.Instruction;
import rtda.JVMFrame;

public class SIPUSH implements Instruction{
    int val;
    @Override
    public void fetchOperands(ByteCodeReader reader) {
        this.val = reader.readInt16();
    }

    @Override
    public void execute(JVMFrame frame) {
        frame.getOperandStack().pushInt(this.val);
    }
}
