package instructions.references;

import instructions.base.ByteCodeReader;
import instructions.base.Index16Instruction;
import instructions.base.Instruction;
import rtda.JVMFrame;
import rtda.LocalVars;
import rtda.OperandStack;
import rtda.heap.*;
import tool.Tool;

public class ANEW_ARRAY extends Index16Instruction{



    @Override
    public void execute(JVMFrame frame) {
        JVMConstantPool cp = frame.getMethod().klass.getCp();
        JVMClassRef classRef = cp.getContant(this.index).getClassRef();
        JVMClass componentClass = classRef.resolvedClass();
        OperandStack stack = frame.getOperandStack();
        int count = stack.popInt();
        if(count < 0){
            Tool.panic("java.lang.NegativArraySizeExcepton");
        }

        JVMClass arrClass = componentClass.arrayClass();
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
