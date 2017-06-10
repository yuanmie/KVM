package classfile;
import tool.Tool;
public class ClassFile {
    public int magic;
    public short minorVersion;
    public short majorVersion;
    public ConstantPool constantPool;
    public short accessFlag;
    public short thisClass;
    public short superClass;
    public short[] interfaces;
    public MemberInfo[] fields;
    public MemberInfo[] methods;
    public AttributeInfo[] attributes;

    public ClassFile(byte[] data){
        parse(data);
    }

    public  ClassFile parse(byte[] data){
        ClassReader cr = new ClassReader(data);
        read(cr);
        return this;
    }

    public  void read(ClassReader reader){
        readAndCheckMagic(reader);
        readAndCheckVersion(reader);
        this.constantPool = ConstantPool.readConstantPool(reader);
        this.accessFlag = reader.readUint16();
        this.thisClass = reader.readUint16();
        this.superClass = reader.readUint16();
        this.interfaces = reader.readUint16s();
        this.fields = MemberInfo.readMembers(reader, this.constantPool);
        this.methods = MemberInfo.readMembers(reader, this.constantPool);
        this.attributes = AttributeInfo.readAttributes(reader, this.constantPool);
    }

    private void readAndCheckVersion(ClassReader reader) {
        this.minorVersion = reader.readUint16();
        this.majorVersion = reader.readUint16();
        switch (this.majorVersion){
            case 45:
                return;
            case 46:case 47:case 48:case 49:case 50:
            case 51:case 52:
                if(this.minorVersion == 0) return;
        }
        Tool.panic("java.lang.UpsupportedClassVersionError!");
    }

    private void readAndCheckMagic(ClassReader reader) {
        int magic = reader.readUint32();
        if(magic != 0xCAFEBABE){
            Tool.panic("java.lang.ClassFormatError: magic!");
        }
    }

    public String className(){
        return this.constantPool.getClassName(this.thisClass);
    }

    public String superClassName(){
        short sc = this.superClass;
        /*
        需要运算，转为无符号
         */

        int sc_U = Short.toUnsignedInt(sc);
        if(sc_U > 0){
            return this.constantPool.getClassName(this.superClass);
        }
        return ""; //superclass is object
    }

    public String[] InterfaceNames(){

        String[] interfaceNames = new String[this.interfaces.length];
        for(int i = 0; i < this.interfaces.length; i++){
            interfaceNames[i] = this.constantPool.getClassName(this.interfaces[i]);
        }

        return interfaceNames;
    }

    public int getMagic() {
        return magic;
    }

    public short getMinorVersion() {
        return minorVersion;
    }

    public short getMajorVersion() {
        return majorVersion;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public short getAccessFlag() {
        return accessFlag;
    }

    public short getThisClass() {
        return thisClass;
    }

    public short getSuperClass() {
        return superClass;
    }

    public short[] getInterfaces() {
        return interfaces;
    }

    public MemberInfo[] getFields() {
        return fields;
    }

    public MemberInfo[] getMethods() {
        return methods;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }
}
