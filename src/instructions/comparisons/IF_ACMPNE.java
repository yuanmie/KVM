package instructions.comparisons;

import rtda.JVMFrame;

public class IF_ACMPNE extends IF_ACMP{

    @Override
    public void execute(JVMFrame frame) {
        if (!super._acmp(frame)){
            branch(frame, this.offset);
        }
    }
}
