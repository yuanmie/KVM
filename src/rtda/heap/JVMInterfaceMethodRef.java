package rtda.heap;

import classfile.ConstantInterfaceMethodrefInfo;
import classfile.MemberInfo;
import tool.Tool;

public class JVMInterfaceMethodRef extends JVMMemberRef{
    JVMMethod method;
    public JVMInterfaceMethodRef(JVMConstantPool cp, ConstantInterfaceMethodrefInfo info) {
        this.cp = cp;
        this.copyMemberRefInfo(info);
    }

    public JVMMethod resolvedMethod(){
        if(this.method == null){
            this.resolveInterfaceMethodRef();
        }
        return this.method;
    }

    private void resolveInterfaceMethodRef() {
        JVMClass d = this.cp.klass;
        JVMClass c = this.resolvedClass();
        if(!c.IsInterface()){
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
        for(JVMMethod m : c.methods){
            if(name.equals(m.name) && descriptor.equals(m.descriptor)){
                return method;
            }
        }

        return lookupMethodInInterfaces(c.interfaces, name, descriptor);
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

    private JVMMethod lookupMethodInClass(JVMClass klass, String name, String descriptor) {
        JVMClass c = klass;
        while(c != null){

            c = c.superclass;
        }
        return null;
    }
}
