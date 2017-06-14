package instructions.references;

import instructions.base.ByteCodeReader;
import instructions.base.Instruction;
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import rtda.JVMFrame;
import rtda.LocalVars;
import rtda.OperandStack;
import rtda.heap.JVMClass;
import rtda.heap.JVMClassLoader;
import rtda.heap.JVMObject;
import tool.Tool;

public class NEW_ARRAY implements Instruction{
    int atype;

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        this.atype = reader.readUint8();
    }

    @Override
    public void execute(JVMFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int count = stack.popInt();
        if(count < 0){
            Tool.panic("java.lang.NegativArraySizeExcepton");
        }
        JVMClassLoader loader = frame.getMethod().klass.getLoader();
        JVMClass arrClass = getPrimitiveArrayClass(loader, this.atype);
        JVMObject arr = arrClass.newArray(count);
        stack.pushRef(arr);
    }

    private JVMClass getPrimitiveArrayClass(JVMClassLoader loader, int atype) {
        switch (atype){
            case LocalVars.BOOLTYPE: return loader.loadClass("[Z");
            case LocalVars.BTYPE: return loader.loadClass("[B");
            case LocalVars.STYPE: return loader.loadClass("[S");
            case LocalVars.ITYPE: return loader.loadClass("[I");
            case LocalVars.LTYPE: return loader.loadClass("[J");
            case LocalVars.CTYPE: return loader.loadClass("[C");
            case LocalVars.FTYPE: return loader.loadClass("[F");
            case LocalVars.DTYPE: return loader.loadClass("[D");
            default:
                Tool.panic("fuck array");
        }
        return null;
    }
}
