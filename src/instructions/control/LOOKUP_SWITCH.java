package instructions.control;

import instructions.base.BranchInstruction;
import instructions.base.ByteCodeReader;
import rtda.JVMFrame;

public class LOOKUP_SWITCH extends BranchInstruction{
    int defaultOffset;
    int npairs;
    int[] matchOffsets;

    @Override
    public void execute(JVMFrame frame) {
        int key = frame.getOperandStack().popInt();
        for(int i = 0; i < this.npairs * 2; i += 2){
            if(this.matchOffsets[i] == key){
                offset = this.matchOffsets[i+1];
                super.branch(frame, offset);
                return;
            }
        }
        super.branch(frame, this.defaultOffset);
    }

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        reader.skipPadding();
        this.defaultOffset = reader.readInt32();
        this.npairs = reader.readInt32();
        this.matchOffsets = reader.readInt32s(this.npairs * 2);
    }

}
