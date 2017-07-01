package rtda;

import rtda.heap.JVMObject;

import java.nio.ByteBuffer;

public class LocalVars {

    Slot[] localVals;

    /*
    array
     */
    byte[] barray;
    short[] sarray;
    int[] iarray;
    long[] larray;
    char[] carray;
    float[] farray;
    double[] darray;
    JVMObject[] refarray;
    public static final int BOOLTYPE = 4;
    public static final int BTYPE = 8;
    public static final int STYPE = 9;
    public static final int ITYPE = 10;
    public static final int LTYPE = 11;
    public static final int CTYPE = 5;
    public static final int FTYPE = 6;
    public static final int DTYPE = 7;
    public static final int REFTYPE = 12;

    public LocalVars() {

    }

    public int getArrayType() {
        return arrayType;
    }

    int arrayType = -1;

    public byte[] getBarray() {
        return barray;
    }

    public void setBarray(byte[] barray) {
        this.arrayType = BTYPE;
        this.barray = barray;
    }

    public short[] getSarray() {
        return sarray;
    }

    public void setSarray(short[] sarray) {
        this.arrayType = STYPE;
        this.sarray = sarray;
    }

    public int[] getIarray() {
        return iarray;
    }

    public void setIarray(int[] iarray) {
        this.arrayType = ITYPE;
        this.iarray = iarray;
    }

    public long[] getLarray() {
        return larray;
    }

    public void setLarray(long[] larray) {
        this.arrayType = LTYPE;
        this.larray = larray;
    }

    public char[] getCarray() {
        return carray;
    }

    public void setCarray(char[] carray) {
        this.arrayType = CTYPE;
        this.carray = carray;
    }

    public float[] getFarray() {
        return farray;
    }

    public void setFarray(float[] farray) {
        this.arrayType = FTYPE;
        this.farray = farray;
    }

    public double[] getDarray() {
        return darray;
    }

    public void setDarray(double[] darray) {
        this.arrayType = DTYPE;
        this.darray = darray;
    }

    public JVMObject[] getRefarray() {
        return refarray;
    }

    public void setRefarray(JVMObject[] refarray) {
        this.arrayType = REFTYPE;
        this.refarray = refarray;
    }

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
        if(this.localVals[index] == null){
            int debugflag = 1;
        }
        return this.localVals[index].getRef();
    }

    public String toString(){
        StringBuffer buffer = new StringBuffer();
        for(Slot s : localVals){
            buffer.append(s);
        }
        return buffer.toString();
    }

    public void setSlot(int i, Slot slot) {
        this.localVals[i] = slot;
    }

    public JVMObject getThis() {
        return getRef(0);
    }
}
