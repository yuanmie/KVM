package rtda;

public class JVMThread {
    int pc;
    JVMStack stack;

    public JVMThread(int pc, JVMStack stack) {
        this.pc = pc;
        this.stack = stack;
    }

    public JVMThread(JVMStack stack){
        this.stack = stack;
    }

    public JVMThread(){
        this(new JVMStack(1024));
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public JVMStack getStack() {
        return stack;
    }

    public void setStack(JVMStack stack) {
        this.stack = stack;
    }

    public void pushFrame(JVMFrame frame){
        this.stack.push(frame);
    }

    public JVMFrame popFrame(){
        return this.stack.pop();
    }

    public JVMFrame currentFrame(){
        return this.stack.top();
    }

    public JVMFrame newJVMFrame(int maxLocals, int maxStack) {
        return new JVMFrame(this, maxLocals, maxStack);
    }
}
