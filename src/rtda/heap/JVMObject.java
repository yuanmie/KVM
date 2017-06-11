package rtda.heap;

import rtda.LocalVars;

public class JVMObject {
    public JVMClass klass;
    public LocalVars fields;

    public JVMObject(JVMClass klass) {
        this.klass = klass;
        this.fields = new LocalVars(klass.instanceSlotCount);
    }

    public boolean isInstanceOf(JVMClass klass) {
        return klass.isAssignableFrom(this.klass);
    }
}
