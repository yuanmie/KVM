package instructions.loads;

import instructions.base.Index8Instruction;
import rtda.JVMFrame;

public class LLOAD extends Index8Instruction{

    public void _lload(JVMFrame frame, int index){
        long val = frame.getLocalVars().getLong(index);
        frame.getOperandStack().pushLong(val);
    }

    @Override
    public void execute(JVMFrame frame) {
        _lload(frame, this.index);
    }
}
