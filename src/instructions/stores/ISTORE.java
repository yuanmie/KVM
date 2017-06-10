package instructions.stores;

import instructions.base.Index8Instruction;
import rtda.JVMFrame;

public class ISTORE extends Index8Instruction{
    public void _istore(JVMFrame frame, int index){
        int val = frame.getOperandStack().popInt();
        frame.getLocalVars().setInt(index, val);
    }

    @Override
    public void execute(JVMFrame frame) {
        _istore(frame, this.index);
    }
}
