package instructions.base;

import rtda.JVMFrame;


public interface Instruction {
    public void fetchOperands(ByteCodeReader reader);
    public void execute(JVMFrame frame);


}
