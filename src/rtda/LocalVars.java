package rtda;

import java.nio.ByteBuffer;

public class LocalVars {
    Slot[] localVals;
    public LocalVars(long maxLocals) {
        if(maxLocals > 0){
            this.localVals = new Slot[(int)maxLocals];
            for(int i = 0; i < maxLocals; i++){
                localVals[i] = new Slot();
            }
        }
    }

    public Slot[] getLocalVals() {
        return localVals;
    }

    public void setLocalVals(Slot[] localVals) {
        this.localVals = localVals;
    }

    public void setInt(int index, int val){
        this.localVals[index].setNum(val);
    }

    public int getInt(int index){
        return this.localVals[index].getNum();
    }

    /*
        store bit of float ,not the value of float
     */
    public void setFloat(int index, float val){
        setInt(index, Float.floatToIntBits(val));
    }

    public float getFloat(int index){
        return Float.intBitsToFloat(getInt(index));
    }


    public void setLong(int index, long val){
        /*
            first store low 32 bit
            second store high 32 bit
         */
        ByteBuffer buffer = ByteBuffer.allocate(8).putLong(val);
        this.localVals[index].setNum((int)val);
        this.localVals[index+1].setNum((int)(val >> 32));
    }

    public long getLong(int index){
        int low = this.localVals[index].getNum();
        int high = this.localVals[index+1].getNum();
        long l = (long)high << 32 | (low & 0xFFFFFFFFL);
        return l;
    }

    public void setDouble(int index, Double val){
        Long l = Double.doubleToLongBits(val);
        setLong(index, l);
    }

    public Double getDouble(int index){
        Long l = getLong(index);
        return Double.longBitsToDouble(l);
    }

    public void setRef(int index, JVMObject ref){
        this.localVals[index].setRef(ref);
    }

    public JVMObject getRef(int index){
        return this.localVals[index].getRef();
    }

    public String toString(){
        StringBuffer buffer = new StringBuffer();
        for(Slot s : localVals){
            buffer.append(s);
        }
        return buffer.toString();
    }
}
