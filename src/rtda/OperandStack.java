package rtda;

import rtda.heap.JVMObject;

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
        dealSlot();
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
        dealSlot();
        this.slots[(int)this.size].setRef(ref);
        ++this.size;
    }

    /*
    赋一个新的slot.
     */
    private void dealSlot(){
        this.slots[(int)this.size] = null;
        this.slots[(int)this.size] = new Slot();
    }

    public JVMObject popRef(){
        --this.size;
        Slot s = this.slots[(int)this.size];
        if(s == null){
            return null;
        }else{
            //this.slots[(int)this.size].setRef(null);
            return s.getRef();
        }
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

    public JVMObject getRefFromTop(int i) {
        if(this.slots[(int)(this.size - 1 - i)] == null){
            return null;
        }
        return this.slots[(int)(this.size - 1 - i)].getRef();
    }

    public void pushBoolean(boolean b) {
        this.pushInt((b ? 1 : 0));
    }

    public void clear() {
        this.size = 0;
        for(int i = 0; i < slots.length; i++){
            slots[i] = null;
        }
    }
}
