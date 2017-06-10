package classfile;
/*
Java中没有无符号数，需要注意有符号和无符号的转换。,所有无符号的变量后面都要加_U
 */
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class ClassReader {
    private byte[] data;
    private int index;

    public ClassReader(byte[] data){
        this.data = data;
        this.index = 0;
    }

    public byte readUint8(){
        return data[index++];
    }

    public short readUint16(){
        ByteBuffer bb = getByteBuffer(2);
        Short r = bb.getShort(0);
        return r;
    }
    public int readUint32(){
        ByteBuffer bb = getByteBuffer(4);
        int r = bb.getInt(0);
        return r;
    }

    public long readUint64(){
        ByteBuffer bb = getByteBuffer(8);
        long r = bb.getLong(0);
        return r;
    }

    public short[] readUint16s(){
        /*
        convert signed to unsigned
         */
        short n = readUint16();
        int n_U = Short.toUnsignedInt(n);
        short[] r = new short[n];
        for(int i = 0; i < n_U; i++){
            r[i] = readUint16();
        }
        return r;
    }

    public byte[] readBytes(long size){
        byte[] r = Arrays.copyOfRange(data, index, (int)(index + size));
        index += size;
        return r;
    }

    private ByteBuffer getByteBuffer(int size){
        ByteBuffer bb = ByteBuffer.allocate(size);
        bb.clear();
        bb.order(ByteOrder.BIG_ENDIAN);
        while(size > 0){
            bb.put(data[index++]);
            --size;
        }
        return bb;
    }
}
