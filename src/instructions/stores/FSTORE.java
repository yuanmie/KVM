package instructions.stores;

import instructions.base.Index8Instruction;
import rtda.JVMFrame;

public class FSTORE extends Index8Instruction{
    public void _fstore(JVMFrame frame, int index){
        float val = frame.getOperandStack().popFloat();
        frame.getLocalVars().setFloat(index, val);
    }

    @Override
    public void execute(JVMFrame frame) {
        _fstore(frame, this.index);
    }
}
