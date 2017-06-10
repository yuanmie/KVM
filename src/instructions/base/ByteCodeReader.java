package instructions.base;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteCodeReader {
    byte[] code;
    int pc;

    public void reset(byte[] code, int pc){
        this.code = code;
        this.pc = pc;
    }

    public int readUint8() {
        byte b = this.code[this.pc];
        ++this.pc;
        return Byte.toUnsignedInt(b);
    }

    public int readInt8(){
        byte b = this.code[this.pc];
        ++this.pc;
        return b;
    }

    public int readInt16() {
        ByteBuffer bb = getByteBuffer(2);
        return bb.getShort(0);
    }

    public int readUint16() {
        ByteBuffer bb = getByteBuffer(2);
        return Short.toUnsignedInt(bb.getShort(0));
    }

    public int readInt32(){
        ByteBuffer bb = getByteBuffer(4);
        return bb.getInt(0);
    }

    public long readUInt32(){
        ByteBuffer bb = getByteBuffer(4);
        return Integer.toUnsignedLong(bb.getInt(0));
    }

    private ByteBuffer getByteBuffer(int size){
        ByteBuffer bb = ByteBuffer.allocate(size);
        bb.clear();
        bb.order(ByteOrder.BIG_ENDIAN);
        while(size > 0){
            bb.put(code[this.pc++]);
            --size;
        }
        return bb;
    }

    public void skipPadding() {
        while(this.pc % 4 != 0){
            this.readUint8();
        }
    }

    public int[] readInt32s(int jumpOffsetsCount) {
        int[] ints = new int[jumpOffsetsCount];
        for(int i = 0; i < jumpOffsetsCount; i++){
            ints[i] = this.readInt32();
        }
        return ints;
    }

    public byte[] getCode() {
        return code;
    }

    public void setCode(byte[] code) {
        this.code = code;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }
}
