package Native;

import rtda.JVMFrame;
import rtda.heap.*;

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
    }
}
