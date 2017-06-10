package instructions.extended;


import instructions.base.Index8Instruction;
import instructions.base.Instruction;
import instructions.base.ByteCodeReader;
import instructions.loads.*;
import instructions.math.IINC;
import instructions.stores.*;
import rtda.JVMFrame;
import tool.Tool;

public class WIDE implements Instruction{
    private Instruction modifiedInstruction;


    @Override
    public void fetchOperands(ByteCodeReader reader) {
        int opcode = reader.readUint8();
        Index8Instruction inst;
        switch(opcode) {
            case 0x15:
                inst = new ILOAD();
                inst.index = reader.readUint16();
                this.modifiedInstruction = inst;
                break;
            case 0x16:
                inst = new LLOAD();
                inst.index = (reader.readUint16());
                this.modifiedInstruction = inst;
            case 0x17:
                inst = new FLOAD();
                inst.index = (reader.readUint16());
                this.modifiedInstruction = inst;
            case 0x18:
                inst = new DLOAD();
                inst.index = (reader.readUint16());
                this.modifiedInstruction = inst;
            case 0x19:
                inst = new ALOAD();
                inst.index = (reader.readUint16());
                this.modifiedInstruction = inst;
            case 0x36:
                inst = new ISTORE();
                inst.index = (reader.readUint16());
                this.modifiedInstruction = inst;
            case 0x37:
                inst = new LSTORE();
                inst.index = (reader.readUint16());
                this.modifiedInstruction = inst;
            case 0x38:
                inst = new FSTORE();
                inst.index = (reader.readUint16());
                this.modifiedInstruction = inst;
            case 0x39:
                inst = new DSTORE();
                inst.index = (reader.readUint16());
                this.modifiedInstruction = inst;
            case 0x3a:
                inst = new ASTORE();
                inst.index = (reader.readUint16());
                this.modifiedInstruction = inst;
            case 0x84:
                IINC innc = new IINC();
                innc.index = reader.readUint16();
                innc.Const = reader.readInt16();
                this.modifiedInstruction = innc;
            case 0xa9: // ret;
                Tool.panic("Unsupported opcode: 0xa9!");
        }
    }

    @Override
    public void execute(JVMFrame frame) {
        this.modifiedInstruction.execute(frame);
    }
}
