package instructions.references;

import instructions.base.Index16Instruction;
import rtda.JVMFrame;
import rtda.OperandStack;
import rtda.heap.JVMConstant;
import rtda.heap.JVMConstantPool;
import tool.Tool;

public class LDC2W extends Index16Instruction{
    @Override
    public void execute(JVMFrame frame) {
        OperandStack stack = frame.getOperandStack();
        JVMConstantPool cp = frame.getMethod().klass.getCp();
        JVMConstant c = cp.getContant(index);

        switch (c.getType()){
            case 3:
                stack.pushLong(c.getLongVal());
                break;
            case 4:
                stack.pushDouble(c.getDoubleVal());
                break;
            default:
                Tool.panic("todl:ldc");
        }
    }
}
