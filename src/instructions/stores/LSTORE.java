package instructions.stores;

import instructions.base.Index8Instruction;
import rtda.JVMFrame;

public class LSTORE extends Index8Instruction {
    public void _lstore(JVMFrame frame, int index){
        long val = frame.getOperandStack().popLong();
        frame.getLocalVars().setLong(index, val);
    }

    @Override
    public void execute(JVMFrame frame) {
        _lstore(frame, this.index);
    }
}
