package rtda.heap;

public class JVMConstant {
    private int type;
    public int intVal;
    public float floatVal;
    public long longVal;
    public double doubleVal;
    public String strVal;
    public JVMClassRef classRef;
    public JVMFieldRef fieldRef;
    public JVMMethodRef methodRef;
    public JVMInterfaceMethodRef interfaceMethodRef;

    public static final int ISINT = 1;
    public final static int ISFLOAT = 2;
    public final static int ISLONG = 3;
    public final static int ISDOUBLE = 4;
    public final static int ISSTR = 5;
    public final static int ISCLASSREF = 6;
    public final static int ISFIELDREF = 7;
    public final static int ISMETHODREF = 8;
    public final static int ISINTERFACEMETHODREF = 9;



    public int getIntVal() {
        return intVal;
    }

    public void setIntVal(int intVal) {
        this.type = ISINT;
        this.intVal = intVal;
    }

    public float getFloatVal() {
        return floatVal;
    }

    public void setFloatVal(float floatVal) {
        this.type = ISFLOAT;
        this.floatVal = floatVal;
    }

    public long getLongVal() {
        return longVal;
    }

    public void setLongVal(long longVal) {
        this.type = ISLONG;
        this.longVal = longVal;
    }

    public double getDoubleVal() {

        return doubleVal;
    }

    public void setDoubleVal(double doubleVal) {
        this.type = ISDOUBLE;
        this.doubleVal = doubleVal;
    }

    public String getStrVal() {
        return strVal;
    }

    public void setStrVal(String strVal) {
        this.type = ISSTR;
        this.strVal = strVal;
    }

    public JVMClassRef getClassRef() {
        return classRef;
    }

    public void setClassRef(JVMClassRef classRef) {
        this.type = ISCLASSREF;
        this.classRef = classRef;
    }

    public JVMFieldRef getFieldRef() {
        return fieldRef;
    }

    public void setFieldRef(JVMFieldRef fieldRef) {
        this.type = ISFIELDREF;
        this.fieldRef = fieldRef;
    }

    public JVMMethodRef getMethodRef() {
        return methodRef;
    }

    public void setMethodRef(JVMMethodRef methodRef) {
        this.type = ISMETHODREF;
        this.methodRef = methodRef;
    }

    public JVMInterfaceMethodRef getInterfaceMethodRef() {
        return interfaceMethodRef;
    }

    public void setInterfaceMethodRef(JVMInterfaceMethodRef interfaceMethodRef) {
        this.type = ISINTERFACEMETHODREF;
        this.interfaceMethodRef = interfaceMethodRef;
    }

    public int getType() {
        return type;
    }
}
