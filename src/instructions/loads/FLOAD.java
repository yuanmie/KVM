package instructions.loads;

import instructions.base.Index8Instruction;
import rtda.JVMFrame;

public class FLOAD extends Index8Instruction{

    public void _fload(JVMFrame frame, int index){
        float val = frame.getLocalVars().getFloat(index);
        frame.getOperandStack().pushFloat(val);
    }

    @Override
    public void execute(JVMFrame frame) {
        _fload(frame, this.index);
    }
}
