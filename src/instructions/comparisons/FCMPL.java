package instructions.comparisons;

import rtda.JVMFrame;

public class FCMPL extends FCMP{
    @Override
    public void execute(JVMFrame frame) {
        super._fmcp(frame, false);
    }
}
