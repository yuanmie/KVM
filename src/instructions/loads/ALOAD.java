package instructions.loads;

import instructions.base.Index8Instruction;
import rtda.JVMFrame;
import rtda.heap.JVMObject;

public class ALOAD extends Index8Instruction{

    public void _aload(JVMFrame frame, int index){
        JVMObject val = frame.getLocalVars().getRef(index);
        frame.getOperandStack().pushRef(val);
    }

    @Override
    public void execute(JVMFrame frame) {
        _aload(frame, this.index);
    }
}
