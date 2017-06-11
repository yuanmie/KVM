package instructions.references;

import instructions.base.Index16Instruction;
import rtda.JVMFrame;
import rtda.OperandStack;
import rtda.heap.JVMConstantPool;
import rtda.heap.JVMMemberRef;
import tool.Tool;

public class INVOKE_VIRTUAL extends Index16Instruction {
    @Override
    public void execute(JVMFrame frame) {
        JVMConstantPool cp = frame.getMethod().klass.getCp();
        JVMMemberRef memberRef = cp.getContant(this.index).getMethodRef();
        if(memberRef.getName().equals("println")){
            OperandStack stack = frame.getOperandStack();
            switch (memberRef.getDescriptor()){
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
                    Tool.panic("println: " + memberRef.getDescriptor());
            }

            stack.popRef();
        }
    }
}
