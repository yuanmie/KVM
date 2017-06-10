package instructions.base;

import rtda.JVMFrame;

public class Index8Instruction implements Instruction {
    public int index;


    @Override
    public void fetchOperands(ByteCodeReader reader) {
        this.index = reader.readUint8();
    }

    @Override
    public void execute(JVMFrame frame) {

    }
}
