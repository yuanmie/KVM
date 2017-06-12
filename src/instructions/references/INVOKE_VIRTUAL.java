package instructions.references;

import instructions.base.Index16Instruction;
import instructions.base.Method_invoke_logic;
import rtda.JVMFrame;
import rtda.OperandStack;
import rtda.heap.*;
import tool.Tool;

public class INVOKE_VIRTUAL extends Index16Instruction {
    @Override
    public void execute(JVMFrame frame) {
        JVMClass currentClass = frame.getMethod().klass;
        JVMConstantPool cp = currentClass.getCp();
        JVMMethodRef methodRef = cp.getContant(this.index).getMethodRef();
        JVMClass resolvedClass = methodRef.resolvedClass();
        JVMMethod resolvedMethod = methodRef.resolvedMethod();
        if(resolvedMethod.name.equals("<init>") && resolvedMethod.klass != resolvedClass){
            Tool.panic("java.lang.NoSuchMethodError");
        }


        if(resolvedMethod.IsStatic()){
            Tool.panic("java.lang.IncomatibleClassChangeError");
        }

        JVMObject ref = frame.getOperandStack().getRefFromTop(resolvedMethod.argSlotCount() - 1);
        if(ref == null){
            if(methodRef.getName().equals("println")){
                _println(frame.getOperandStack(),methodRef.getDescriptor());
                return;
            }
            Tool.panic("java.lang.NullPointerException");
        }

        if(resolvedMethod.IsProtected() &&
                resolvedMethod.klass.isSuperClassOf(currentClass)
                && !resolvedMethod.klass.getPackageName().equals(currentClass.getPackageName())
                && ref.klass != currentClass
                && !ref.klass.isSubClassOf(currentClass)){
            Tool.panic("java.lang.IllegalAccessError");
        }

        JVMMethod methodToBeInvoked  = JVMMethodRef.lookupMethodInClass(currentClass.getSuperclass(),
                    methodRef.getName(), methodRef.getDescriptor());

        if(methodToBeInvoked == null || methodToBeInvoked.IsAbstract()){
            Tool.panic("java.lang.AbstractMethodError");
        }
        Method_invoke_logic.InvokeMethod(frame, methodToBeInvoked);

    }

    private void _println(OperandStack stack, String descriptor) {
        switch (descriptor){
            case "(Z)V":
                System.out.printf("%d\n", stack.popInt());
                break;
            case "(C)V":
                System.out.printf("%d\n", stack.popInt());
                break;
            case "(B)V":
                System.out.printf("%d\n", stack.popInt());
                break;
            case "(S)V":
                System.out.printf("%d\n", stack.popInt());
                break;
            case "(I)V":
                System.out.printf("%d\n", stack.popInt());
                break;
            case "(J)V":
                System.out.printf("%d\n", stack.popLong());
                break;
            case "(F)V":
                System.out.printf("%f\n", stack.popFloat());
                break;
            case "(D)V":
                System.out.printf("%f\n", stack.popDouble());
                break;
            default:
                Tool.panic("println: " + descriptor);
        }

        stack.popRef();
    }
}
