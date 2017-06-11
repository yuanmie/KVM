package rtda.heap;

import tool.Tool;

public class JVMSymRef {
    JVMConstantPool cp;
    String className;
    JVMClass klass;

    public JVMClass resolvedClass(){
        if(this.klass == null){
            this.resolveClassRef();
        }
        return this.klass;
    }

    private void resolveClassRef() {
        JVMClass d = cp.klass;
        JVMClass c = d.loader.loadClass(this.className);
        if(!c.isAccessibleTo(d)){
            Tool.panic("java.lang.IllegalAccessError");
        }
        this.klass = c;
    }
}
