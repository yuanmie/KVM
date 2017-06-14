package instructions.references;

import instructions.base.Index8Instruction;
import rtda.JVMFrame;
import rtda.OperandStack;
import rtda.heap.*;
import tool.Tool;

public class LDC extends Index8Instruction{
    @Override
    public void execute(JVMFrame frame) {
        _ldc(frame, this.index);
    }

    public static void _ldc(JVMFrame frame, int index) {
        OperandStack stack = frame.getOperandStack();
        JVMClass klass = frame.getMethod().klass;
        JVMConstantPool cp = frame.getMethod().klass.getCp();
        JVMConstant c = cp.getContant(index);

        switch (c.getType()){
            case 1:
                stack.pushInt(c.getIntVal());
                break;
            case 2:
                stack.pushFloat(c.getFloatVal());
                break;
            case JVMConstant.ISSTR:
                JVMObject o = JVMString.newJVMString(klass.getLoader(), c.getStrVal());
                stack.pushRef(o);
                break;
            default:
                Tool.panic("todl:ldc");
        }
    }
}
