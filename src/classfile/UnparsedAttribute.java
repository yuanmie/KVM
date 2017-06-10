package classfile;

public class UnparsedAttribute implements AttributeInfo {
    String name;
    long length;
    byte[] info;
    public UnparsedAttribute(String attrName, long attrLen, byte[] o) {
        this.name = attrName;
        this.length = attrLen;
        this.info = o;
    }


    @Override
    public void readInfo(ClassReader cr) {
        this.info = cr.readBytes(this.length);
    }
}
