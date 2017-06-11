package rtda.heap;

import classfile.ConstantFieldrefInfo;
import tool.Tool;

public class JVMFieldRef extends JVMMemberRef{
    JVMField field;
    public JVMFieldRef(JVMConstantPool cp, ConstantFieldrefInfo info) {
        this.cp = cp;
        this.copyMemberRefInfo(info);
    }

    public JVMField resolvedField(){
        if(this.field == null){
            this.resolveFieldRef();
        }
        return this.field;
    }

    private void resolveFieldRef() {
        JVMClass d = this.cp.klass;
        JVMClass c = this.resolvedClass();
        JVMField field = lookupField(c, this.name, this.descriptor);
        if(field == null){
            Tool.panic("java.lang.NoSuchFieldError");
        }
        if(!field.isAccessibleTo(d)){
            Tool.panic("java.lang.IllegalAccessError");
        }
        this.field = field;
    }

    private JVMField lookupField(JVMClass c, String name, String descriptor) {
        for(JVMField field : c.fields){
            if(name.equals(field.name) && descriptor.equals(field.descriptor)){
                return field;
            }
        }

        for(JVMClass inter : c.interfaces){
            JVMField field = lookupField(inter, name, descriptor);
            if(field != null){
                return field;
            }
        }

        if(c.superclass != null){
            return lookupField(c.superclass, name, descriptor);
        }
        return null;
    }
}
