package instructions.extended;

import instructions.base.BranchInstruction;
import instructions.base.ByteCodeReader;
import rtda.JVMFrame;
import rtda.JVMObject;

public class IFNULL extends BranchInstruction{


    @Override
    public void execute(JVMFrame frame) {
        JVMObject ref = frame.getOperandStack().popRef();
        if(ref == null){
            branch(frame, this.offset);
        }
    }
}
