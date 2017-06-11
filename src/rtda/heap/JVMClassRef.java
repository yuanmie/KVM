package rtda.heap;

import classfile.ConstantClassInfo;


public class JVMClassRef extends JVMSymRef{
    public JVMClassRef(JVMConstantPool cp, ConstantClassInfo info) {
        this.cp  = cp;
        this.className = info.Name();
    }


}
