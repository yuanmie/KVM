package classfile;

public class LocalVariableTableAttribute implements AttributeInfo {
    LocalVariableTableEntry[] lineNumberTable;

    @Override
    public void readInfo(ClassReader cr) {
        int localVariableTableLength = Short.toUnsignedInt(cr.readUint16());
        this.lineNumberTable = new LocalVariableTableEntry[localVariableTableLength];
        for(int i = 0; i < localVariableTableLength; i++){
            lineNumberTable[i] = new LocalVariableTableEntry(
                    Short.toUnsignedInt(cr.readUint16()),
                    Short.toUnsignedInt(cr.readUint16()),
                    Short.toUnsignedInt(cr.readUint16()),
                    Short.toUnsignedInt(cr.readUint16()),
                    Short.toUnsignedInt(cr.readUint16())
            );

        }

    }

    private class LocalVariableTableEntry {
        int startPc;
        int length;
        int nameIndex;
        int descriptorIndex;
        int index;

        public LocalVariableTableEntry(int startPc, int length, int nameIndex, int descriptorIndex, int index) {
            this.startPc = startPc;
            this.length = length;
            this.nameIndex = nameIndex;
            this.descriptorIndex = descriptorIndex;
            this.index = index;
        }
    }
}
