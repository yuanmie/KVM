package instructions.math;

import instructions.base.ByteCodeReader;
import instructions.base.Instruction;
import rtda.JVMFrame;
import rtda.LocalVars;

public class IINC implements Instruction{
    public int index;
    public int Const;
    @Override
    public void fetchOperands(ByteCodeReader reader) {
        this.index = reader.readUint8();
        this.Const = reader.readInt8();
    }

    @Override
    public void execute(JVMFrame frame) {
        LocalVars localVars = frame.getLocalVars();
        int val = localVars.getInt(this.index);
        val += this.Const;
        localVars.setInt(this.index, val);
    }
}
