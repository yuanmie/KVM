package classfile;

public class ExceptionsAttribute implements AttributeInfo {
    short[] exceptionIndexTable;
    @Override
    public void readInfo(ClassReader cr) {
        this.exceptionIndexTable = cr.readUint16s();
    }

    public short[] getExceptionIndexTable(){
        return this.exceptionIndexTable;
    }
}
