package instructions.references;

import instructions.base.Index16Instruction;
import rtda.JVMFrame;
import rtda.LocalVars;
import rtda.OperandStack;
import rtda.heap.*;
import tool.Tool;

public class GetField extends Index16Instruction{
    @Override
    public void execute(JVMFrame frame) {
        JVMMethod currentMethod = frame.getMethod();
        JVMClass currentClass = currentMethod.klass;
        JVMConstantPool cp = currentClass.getCp();
        JVMFieldRef fieldRef = cp.getContant(this.index).getFieldRef();
        JVMField field = fieldRef.resolvedField();
        //JVMClass klass = field.klass;

        if(field.IsStatic()){
            Tool.panic("java.lang.IncompatibleClassChangeError");
        }

        OperandStack stack = frame.getOperandStack();
        JVMObject ref = stack.popRef();
        if(ref == null) {
            Tool.panic("java.lang.NullPointerException");
        }

        String descriptor = field.descriptor;
        int slotId = field.slotId;
        LocalVars slots = ref.fields;

        switch (descriptor.charAt(0)){
            case 'Z':case 'B':case 'C':case 'S':
            case 'I':
                stack.pushInt(slots.getInt(slotId));
                break;
            case 'F':
                stack.pushFloat(slots.getFloat(slotId));
                break;
            case 'J':
                stack.pushLong(slots.getLong(slotId));
                break;
            case 'D':
                stack.pushDouble(slots.getDouble(slotId));
                break;
            case 'L': case '[':
                stack.pushRef(slots.getRef(slotId));
                break;
        }
    }
}
