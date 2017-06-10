package rtda;

public class JVMFrame {
    public JVMFrame lower;
    LocalVars localVars;
    OperandStack operandStack;
    JVMThread thread;

    public int getNextPc() {
        return nextPc;
    }

    public void setNextPc(int nextPc) {
        this.nextPc = nextPc;
    }

    int nextPc;

    public JVMFrame(long maxLocals, long maxStack) {
        this.localVars = new LocalVars(maxLocals);
        this.operandStack = new OperandStack(maxStack);
        this.lower = null;
    }

    public JVMFrame(JVMThread thread, long maxLocals, long maxStack) {
        this(maxLocals, maxStack);
        this.thread = thread;
    }

    public JVMFrame getLower() {
        return lower;
    }

    public void setLower(JVMFrame lower) {
        this.lower = lower;
    }

    public LocalVars getLocalVars() {
        return localVars;
    }

    public void setLocalVars(LocalVars localVar) {
        this.localVars = localVar;
    }

    public OperandStack getOperandStack() {
        return operandStack;
    }

    public void setOperandStack(OperandStack operandStack) {
        this.operandStack = operandStack;
    }

    public JVMThread getThread() {
        return thread;
    }
}
