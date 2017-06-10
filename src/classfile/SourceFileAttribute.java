package classfile;

public class SourceFileAttribute implements AttributeInfo {
    ConstantPool cp;
    int sourceFileIndex;
    public SourceFileAttribute(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader cr) {
        this.sourceFileIndex = Short.toUnsignedInt(cr.readUint16());
    }

    public String FileName(){
        return this.cp.getUtf8(this.sourceFileIndex);
    }
}
