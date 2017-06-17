package Native;

import rtda.JVMFrame;
import rtda.LocalVars;
import rtda.heap.*;
import tool.Tool;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Consumer;

public class Native {
    static HashMap<String, Consumer<JVMFrame>> methodMap
            = new HashMap<>();

    static Consumer<JVMFrame> emtpyConsumer = (x) -> {};;
    static Consumer<JVMFrame> getClass = (frame)->
    {
        JVMObject t = frame.getLocalVars().getThis();
        JVMObject k = t.klass.getJclass();
        frame.getOperandStack().pushRef(k);

    };

    static Consumer<JVMFrame> getPrimitiveClass = (frame)->
    {
        JVMObject t = frame.getLocalVars().getThis();
        String name = toJVMString(t);
        JVMClassLoader loader = frame.getMethod().klass.getLoader();
        JVMObject k = loader.loadClass(name).getJclass();
        frame.getOperandStack().pushRef(k);

    };

    static Consumer<JVMFrame> getName0 = (frame)->
    {
        JVMObject t = frame.getLocalVars().getThis();
        JVMClass klass = (JVMClass) t.extra;
        String name = klass.javaName();
        JVMObject k = JVMString.newJVMString(klass.getLoader(), name);
        frame.getOperandStack().pushRef(k);

    };

    static Consumer<JVMFrame> desiredAssertionStatus0 = (frame)->
    {
        frame.getOperandStack().pushBoolean(false);

    };
    private static Consumer<JVMFrame> arraycopy = (frame) ->
    {
        LocalVars vars= frame.getLocalVars();
        JVMObject src = vars.getRef(0);
        int srcPos = vars.getInt(1);
        JVMObject dest = vars.getRef(2);
        int destPos = vars.getInt(3);
        int length = vars.getInt(4);

        if(src == null || dest == null){
            Tool.panic("java.lang.NullPointerException");
        }

        if(!checkArrayCopy(src, dest)){
            Tool.panic("java.lang.ArrayStoreException");
        }

        if(srcPos < 0 || destPos < 0 || length < 0 ||
                srcPos + length > src.arrayLength() ||
                destPos+length > dest.arrayLength()){
            Tool.panic("java.lang.IndexOutOfBoundsException");
        }
        ArrayCopy(src, dest, srcPos, destPos, length);
    };

    private static Consumer<JVMFrame> floatToRawIntBits = (frame) ->
    {
        float value = frame.getLocalVars().getFloat(0);
        int bits = Float.floatToIntBits(value);
        frame.getOperandStack().pushInt(bits);
    };

    private static Consumer<JVMFrame> doubleToRawLongBits = (frame) ->
    {
        double value = frame.getLocalVars().getDouble(0);
        long bits = Double.doubleToLongBits(value);
        frame.getOperandStack().pushLong(bits);
    };
    private static Consumer<JVMFrame> intern = (frame) ->
    {
        JVMObject t = frame.getLocalVars().getThis();
        JVMObject interned = JVMString.internString(t);
        frame.getOperandStack().pushRef(interned);
    };

    private static void ArrayCopy(JVMObject src, JVMObject dest, int srcPos, int destPos, int length) {
        switch (src.fields.getArrayType()){
            case LocalVars.BTYPE:

                System.arraycopy(src.fields.getBarray(), srcPos, dest.fields.getBarray(), destPos, length); break;
            case LocalVars.STYPE:
                System.arraycopy(src.fields.getSarray(), srcPos, dest.fields.getSarray(), destPos, length); break;
            case LocalVars.ITYPE:
                System.arraycopy(src.fields.getIarray(), srcPos, dest.fields.getIarray(), destPos, length); break;
            case LocalVars.LTYPE:
                System.arraycopy(src.fields.getLarray(), srcPos, dest.fields.getLarray(), destPos, length); break;
            case LocalVars.CTYPE:
                System.arraycopy(src.fields.getCarray(), srcPos, dest.fields.getCarray(), destPos, length); break;
            case LocalVars.FTYPE:
                System.arraycopy(src.fields.getFarray(), srcPos, dest.fields.getFarray(), destPos, length); break;
            case LocalVars.DTYPE:
                System.arraycopy(src.fields.getDarray(), srcPos, dest.fields.getDarray(), destPos, length); break;
            case LocalVars.REFTYPE:
                System.arraycopy(src.fields.getRefarray(), srcPos, dest.fields.getRefarray(), destPos, length); break;
            default:
                Tool.panic("fuck array");
        }
    }

    private static boolean checkArrayCopy(JVMObject src, JVMObject dest) {
        JVMClass srcClass = src.klass;
        JVMClass destClass = dest.klass;

        if(!srcClass.IsArray() || !destClass.IsArray()){
            return false;
        }

        if(srcClass.componentClass().isPrimitive() ||
                destClass.componentClass().isPrimitive()){
            return srcClass == destClass;
        }

        return true;
    }


    public static void register(String className,
                         String methodName,
                         String methodDescriptor,
                         Consumer<JVMFrame> func){
        String key = className + "~" + methodName
                + "~" + methodDescriptor;
        methodMap.put(key, func);
    }

    public static Consumer<JVMFrame> findNativeMethod(String className,
                                               String methodName,
                                               String methodDescriptor){
        String key = className + "~" + methodName
                + "~" + methodDescriptor;
        if(methodMap.containsKey(key)){
            return methodMap.get(key);
        }

        if(methodDescriptor.equals("()V")
                && methodName.equals("registerNatives")){
            return emtpyConsumer;
        }

        return null;
    }

    public static String toJVMString(JVMObject jstr) {
        JVMObject o = jstr.getRefVar("value", "[C");
        return new String(o.Chars());
    }

    public static void init(){
        String jlClass = "java/lang/Class";
        register(jlClass, "getPrimitiveClass", "(Ljava/lang/String;)Ljava/lang/Class;", getPrimitiveClass);
        register(jlClass, "getName0", "()Ljava/lang/String;", getName0);
        register(jlClass, "desiredAssertionStatus0", "(Ljava/lang/Class;)Z", desiredAssertionStatus0);


        register("java/lang/System", "arraycopy",
                "(Ljava/lang/Object;ILjava/lang/Object;II)V", arraycopy);
        register("java/lang/Float", "floatToRawIntBits",
                "(F)I", floatToRawIntBits);
        register("java/lang/Double", "doubleToRawLongBits",
                "(D)J", doubleToRawLongBits);

        register("java/lang/String", "intern", "()Ljava/lang/String;"
        , intern);
    }
}
