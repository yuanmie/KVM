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

    public Interpret(JVMMethod method){
        JVMThread thread = new JVMThread();
        JVMFrame frame = thread.newJVMFrame(method);
        thread.pushFrame(frame);
        loop(thread, method.getCode());
    }
    public Interpret(MemberInfo methodInfo) {
        this.codeAttr = methodInfo.getCodeAttribute();
        int maxLocals = codeAttr.getMaxLocals();
        int maxStack = codeAttr.getMaxStack();
        byte[] bytecode = codeAttr.getCode();

        JVMThread thread = new JVMThread();
        JVMFrame frame = thread.newJVMFrame(maxLocals, maxStack);
        thread.pushFrame(frame);
        loop(thread, bytecode);
    }

    private void loop(JVMThread thread, byte[] bytecode) {
        JVMFrame frame = thread.popFrame();
        ByteCodeReader reader = new ByteCodeReader();
        while(true){
            int pc = frame.getNextPc();
            thread.setPc(pc);
            reader.reset(bytecode, pc);
            int opcode = reader.readUint8();
            //return instruction not implement
            if(opcode == 0xb2){
                catchErr(frame);
                return;
            }
            Instruction inst = Factory.newInstruction(opcode);
            inst.fetchOperands(reader);
            int nextPc = reader.getPc();
            frame.setNextPc(nextPc);
            System.out.printf("pc:%2d inst:%s \n", pc, inst);
            inst.execute(frame);
        }
    }

    public void catchErr(JVMFrame frame){
        System.out.printf("Localvars:%s\n", frame.getLocalVars());
        System.out.printf("OperandStack:%s\n", frame.getOperandStack());
        Tool.panic("fuck you");
    }

}
