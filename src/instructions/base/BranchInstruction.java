package instructions.base;

import rtda.JVMFrame;

public class BranchInstruction implements Instruction{
    public int offset;

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        this.offset = reader.readInt16();
    }

    @Override
    public void execute(JVMFrame frame) {

    }

    public void branch(JVMFrame frame, int offset){
        int pc = frame.getThread().getPc();
        int nextPc = pc + offset;
        frame.setNextPc(nextPc);
    }
}
