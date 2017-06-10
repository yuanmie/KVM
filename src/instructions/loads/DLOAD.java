package instructions.loads;

import instructions.base.Index8Instruction;
import rtda.JVMFrame;

public class DLOAD extends Index8Instruction{

    public void _dload(JVMFrame frame, int index){
        double val = frame.getLocalVars().getDouble(index);
        frame.getOperandStack().pushDouble(val);
    }

    @Override
    public void execute(JVMFrame frame) {
        _dload(frame, this.index);
    }
}
