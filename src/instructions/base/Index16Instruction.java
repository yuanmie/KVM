package instructions.base;

import rtda.JVMFrame;

public class Index16Instruction implements Instruction{
    int index;

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        this.index = reader.readInt16();
    }

    @Override
    public void execute(JVMFrame frame) {

    }
}
