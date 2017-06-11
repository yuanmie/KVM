package classfile;


public class MemberInfo {
    ConstantPool cp;
    short accessFlags;
    short nameIndex;
    short descriptorIndex;
    AttributeInfo[] attributes;

    public ConstantPool getCp() {
        return cp;
    }

    public short getAccessFlags() {
        return accessFlags;
    }

    public short getNameIndex() {
        return nameIndex;
    }

    public short getDescriptorIndex() {
        return descriptorIndex;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }

    public static MemberInfo[] readMembers(ClassReader cr, ConstantPool cp){
        short memberCount = cr.readUint16();
        int memberCount_U = Short.toUnsignedInt(memberCount);
        MemberInfo[] members = new MemberInfo[memberCount_U];
        for(int i = 0; i < members.length; i++){
            members[i] = readMember(cr, cp);
        }

        return members;
    }

    public static MemberInfo readMember(ClassReader cr, ConstantPool cp){
        MemberInfo mi = new MemberInfo();
        mi.cp = cp;
        mi.accessFlags = cr.readUint16();
        mi.nameIndex = cr.readUint16();
        mi.descriptorIndex = cr.readUint16();
        mi.attributes = AttributeInfo.readAttributes(cr, cp);
        return mi;
    }

    public String Name(){
        return this.cp.getUtf8(this.nameIndex);
    }

    public String Desciptor(){
        return this.cp.getUtf8(this.descriptorIndex);
    }

    public CodeAttribute getCodeAttribute(){
        for(AttributeInfo attr: this.attributes){
            if(attr instanceof CodeAttribute){
                return (CodeAttribute) attr;
            }
        }
        return null;
    }

    public ConstantValueAttribute ConstantValueAttribute() {
        for(AttributeInfo attrInfo : this.attributes){
            if(attrInfo instanceof ConstantValueAttribute){
                return (ConstantValueAttribute) attrInfo;
            }
        }
        return null;
    }
}
