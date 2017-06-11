package classfile;

import tool.Tool;

public abstract class ConstantInfo {
    final int CONSTANT_Class              = 7;
    final int CONSTANT_Fieldref           = 9;
    final int CONSTANT_Methodref          = 10;
    final int CONSTANT_InterfaceMethodref = 11;
    final int CONSTANT_String             = 8;
    final int CONSTANT_Integer            = 3;
    final int CONSTANT_Float              = 4;
    final int CONSTANT_Long               = 5;
    final int CONSTANT_Double             = 6;
    final int CONSTANT_NameAndType        = 12;
    final int CONSTANT_Utf8               = 1;
    final int CONSTANT_MethodHandle       = 15;
    final int CONSTANT_MethodType         = 16;
    final int CONSTANT_InvokeDynamic      = 18;

    abstract public void readInfo(ClassReader cr);

    ConstantInfo readConstantInfo(ClassReader reader, ConstantPool cp){
       byte tag = reader.readUint8();
       int tag_U = Byte.toUnsignedInt(tag);
       ConstantInfo c = newConstantInfo(tag_U, cp);
       c.readInfo(reader);
       return c;
    }

    ConstantInfo newConstantInfo(int tag, ConstantPool cp){
        switch (tag) {
            case CONSTANT_Integer:
                return new ConstantIntegerInfo();
            case CONSTANT_Float:
                return new ConstantFloatInfo();
            case CONSTANT_Long:
                return new ConstantLongInfo();
            case CONSTANT_Double:
                return new ConstantDoubleInfo();
            case CONSTANT_Utf8:
                return new ConstantUtf8Info();
            case CONSTANT_String:
                return new ConstantStringInfo(cp);
            case CONSTANT_Class:
                return new ConstantClassInfo(cp);
            case CONSTANT_Fieldref:
                return new ConstantFieldrefInfo(cp);
            case CONSTANT_Methodref:
                return new ConstantMethodrefInfo(cp);
            case CONSTANT_InterfaceMethodref:
                return new ConstantInterfaceMethodrefInfo(cp);
            case CONSTANT_NameAndType:
                return new ConstantNameAndTypeInfo();
            case CONSTANT_MethodType:
                return new ConstantMethodTypeInfo();
            case CONSTANT_MethodHandle:
                return new ConstantMethodHandleInfo();
            case CONSTANT_InvokeDynamic:
                return new ConstantInvokeDynamicInfo();
            default:
                Tool.panic("java.lang.ClassFormatError: constant pool tag!");
        }
        return null;
    }

}
