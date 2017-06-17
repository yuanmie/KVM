package instructions.references;

import instructions.base.Index16Instruction;
import rtda.JVMFrame;
import rtda.LocalVars;
import rtda.OperandStack;
import rtda.heap.*;
import tool.Tool;

public class PutField extends Index16Instruction {
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
        if(field.IsFinal()){
            if (currentClass != field.klass || !currentMethod.name.equals("<init>")) {
                Tool.panic("java.lang.IllealAccessError");
            }
        }

        String descriptor = field.descriptor;
        int slotId = field.slotId;
        //LocalVars slots = klass.getStaticVars();
        OperandStack stack = frame.getOperandStack();
        JVMObject ref;
        switch (descriptor.charAt(0)){
            case 'Z':case 'B':case 'C':case 'S':
            case 'I':
                int i = stack.popInt();
                ref = stack.popRef();
                if(ref == null){
                    Tool.panic("java.lang.NullPointerException");
                }
                ref.fields.setInt(slotId, i);
                break;
            case 'F':
                float f = stack.popFloat();
                ref = stack.popRef();
                if(ref == null){
                    Tool.panic("java.lang.NullPointerException");
                }
                ref.fields.setFloat(slotId, f);
                break;
            case 'J':
                long l = stack.popLong();
                ref = stack.popRef();
                if(ref == null){
                    Tool.panic("java.lang.NullPointerException");
                }
                ref.fields.setLong(slotId, l);
                break;
            case 'D':
                Double d = stack.popDouble();
                ref = stack.popRef();
                if(ref == null){
                    Tool.panic("java.lang.NullPointerException");
                }
                ref.fields.setDouble(slotId, d);
                break;
            case 'L': case '[':
                JVMObject o = stack.popRef();
                ref = stack.popRef();
                if(ref == null){
                    Tool.panic("java.lang.NullPointerException");
                }
                ref.fields.setRef(slotId, o);
                break;

        }
    }
}
