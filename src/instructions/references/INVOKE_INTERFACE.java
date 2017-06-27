package instructions.references;

import instructions.base.ByteCodeReader;

import instructions.base.Instruction;
import instructions.base.Method_invoke_logic;
import rtda.JVMFrame;
import rtda.heap.*;
import tool.Tool;

public class INVOKE_INTERFACE implements Instruction {
    int index;
    @Override
    public void fetchOperands(ByteCodeReader reader) {
        this.index = reader.readUint16();
        reader.readUint8();
        reader.readUint8();
    }

    @Override
    public void execute(JVMFrame frame) {
        JVMClass currentClass = frame.getMethod().klass;
        JVMConstantPool cp = currentClass.getCp();
        JVMInterfaceMethodRef methodRef = cp.getContant(this.index).getInterfaceMethodRef();
        JVMMethod resolvedMethod = methodRef.resolvedMethod();

        if(resolvedMethod.IsStatic() || resolvedMethod.isPrivate()){
            Tool.panic("java.lang.IncomatibleClassChangeError");
        }

        JVMObject ref = frame.getOperandStack().getRefFromTop(resolvedMethod.argSlotCount() - 1);
        if(ref == null){
            Tool.panic("java.lang.NullPointerException");
        }

//        if(!ref.klass.isImplements(methodRef.resolvedClass())){
//            Tool.panic("java.lang.IncompatibleClassChangeError");
//        }

        JVMMethod methodToBeInvoked = JVMMethodRef.lookupMethodInClass(ref.klass,
                    methodRef.getName(), methodRef.getDescriptor());


        if(!methodToBeInvoked.IsPublic()){
            Tool.panic("java.lang.IllegalAccessError");
        }
        Method_invoke_logic.InvokeMethod(frame, methodToBeInvoked);
    }
}
