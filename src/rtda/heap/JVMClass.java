package rtda.heap;

import classfile.ClassFile;
import classfile.SourceFileAttribute;
import rtda.JVMFrame;
import rtda.JVMThread;
import rtda.LocalVars;
import tool.Tool;

import java.util.HashMap;

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
    private String sourceFile;

    public JVMClass(int accessFlag, String name, JVMClassLoader loader, boolean initStarted) {
        this.accessFlag = accessFlag;
        this.name = name;
        this.loader = loader;
        this.initStarted = initStarted;
    }

    public JVMObject getJclass() {
        return jclass;
    }

    JVMObject jclass;
    boolean initStarted; //if class has init
    public static HashMap<String, String> primitiveTypes;
    static{
        primitiveTypes = new HashMap<>();
        primitiveTypes.put("void", "V");
        primitiveTypes.put("boolean", "Z");
        primitiveTypes.put("byte", "B");
        primitiveTypes.put("short", "S");
        primitiveTypes.put("int", "I");
        primitiveTypes.put("long", "J");
        primitiveTypes.put("char", "C");
        primitiveTypes.put("float", "F");
        primitiveTypes.put("double", "D");
    }

    public JVMClass(int accessFlag, String name, JVMClassLoader jvmClassLoader, boolean initStarted, JVMClass superclass, JVMClass[] interfaces) {
        this.accessFlag = accessFlag;
        this.name = name;
        this.loader = jvmClassLoader;
        this.initStarted = initStarted;
        this.superclass = superclass;
        this.interfaces = interfaces;
    }

    public void startInit(){
        this.initStarted = true;
    }

    public boolean InitStarted(){
        return this.initStarted;
    }
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
        this.sourceFile = getSourceFile(cf);
    }

    private String getSourceFile(ClassFile cf) {
        SourceFileAttribute sfAttr = cf.getSourceFileAttribute();
        if(sfAttr != null){
            return sfAttr.FileName();
        }
        return "unknown";
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

        if (!s.IsArray()) {
            if (!s.IsInterface()) {
                // s is class
                if (!t.IsInterface()) {
                    // t is not interface
                    return s.isSubClassOf(t);
                } else {
                    // t is interface
                    return s.isImplements(t);
                }
            } else {
                // s is interface
                if (!t.IsInterface()) {
                    // t is not interface
                    return t.isJlObject();
                } else {
                    // t is interface
                    return t.isSuperInterfaceOf(s);
                }
            }
        } else {
            // s is array
            if (!t.IsArray()) {
                if (!t.IsInterface()) {
                    // t is class
                    return t.isJlObject();
                } else {
                    // t is interface
                    return t.isJlCloneable() || t.isJioSerializable();
                }
            } else {
                // t is array
                JVMClass sc = s.componentClass();
                JVMClass tc = t.componentClass();
                return sc == tc || tc.isAssignableFrom(sc);
            }
        }

    }

    
    private boolean isSuperInterfaceOf(JVMClass s) {
        return s.isSubInterfaceOf(this);
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

    private JVMMethod getMethod(String name, String des, boolean isStatic) {
        for(JVMMethod method : this.methods){
            if(method.IsStatic() == isStatic && name.equals(method.name)
                    && des.equals(method.descriptor)){
                return method;
            }
        }
        return null;
    }

    public boolean isSuperClassOf(JVMClass currentClass) {
        return currentClass.isSubClassOf(this);
    }

    public static void initClass(JVMThread thread, JVMClass klass) {
        klass.startInit();
        scheduleClinit(thread, klass);
        initSuperClass(thread, klass);

    }

    private static void initSuperClass(JVMThread thread, JVMClass klass) {
        if(!klass.IsInterface()){
            JVMClass superclass = klass.superclass;
            if(superclass != null && !superclass.InitStarted()){
                initClass(thread, superclass);
            }
        }
    }

    private static void scheduleClinit(JVMThread thread, JVMClass klass) {
        JVMMethod clinit = klass.getClinitMethod();
        if(clinit != null){
            JVMFrame frame = thread.newJVMFrame(clinit);
            thread.pushFrame(frame);
        }
    }

    public JVMMethod getClinitMethod() {
        return this.getStaticMethod("<clinit>", "()V");
    }

    public JVMObject newArray(int count){
        if(!this.IsArray()){
            Tool.panic("not array class: " + this.name);
        }

        switch (this.name){
            case "[Z" : return new JVMObject(this, count, LocalVars.BOOLTYPE);
            case "[B" : return new JVMObject(this, count, LocalVars.BTYPE);
            case "[C" : return new JVMObject(this, count, LocalVars.CTYPE);
            case "[S" : return new JVMObject(this, count, LocalVars.STYPE);
            case "[I" : return new JVMObject(this, count, LocalVars.ITYPE);
            case "[J" : return new JVMObject(this, count, LocalVars.LTYPE);
            case "[F" : return new JVMObject(this, count, LocalVars.FTYPE);
            case "[D" : return new JVMObject(this, count, LocalVars.DTYPE);
            default: return new JVMObject(this, count, LocalVars.REFTYPE);

        }
    }

    public boolean IsArray() {
       return this.name.charAt(0) == '[';
    }

    public JVMClass arrayClass() {
        String arrayClassName = getArrayClassName(this.name);
        return this.loader.loadClass(arrayClassName);
    }

    private String getArrayClassName(String name) {
        return "[" + toDescriptor(name);
    }

    private String toDescriptor(String name) {
        if(name.charAt(0) == '['){
            return name;
        }

        String d = primitiveTypes.get(name);
        if(d != null || !"".equals(d)){
            return d;
        }
        return "L" + name + ";";
    }

    public JVMClass componentClass() {
        String componentClassName = getComponentClassName(this.name);
        return this.loader.loadClass(componentClassName);
    }

    private String getComponentClassName(String name) {
        if(name.charAt(0) == '['){
            String componentTypeDescriptor = name.substring(1);
            return toClassName(componentTypeDescriptor);
        }
        Tool.panic("not arry: " + name);
        return null;
    }

    private String toClassName(String descriptor) {
        if(descriptor.charAt(0) == '['){
            return descriptor;
        }

        if(descriptor.charAt(0) == 'L'){
            return descriptor.substring(1, descriptor.length() - 1);
        }

        for(String c : primitiveTypes.keySet()){
            if(primitiveTypes.get(c).equals(descriptor)){
                return c;
            }
        }

        Tool.panic("Invalid descriptor: " + descriptor);
        return null;
    }

    public boolean isJlObject() {
        return this.name.equals("java/lang/Object");
    }

    public boolean isJlCloneable() {
        return this.name.equals("java/lang/Cloneable");
    }


    public boolean isJioSerializable() {
        return this.name.equals("java/io/Serializable");

    }

    public JVMField getField(String name, String desciptor, boolean isStatic) {
        JVMClass c = this;
        while(c != null){
            for(JVMField f : c.fields){
                if(f.IsStatic() == isStatic && f.name.equals(name)
                        && f.descriptor.equals(desciptor)){
                    return f;
                }
            }
            c = c.superclass;
        }
        return null;
    }

    public String javaName() {
        return this.name.replaceAll("/", ".");
    }

    public boolean isPrimitive() {
        return primitiveTypes.containsKey(this.name);
    }

    public JVMObject getRefVar(String name, String descriptor) {
        JVMField field = this.getField(name ,descriptor, true);
        return this.staticVars.getRef(field.slotId);
    }

    public JVMMethod getInstanceMethod(String name, String descriptor) {
        return this.getMethod(name, descriptor, false);
    }

    public String sourceFile() {
        return sourceFile;
    }
}
