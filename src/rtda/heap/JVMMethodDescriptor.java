package rtda.heap;

import java.util.ArrayList;

public class JVMMethodDescriptor {
    ArrayList<String> parameterTypes;
    String returnType;

    public JVMMethodDescriptor(){
        this.parameterTypes = new ArrayList<>();
    }
    public String[] getParameterTypes() {
        String[] ps = new String[parameterTypes.size()];
        return parameterTypes.toArray(ps);
    }

    public String getReturnType() {
        return returnType;
    }

    public void addParameterType(String t) {
        parameterTypes.add(t);
    }
}
