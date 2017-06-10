package instructions.stores;

import instructions.base.Index8Instruction;
import rtda.JVMFrame;

public class DSTORE extends Index8Instruction{
    public void _dstore(JVMFrame frame, int index){
        double val = frame.getOperandStack().popDouble();
        frame.getLocalVars().setDouble(index, val);
    }

    @Override
    public void execute(JVMFrame frame) {
        _dstore(frame, this.index);
    }
}
