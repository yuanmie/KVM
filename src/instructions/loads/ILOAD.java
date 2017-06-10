package instructions.loads;

import instructions.base.Index8Instruction;
import rtda.JVMFrame;

public class ILOAD extends Index8Instruction{

    public void _iload(JVMFrame frame, int index){
        int val = frame.getLocalVars().getInt(index);
        frame.getOperandStack().pushInt(val);
    }

    @Override
    public void execute(JVMFrame frame) {
        _iload(frame, this.index);
    }
}
