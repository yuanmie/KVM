package rtda;

import rtda.heap.JVMObject;
import tool.Tool;

public class Slot {
    int num;
    JVMObject ref;
    int assertType = 0;
    final int NUMTYPE = 0;
    final int OBJECTTYPE = 1;
    final int NULLTYPE = 2;
    public Slot(){

    }
    public int getNum() {
        if(this.assertType != NUMTYPE){
            Tool.panic("something wrong");
        }
        return num;
    }

    public void setNum(int num) {
        this.assertType = NUMTYPE;
        this.num = num;
    }

    public JVMObject getRef() {
//        if(this.assertType != OBJECTTYPE){
//            return null;
//        }


        return ref;
    }

    public void setRef(JVMObject ref) {
        if(ref == null){
            this.assertType = NULLTYPE;
            return;
        }
        this.assertType = OBJECTTYPE;
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
