package rtda.heap;

import classfile.ClassFile;
import classpath.Classpath;
import classpath.Result;
import rtda.LocalVars;
import tool.Tool;

import java.util.HashMap;
import java.util.Map;

public class JVMClassLoader {
    Classpath cp;
    Map<String, JVMClass> classMap;

    public JVMClassLoader(Classpath cp) {
        this.cp = cp;
        this.classMap = new HashMap<>();
    }

    public static JVMClassLoader newJVMClassLoader(Classpath cp){
        JVMClassLoader cl = new JVMClassLoader(cp);
        cl.loadBasicClasses();
        cl.loadPrimitiveClasses();
        return cl;
    }
    public void loadBasicClasses(){
        JVMClass jlclassClass = this.loadClass("java/lang/Class");
        for(Map.Entry<String, JVMClass> e : classMap.entrySet()){
            JVMClass c = e.getValue();
            if(c.jclass == null){
                c.jclass = jlclassClass.newObject();
                c.jclass.extra = c;
            }
        }
    }

    public void loadPrimitiveClasses(){
        for (String primitiveType :JVMClass.primitiveTypes.keySet()){
            this.loadPrimitiveClass(primitiveType);
        }
    }

    private void loadPrimitiveClass(String className) {
        JVMClass c = new JVMClass(ACCESSFLAG.ACC_PUBLIC,
                className,
                this,
                true);

        c.jclass = this.classMap.get("java/lang/Class").newObject();
        c.jclass.extra = c;
        this.classMap.put(className, c);
    }

    public JVMClass loadClass(String name){
        if(this.classMap.get(name) != null){
            return this.classMap.get(name);
        }
        JVMClass c;
        if(name.charAt(0) == '['){
            c =  this.loadArrayClass(name);
        }else{
            c =  this.loadNonArrayClass(name);
        }

        if(classMap.get("java/lang/Class") != null){
            JVMClass temp = classMap.get("java/lang/Class");
            c.jclass = temp.newObject();
            c.jclass.extra = c;
        }
        return c;
    }

    private JVMClass loadArrayClass(String name) {
        JVMClass klass = new JVMClass(ACCESSFLAG.ACC_PUBLIC,
                name, this, true, this.loadClass("java/lang/Object"),
                new JVMClass[]{this.loadClass("java/lang/Cloneable"),
                this.loadClass("java/io/Serializable")});

        this.classMap.put(name, klass);
        return klass;
    }

    private JVMClass loadNonArrayClass(String name) {
        Result r = this.readClass(name);
        JVMClass klass = this.defineClass(r.getData());
        link(klass);
        System.out.printf("[Loaded %s from %s]\n", name, r.getEntry());
        return klass;
    }

    private void link(JVMClass klass) {
        verify(klass);
        perpare(klass);
    }

    private void perpare(JVMClass klass) {
        calcInstanceFieldSlotIds(klass);
        calcStaticFieldSlotIds(klass);
        allocAndInitStaticVars(klass);
    }

    private void allocAndInitStaticVars(JVMClass klass) {
        klass.staticVars = new LocalVars(klass.staticSlotCount);
        for(JVMField field : klass.fields){
            if(field.IsStatic() && field.IsFinal()){
                initStaticFinalVar(klass, field);
            }
        }
    }

    private void initStaticFinalVar(JVMClass klass, JVMField field) {
        LocalVars vars = klass.staticVars;
        JVMConstantPool cp = klass.cp;
        int cpIndex = field.constValueIndex;
        int slotId = field.slotId;
        if(cpIndex > 0){
            switch (field.descriptor){
                case "Z":case "B":case "C":case "S":
                case "I":
                    int val = cp.getContant(cpIndex).getIntVal();
                    vars.setInt(slotId, val);
                    break;
                case "J":
                    long l = cp.getContant(cpIndex).getLongVal();
                    vars.setLong(slotId,l);
                    break;
                case "F":
                    float f = cp.getContant(cpIndex).getFloatVal();
                    vars.setFloat(slotId, f);
                    break;
                case "D":
                    double d = cp.getContant(cpIndex).getDoubleVal();
                    vars.setDouble(slotId, d);
                    break;
                case "Ljava/lang/String;":
                    String str = cp.getContant(cpIndex).getStrVal();
                    JVMObject jstr = JVMString.newJVMString(klass.loader, str);
                    vars.setRef(slotId, jstr);
            }
        }

    }

    private void calcStaticFieldSlotIds(JVMClass klass) {
        int slotId = 0;
        if(klass.superclass != null){
            slotId = klass.superclass.instanceSlotCount;
        }

        for(JVMField field : klass.fields){
            if(field.IsStatic()){
                field.slotId = slotId;
                ++slotId;
                if(field.isLongOrDouble()){
                    ++slotId;
                }
            }
        }
        klass.staticSlotCount = slotId;
    }

    private void calcInstanceFieldSlotIds(JVMClass klass) {
        int slotId = 0;
        if(klass.superclass != null){
            slotId = klass.superclass.instanceSlotCount;
        }

        for(JVMField field : klass.fields){
            if(!field.IsStatic()){
                field.slotId = slotId;
                ++slotId;
                if(field.isLongOrDouble()){
                    ++slotId;
                }
            }
        }
        klass.instanceSlotCount = slotId;
    }

    private void verify(JVMClass klass) {
    }

    private JVMClass defineClass(byte[] data) {
        JVMClass klass = parseClass(data);
        klass.loader = this;
        resolveSupperClass(klass);
        resolveInterfaces(klass);
        this.classMap.put(klass.name, klass);
        return klass;
    }

    private void resolveInterfaces(JVMClass klass) {
        int interfaceCount = klass.interfaceNames.length;
        if(interfaceCount > 0){
            for(int i = 0; i < interfaceCount; i++){
                klass.interfaces[i] = klass.loader.loadClass(klass.interfaceNames[i]);
            }
        }
    }

    private void resolveSupperClass(JVMClass klass) {
        if(!klass.name.equals("java/lang/Object")){
            klass.superclass = klass.loader.loadClass(klass.superclassName);
        }
    }

    private JVMClass parseClass(byte[] data) {
        ClassFile cf = new ClassFile(data);
        return new JVMClass(cf);

    }

    private Result readClass(String name) {
        Result r = this.cp.readClass(name);
        if( r == null){
            Tool.panic("java.lang.ClassNotFoundException: " + name);
        }
        return r;
    }
}
