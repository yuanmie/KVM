package rtda.heap;

import classfile.ExceptionTableEntry;

public class JVMExceptionTable {
    private JVMExceptionHandler[] table;
    public JVMExceptionTable(ExceptionTableEntry[] exceptionTable, JVMConstantPool cp) {
        table = new JVMExceptionHandler[exceptionTable.length];
        for(int i = 0; i < exceptionTable.length; i++){
            ExceptionTableEntry entry = exceptionTable[i];
            table[i] = new JVMExceptionHandler(entry.getStartPc(),
                    entry.getEndPc(), entry.getHandlerPc(), getCatchType(entry.getCatchType(), cp));
        }
    }

    private JVMClassRef getCatchType(int index, JVMConstantPool cp) {
        if(index == 0 ){
            return null;
        }
        return cp.getContant(index).getClassRef();
    }

    public JVMExceptionHandler findExceptionHandler(JVMClass exClass, int pc) {
        for(JVMExceptionHandler handler : this.table){
            if(pc >= handler.getStartPc() && pc <= handler.getEndPc()){
                if(handler.getCatchType() == null){
                    return handler;
                }
                JVMClass catchClass = handler.getCatchType().resolvedClass();
                if(catchClass == exClass || catchClass.isSubClassOf(exClass)){
                    return handler;
                }
            }
        }
        return null;
    }
}
