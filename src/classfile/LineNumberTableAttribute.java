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

    private class LineNumberTableEntry {
        int startPc;
        int lineNumber;

        public LineNumberTableEntry(int startPc, int lineNumber) {
            this.startPc = startPc;
            this.lineNumber = lineNumber;
        }
    }
}
