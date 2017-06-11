package rtda;

import rtda.heap.JVMObject;

public class Slot {
    int num;
    JVMObject ref;

    public Slot(){

    }
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public JVMObject getRef() {
        return ref;
    }

    public void setRef(JVMObject ref) {
        this.ref = ref;
    }

    public String toString(){
        if(ref == null){
            return String.format("%d\n", num);
        }else{
            return "ref\n";
        }
    }
}
