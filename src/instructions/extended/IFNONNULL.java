package instructions.extended;

import instructions.base.BranchInstruction;
import rtda.JVMFrame;
import rtda.JVMObject;

public class IFNONNULL extends BranchInstruction{


    @Override
    public void execute(JVMFrame frame) {
        JVMObject ref = frame.getOperandStack().popRef();
        if(ref != null){
            branch(frame, this.offset);
        }
    }
}
