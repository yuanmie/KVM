import classfile.CodeAttribute;
import classfile.MemberInfo;
import instructions.Factory;
import instructions.base.ByteCodeReader;
import instructions.base.Index16Instruction;
import instructions.base.Instruction;
import rtda.JVMFrame;
import rtda.JVMThread;
import rtda.heap.JVMMethod;
import tool.Tool;

public class Interpret {
    CodeAttribute codeAttr;

    public Interpret(JVMMethod method, boolean log){
        JVMThread thread = new JVMThread();
        JVMFrame frame = thread.newJVMFrame(method);
        thread.pushFrame(frame);
        loop(thread, log);
    }
    public Interpret(MemberInfo methodInfo) {
        this.codeAttr = methodInfo.getCodeAttribute();
        int maxLocals = codeAttr.getMaxLocals();
        int maxStack = codeAttr.getMaxStack();
        byte[] bytecode = codeAttr.getCode();

        JVMThread thread = new JVMThread();
        JVMFrame frame = thread.newJVMFrame(maxLocals, maxStack);
        thread.pushFrame(frame);

    }

    private void loop(JVMThread thread, boolean log) {
        ByteCodeReader reader = new ByteCodeReader();
        while(true){
            JVMFrame frame = thread.currentFrame();
            int pc = frame.getNextPc();
            thread.setPc(pc);
            reader.reset(frame.getMethod().getCode(), pc);
            int opcode = reader.readUint8();

            Instruction inst = Factory.newInstruction(opcode);
            inst.fetchOperands(reader);
            int nextPc = reader.getPc();
            frame.setNextPc(nextPc);
            if(log){
                logInstruction(frame, inst);
            }
            inst.execute(frame);
            if(thread.isStackEmpty()){
                break;
            }
        }
    }

    private void logInstruction(JVMFrame frame, Instruction inst) {
        JVMMethod method = frame.getMethod();
        String className = method.klass.getName();
        int pc = frame.getThread().getPc();
        System.out.printf("%s.%s() %d  %s %s\n", className, method.name
        ,pc, inst, inst);
    }

    public void catchErr(JVMFrame frame){
        System.out.printf("Localvars:%s\n", frame.getLocalVars());
        System.out.printf("OperandStack:%s\n", frame.getOperandStack());
        Tool.panic("fuck you");
    }

}
