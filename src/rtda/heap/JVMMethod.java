package rtda.heap;

import classfile.CodeAttribute;
import classfile.MemberInfo;

public class JVMMethod extends JVMClassMember{
    int maxStack;
    int maxLocals;
    byte[] code;

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public static JVMMethod[] newMethods(JVMClass klass, MemberInfo[] memberInfos){
        int length = memberInfos.length;
        JVMMethod[] methods = new JVMMethod[length];
        for(int i = 0; i < length; i++){
            methods[i] = new JVMMethod();
            methods[i].klass = klass;
            methods[i].copyMemberInfo(memberInfos[i]);
            methods[i].copyAttributes(memberInfos[i]);

        }

        return methods;
    }

    private void copyAttributes(MemberInfo memberInfo) {
        CodeAttribute codeAttribute = memberInfo.getCodeAttribute();
        if(codeAttribute != null){
            this.maxLocals = codeAttribute.getMaxLocals();
            this.maxStack = codeAttribute.getMaxStack();
            this.code = codeAttribute.getCode();
        }
    }

    public byte[] getCode() {
        return code;
    }
}
