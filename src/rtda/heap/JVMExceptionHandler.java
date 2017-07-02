package rtda.heap;

public class JVMExceptionHandler {
    private int startPc;
    private int endPc;
    private int handlerPc;
    private JVMClassRef catchType;

    public JVMExceptionHandler(int startPc, int endPc, int handlerPc, JVMClassRef catchType) {
        this.startPc = startPc;
        this.endPc = endPc;
        this.handlerPc = handlerPc;
        this.catchType = catchType;
    }

    public int getStartPc() {
        return startPc;
    }

    public void setStartPc(int startPc) {
        this.startPc = startPc;
    }

    public int getEndPc() {
        return endPc;
    }

    public void setEndPc(int endPc) {
        this.endPc = endPc;
    }

    public int getHandlerPc() {
        return handlerPc;
    }

    public void setHandlerPc(int handlerPc) {
        this.handlerPc = handlerPc;
    }

    public JVMClassRef getCatchType() {
        return catchType;
    }

    public void setCatchType(JVMClassRef catchType) {
        this.catchType = catchType;
    }
}
