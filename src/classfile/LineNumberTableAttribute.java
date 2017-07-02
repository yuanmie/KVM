package classfile;

public class LineNumberTableAttribute implements AttributeInfo {
    LineNumberTableEntry[] lineNumberTable;

    @Override
    public void readInfo(ClassReader cr) {
        int lineNumberTableLength = Short.toUnsignedInt(cr.readUint16());
        this.lineNumberTable = new LineNumberTableEntry[lineNumberTableLength];
        for(int i = 0; i < lineNumberTableLength; i++){
            lineNumberTable[i] = new LineNumberTableEntry(
                    Short.toUnsignedInt(cr.readUint16()),
                    Short.toUnsignedInt(cr.readUint16())
            );

        }

    }

    public int getLineNumber(int pc) {
        for(int i = lineNumberTable.length - 1; i >= 0; i--){
            LineNumberTableEntry entry = this.lineNumberTable[i];
            if(pc >= entry.startPc){
                return entry.lineNumber;
            }
        }
        return -1;
    }

    private class LineNumberTableEntry {
        int startPc;
        int lineNumber;

        public LineNumberTableEntry(int startPc, int lineNumber) {
            this.startPc = startPc;
            this.lineNumber = lineNumber;
        }
    }
}
