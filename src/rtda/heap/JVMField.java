package rtda.heap;

import classfile.ConstantValueAttribute;
import classfile.MemberInfo;
import rtda.JVMFrame;

public class JVMField extends JVMClassMember{
    public int slotId;
    public int constValueIndex;
    public static JVMField[] newFields(JVMClass klass, MemberInfo[] memberInfos){
        int length = memberInfos.length;
        JVMField[] fields = new JVMField[length];
        for(int i = 0; i < length; i++){
            fields[i] = new JVMField();
            fields[i].klass = klass;
            fields[i].copyMemberInfo(memberInfos[i]);
            fields[i].copyAttributes(memberInfos[i]);
        }
        return fields;
    }

    private void copyAttributes(MemberInfo memberInfo) {
        ConstantValueAttribute val = memberInfo.ConstantValueAttribute();
        if(val != null){
            this.constValueIndex = val.ConstantValueIndex();
        }
    }

    public boolean IsPublic()  {
        return (this.accessFlag&ACCESSFLAG.ACC_PUBLIC) != 0;
    }
    public boolean IsFinal()  {
        return 0 != (this.accessFlag&ACCESSFLAG.ACC_FINAL);
    }
    public boolean IsSuper()  {
        return 0 != (this.accessFlag&ACCESSFLAG.ACC_SUPER);
    }
    public boolean IsInterface()  {
        return 0 != (this.accessFlag&ACCESSFLAG.ACC_INTERFACE);
    }
    public boolean IsAbstract()  {
        return 0 != (this.accessFlag&ACCESSFLAG.ACC_ABSTRACT);
    }
    public boolean IsSynthetic()  {
        return 0 != (this.accessFlag&ACCESSFLAG.ACC_SYNTHETIC);
    }
    public boolean IsAnnotation()  {
        return 0 != (this.accessFlag&ACCESSFLAG.ACC_ANNOTATION);
    }
    public boolean IsEnum()  {
        return 0 != (this.accessFlag&ACCESSFLAG.ACC_ENUM);
    }

    public boolean isLongOrDouble() {
        return this.descriptor.equals("J")
                || this.descriptor.equals("D");
    }
}
