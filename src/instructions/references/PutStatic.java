package instructions.references;

import instructions.base.Index16Instruction;
import rtda.JVMFrame;
import rtda.LocalVars;
import rtda.OperandStack;
import rtda.heap.*;
import tool.Tool;

public class PutStatic extends Index16Instruction{
    @Override
    public void execute(JVMFrame frame) {
        JVMMethod currentMethod = frame.getMethod();
        JVMClass currentClass = currentMethod.klass;
        JVMConstantPool cp = currentClass.getCp();
        JVMFieldRef fieldRef = cp.getContant(this.index).getFieldRef();
        JVMField field = fieldRef.resolvedField();
        JVMClass klass = field.klass;

        if(!field.IsStatic()){
            Tool.panic("java.lang.IncompatibleClassChangeError");
        }
        if(field.IsFinal()){
            if (currentClass != klass || !currentClass.getName().equals("<clinit>")) {
                Tool.panic("java.lang.IllealAccessError");
            }
        }

        String descriptor = field.descriptor;
        int slotId = field.slotId;
        LocalVars slots = klass.getStaticVars();
        OperandStack stack = frame.getOperandStack();
        switch (descriptor.charAt(0)){
            case 'Z':case 'B':case 'C':case 'S':
            case 'I':
                slots.setInt(slotId,stack.popInt());
                break;
            case 'F':
                slots.setFloat(slotId, stack.popFloat());
                break;
            case 'J':
                slots.setLong(slotId, stack.popLong());
                break;
            case 'D':
                slots.setDouble(slotId, stack.popDouble());
                break;
            case 'L': case '[':
                slots.setRef(slotId,stack.popRef());
                break;

        }
    }
}
