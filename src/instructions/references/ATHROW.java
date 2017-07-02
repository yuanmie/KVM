package instructions.references;

import instructions.base.NoOperandsInstruction;
import rtda.JVMFrame;
import rtda.JVMThread;
import rtda.OperandStack;
import rtda.StackTraceElement;
import rtda.heap.JVMObject;
import tool.Tool;

public class ATHROW extends NoOperandsInstruction{
    @Override
    public void execute(JVMFrame frame) {
        JVMObject ex = frame.getOperandStack().popRef();
        if(ex == null){
            Tool.panic("java.lang.NullPointerExcetpion");
        }
        JVMThread thread = frame.getThread();
        if(!findAndGotoExceptionHandler(thread, ex)){
            handleUncaughtException(thread, ex);
        }
    }

    private void handleUncaughtException(JVMThread thread, JVMObject ex) {
        thread.clearStack();
        JVMObject jmsg = ex.getRefVar("detailMessage", "Ljava/lang/String;");
        String msg = toJVMString(jmsg);
        System.err.println(ex.klass.javaName() + ": " + msg);
        StackTraceElement[] stes = (StackTraceElement[]) ex.extra;
        for(int i = 0; i < stes.length; i++){
            System.err.println(stes[i]);
        }
    }

    private boolean findAndGotoExceptionHandler(JVMThread thread, JVMObject ex) {
        while(true){
            JVMFrame frame = thread.currentFrame();
            int pc = frame.getNextPc() - 1;
            int handlerPc = frame.getMethod().findExceptionHandler(ex.klass, pc);
            if(handlerPc > 0){
                OperandStack stack = frame.getOperandStack();
                stack.clear();
                stack.pushRef(ex);
                frame.setNextPc(handlerPc);
                return true;
            }
            thread.popFrame();
            if(thread.isStackEmpty()){
                break;
            }
        }
        return false;
    }

    private String toJVMString(JVMObject jstr) {
        JVMObject o = jstr.getRefVar("value", "[C");
        return new String(o.Chars());
    }
}
