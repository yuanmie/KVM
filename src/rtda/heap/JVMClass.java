package rtda.heap;

import classfile.ClassFile;
import rtda.LocalVars;

public class JVMClass {
    int accessFlag;
    String name;
    String superclassName;
    String[] interfaceNames;
    JVMConstantPool cp;
    JVMField[] fields;
    JVMMethod[] methods;
    JVMClassLoader loader;
    JVMClass superclass;
    JVMClass[] interfaces;
    int instanceSlotCount;
    int staticSlotCount;
    LocalVars staticVars;

    public int getAccessFlag() {
        return accessFlag;
    }

    public String getName() {
        return name;
    }

    public String getSuperclassName() {
        return superclassName;
    }

    public String[] getInterfaceNames() {
        return interfaceNames;
    }

    public JVMConstantPool getCp() {
        return cp;
    }

    public JVMField[] getFields() {
        return fields;
    }

    public JVMMethod[] getMethods() {
        return methods;
    }

    public JVMClassLoader getLoader() {
        return loader;
    }

    public JVMClass getSuperclass() {
        return superclass;
    }

    public JVMClass[] getInterfaces() {
        return interfaces;
    }

    public int getInstanceSlotCount() {
        return instanceSlotCount;
    }

    public int getStaticSlotCount() {
        return staticSlotCount;
    }

    public LocalVars getStaticVars() {
        return staticVars;
    }

    public JVMClass(ClassFile cf){
        this.accessFlag = cf.getAccessFlag();
        this.name = cf.className();
        this.superclassName = cf.superClassName();
        this.interfaceNames = cf.InterfaceNames();
        this.interfaces = new JVMClass[this.interfaceNames.length];
        this.cp = new JVMConstantPool(this, cf.getConstantPool());
        this.fields = JVMField.newFields(this, cf.getFields());
        this.methods = JVMMethod.newMethods(this, cf.getMethods());
    }

    public boolean IsPublic()  {
        return (this.accessFlag&ACCESSFLAG.ACC_PUBLIC) != 0;
    }
    public boolean IsFinal()  {
        return 0 != (this.accessFlag&ACCESSFLAG.ACC_FINAL);
    }
    public boolean IsSuper()  {
        return 0 != (this.accessFlag&ACCESSFLAG.ACC_SUPER);
    }
    public boolean IsInterface()  {
        return 0 != (this.accessFlag&ACCESSFLAG.ACC_INTERFACE);
    }
    public boolean IsAbstract()  {
        return 0 != (this.accessFlag&ACCESSFLAG.ACC_ABSTRACT);
    }
    public boolean IsSynthetic()  {
        return 0 != (this.accessFlag&ACCESSFLAG.ACC_SYNTHETIC);
    }
    public boolean IsAnnotation()  {
        return 0 != (this.accessFlag&ACCESSFLAG.ACC_ANNOTATION);
    }
    public boolean IsEnum()  {
        return 0 != (this.accessFlag&ACCESSFLAG.ACC_ENUM);
    }

    public boolean isAccessibleTo(JVMClass other) {
        return this.IsPublic() || this.getPackageName().equals(
                other.getPackageName());
    }

    public String getPackageName() {
        int index = this.name.lastIndexOf("/");
        if(index >= 0){
            return this.name.substring(0, index);
        }
        return "";
    }

    public JVMObject newObject() {
        return new JVMObject(this);
    }

    public boolean isAssignableFrom(JVMClass other) {
        JVMClass s = other;
        JVMClass t = this;

        if(s == t){
            return true;
        }
        if(!t.IsInterface()){
            return s.isSubClassOf(t);
        }else{
            return s.isImplements(t);
        }
    }

    public boolean isImplements(JVMClass t) {
        JVMClass c = this;
        while(c != null){
            for(JVMClass i : c.interfaces){
                if(i == t || i.isSubInterfaceOf(t)){
                    return true;
                }
            }
            c = c.superclass;
        }
        return false;
    }

    private boolean isSubInterfaceOf(JVMClass t) {
        for(JVMClass i : this.interfaces){
            if(i == t || i.isSubInterfaceOf(t)){
                return true;
            }
        }
        return false;
    }

    public boolean isSubClassOf(JVMClass t) {
        JVMClass c = this.superclass;
        while(c != null){
            if(c == t){
                return true;
            }
            c = c.superclass;
        }
        return  false;
    }

    public JVMMethod getMainMethod() {
        return this.getStaticMethod("main", "([Ljava/lang/String;)V");
    }

    private JVMMethod getStaticMethod(String name, String des) {
        for(JVMMethod method : this.methods){
            if(method.IsStatic() && name.equals(method.name)
                    && des.equals(method.descriptor)){
                return method;
            }
        }
        return null;
    }


    public boolean isSuperClassOf(JVMClass currentClass) {
        return currentClass.isSubClassOf(this);
    }
}
