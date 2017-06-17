package rtda.heap;


import java.util.HashMap;
import Native.Native;
public class JVMString {
    static HashMap<String, JVMObject> map = new HashMap<>();
    public static JVMObject newJVMString(JVMClassLoader loader, String str){
        if(map.containsKey(str)){
            return map.get(str);
        }
        char[] chars = str.toCharArray();
        JVMObject jchars = new JVMObject(loader.loadClass("[C"), chars);
        JVMObject jvmstr = loader.loadClass("java/lang/String").newObject();
        jvmstr.setRefVar("value", "[C", jchars);
        map.put(str, jvmstr);
        return jvmstr;
    }

    public static JVMObject internString(JVMObject t) {
         String str = Native.toJVMString(t);
         if(map.containsKey(str)){
             return map.get(str);
         }
         map.put(str, t);
         return t;
    }
}
