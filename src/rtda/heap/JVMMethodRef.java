package rtda.heap;

import classfile.ConstantMethodrefInfo;
import tool.Tool;

public class JVMMethodRef extends JVMMemberRef{
    private JVMMethod method;

    public JVMMethodRef(JVMConstantPool cp, ConstantMethodrefInfo info) {
        this.cp = cp;
        this.copyMemberRefInfo(info);
    }
    
    public JVMMethod resolvedMethod(){
        if(this.method == null){
            this.resolveMethodRef();
        }
        return this.method;
    }

    private void resolveMethodRef() {
        JVMClass d = this.cp.klass;
        JVMClass c = this.resolvedClass();
        if(c.IsInterface()){
            Tool.panic("java.lang.IncompatibleClassChangeError");
        }
        JVMMethod method = lookupMethod(c, this.name, this.descriptor);
        if(method == null){
            Tool.panic("java.lang.NoSuchMethodError");
        }
        if(!method.isAccessibleTo(d)){
            Tool.panic("java.lang.IllegalAccessError");
        }
        this.method = method;
    }

    private JVMMethod lookupMethod(JVMClass c, String name, String descriptor) {
        JVMMethod method = lookupMethodInClass(c, name,descriptor);
        if(method == null){
            method = lookupMethodInInterfaces(c.interfaces, name, descriptor);
        }
        return method;
    }

    private JVMMethod lookupMethodInInterfaces(JVMClass[] interfaces, String name, String descriptor) {
        for(JVMClass i : interfaces){
            for(JVMMethod m : i.methods){
                if(name.equals(m.name) && descriptor.equals(m.descriptor)){
                    return method;
                }
            }

            JVMMethod method = lookupMethodInInterfaces(i.interfaces, name, descriptor);
            if(method != null){
                return method;
            }
        }
        return null;
    }

    public static JVMMethod lookupMethodInClass(JVMClass klass, String name, String descriptor) {
        JVMClass c = klass;
        while(c != null){
            for(JVMMethod m : c.methods){
                if(name.equals(m.name) && descriptor.equals(m.descriptor)){
                    return m;
                }
            }
            c = c.superclass;
        }
        return null;
    }

}
