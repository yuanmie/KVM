package instructions.control;

import instructions.base.BranchInstruction;
import instructions.base.ByteCodeReader;
import rtda.JVMFrame;

public class TABLE_SWITCH extends BranchInstruction{
    int defaultOffset;
    int low;
    int high;
    int[] jumpOffsets;

    @Override
    public void execute(JVMFrame frame) {
        int index = frame.getOperandStack().popInt();
        int offset;
        if(index >= this.low && index <= this.high){
            offset = this.jumpOffsets[index - this.low];
        }else{
            offset = this.defaultOffset;
        }
        super.branch(frame, offset);
    }

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        reader.skipPadding();
        this.low = reader.readInt32();
        this.high = reader.readInt32();
        int jumpOffsetsCount = this.high - this.low + 1;
        this.jumpOffsets = reader.readInt32s(jumpOffsetsCount);
    }

}
