package instructions.stores;

import instructions.base.Index8Instruction;
import rtda.JVMFrame;
import rtda.JVMObject;

public class ASTORE extends Index8Instruction{
    public void _astore(JVMFrame frame, int index){
        JVMObject val = frame.getOperandStack().popRef();
        frame.getLocalVars().setRef(index, val);
    }

    @Override
    public void execute(JVMFrame frame) {
        _astore(frame, this.index);
    }
}
