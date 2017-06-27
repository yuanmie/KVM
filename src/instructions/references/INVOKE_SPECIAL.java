package instructions.references;

import instructions.base.Index16Instruction;
import instructions.base.Method_invoke_logic;
import rtda.JVMFrame;
import rtda.heap.*;
import tool.Tool;

public class INVOKE_SPECIAL extends Index16Instruction {
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

        /*
        todo:if need -1
         */
        JVMObject ref = frame.getOperandStack().getRefFromTop(resolvedMethod.argSlotCount() - 1);
        if(ref == null){
            Tool.panic("java.lang.NullPointerException");
        }

        if(resolvedMethod.IsProtected() &&
                resolvedMethod.klass.isSuperClassOf(currentClass)
                && !resolvedMethod.klass.getPackageName().equals(currentClass.getPackageName())
                && ref.klass != currentClass
                && !ref.klass.isSubClassOf(currentClass)){
            Tool.panic("java.lang.IllegalAccessError");
        }

        JVMMethod methodToBeInvoked = resolvedMethod;
        if(currentClass.IsSuper()
                && resolvedClass.isSuperClassOf(currentClass)
            && !resolvedMethod.name.equals("<init>")){
            methodToBeInvoked = JVMMethodRef.lookupMethodInClass(currentClass.getSuperclass(),
                    methodRef.getName(), methodRef.getDescriptor());
        }

        if(methodToBeInvoked == null || methodToBeInvoked.IsAbstract()){
            Tool.panic("java.lang.AbstractMethodError");
        }
        Method_invoke_logic.InvokeMethod(frame, methodToBeInvoked);
    }
}
