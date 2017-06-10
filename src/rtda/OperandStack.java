package rtda;

public class OperandStack {
    long size;
    Slot[] slots;
    public OperandStack(long maxStack) {
        if(maxStack > 0){
            this.slots = new Slot[(int)maxStack];
            for(int i = 0; i < maxStack; i++){
                slots[i] = new Slot();
            }
        }
    }

    public void pushInt(int val){
        this.slots[(int)this.size].setNum(val);
        ++this.size;
    }

    public int popInt(){
        --this.size;
        return this.slots[(int)this.size].getNum();
    }

    public void pushFloat(float val){
        pushInt(Float.floatToIntBits(val));
    }

    public float popFloat(){
        return Float.intBitsToFloat(popInt());
    }

    public void pushLong(long val){
        this.slots[(int)this.size].setNum((int)val);
        this.slots[(int)this.size + 1].setNum((int)(val >> 32));
        this.size += 2;
    }

    public long popLong(){
        this.size -= 2;
        long low = this.slots[(int)this.size].getNum();
        long high = this.slots[(int)(this.size + 1)].getNum();
        return high << 32 | (low & 0xFFFFFFFFl);
    }

    public void pushDouble(Double val){
        pushLong(Double.doubleToLongBits(val));
    }

    public Double popDouble(){
        return Double.longBitsToDouble(popLong());
    }

    public void pushRef(JVMObject ref){
        this.slots[(int)this.size].setRef(ref);
        ++this.size;
    }

    public JVMObject popRef(){
        --this.size;
        JVMObject ref = this.slots[(int)this.size].getRef();
        this.slots[(int)this.size].setRef(null);
        return ref;
    }

    public void pushSlot(Slot slot){
        this.slots[(int)this.size] = slot;
        ++this.size;
    }

    public Slot popSlot(){
        --this.size;
        return this.slots[(int)this.size];
    }

    public String toString(){
        StringBuffer buffer = new StringBuffer();
        for(Slot s : slots){
            buffer.append(s);
        }
        return buffer.toString();
    }
}
