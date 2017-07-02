package classfile;

public class CodeAttribute implements AttributeInfo {
    ConstantPool cp;
    int maxStack;
    int maxLocals;
    byte[] code;

    public ConstantPool getCp() {
        return cp;
    }

    public void setCp(ConstantPool cp) {
        this.cp = cp;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public void setMaxStack(int maxStack) {
        this.maxStack = maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public void setMaxLocals(int maxLocals) {
        this.maxLocals = maxLocals;
    }

    public byte[] getCode() {
        return code;
    }

    public void setCode(byte[] code) {
        this.code = code;
    }

    public ExceptionTableEntry[] getExceptionTable() {
        return exceptionTable;
    }

    public void setExceptionTable(ExceptionTableEntry[] exceptionTable) {
        this.exceptionTable = exceptionTable;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributeInfo[] attributes) {
        this.attributes = attributes;
    }

    ExceptionTableEntry[] exceptionTable;
    AttributeInfo[] attributes;

    public CodeAttribute(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader cr) {
        this.maxStack = Short.toUnsignedInt(cr.readUint16());
        this.maxLocals = Short.toUnsignedInt(cr.readUint16());
        long codeLength = Integer.toUnsignedLong(cr.readUint32());
        this.code = cr.readBytes(codeLength);
        this.exceptionTable = readExceptionTable(cr);
        this.attributes =AttributeInfo.readAttributes(cr, this.cp);
    }

    ExceptionTableEntry[] readExceptionTable(ClassReader cr){
        int exceptionTableLength = Short.toUnsignedInt(cr.readUint16());
        ExceptionTableEntry[] exceptionTable = new ExceptionTableEntry[exceptionTableLength];
        for(int i = 0; i < exceptionTableLength; i++){
            exceptionTable[i] = new ExceptionTableEntry(
                    Short.toUnsignedInt(cr.readUint16()),
                    Short.toUnsignedInt(cr.readUint16()),
                    Short.toUnsignedInt(cr.readUint16()),
                    Short.toUnsignedInt(cr.readUint16())
            );
        }
        return exceptionTable;
    }

    public LineNumberTableAttribute getLineNumberTableAttribute() {
        for(AttributeInfo attr : this.attributes){
            if(attr instanceof LineNumberTableAttribute){
                return (LineNumberTableAttribute) attr;
            }
        }
        return null;
    }
}
