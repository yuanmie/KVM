package classfile;


public interface AttributeInfo {
    void readInfo(ClassReader cr);
    static AttributeInfo[] readAttributes(ClassReader cr, ConstantPool cp){
        int attributesCount = Short.toUnsignedInt(cr.readUint16());
        AttributeInfo[] attributes = new AttributeInfo[attributesCount];
        for(int i = 0; i < attributesCount; i++){
            attributes[i] = readAttribute(cr, cp);
        }
        return attributes;
    }

    static AttributeInfo readAttribute(ClassReader cr, ConstantPool cp){
        int attrNameIndex = Short.toUnsignedInt(cr.readUint16());
        String attrName = cp.getUtf8(attrNameIndex);
        long attrLen = Integer.toUnsignedLong(cr.readUint32());
        AttributeInfo attrInfo = newAttributeInfo(attrName, attrLen, cp);
        attrInfo.readInfo(cr);
        return attrInfo;
    }

    static AttributeInfo newAttributeInfo(String attrName, long attrLen,
                                           ConstantPool cp){
        switch (attrName){
            case "Code": return new CodeAttribute(cp);
            case "ConstantValue": return new ConstantValueAttribute();
            case "Deprecated" : return new DeprecatedAttribute();
            case "Exceptions" : return new ExceptionsAttribute();
            case "LineNumberTable" : return new LineNumberTableAttribute();
            case "LocalVariableTable" : return new LocalVariableTableAttribute();
            case "SourceFile" : return new SourceFileAttribute(cp);
            case "Synthetic" : return new SyntheticAttribute();
            default: return new UnparsedAttribute(attrName, attrLen, null);
        }

    }




}
