package rtda.heap;

import classfile.CodeAttribute;
import classfile.MemberInfo;

public class JVMMethod extends JVMClassMember{
    int maxStack;
    int maxLocals;
    byte[] code;
    int argSlotCount;
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
            JVMMethodDescriptor parsedDescriptor = parseMethodDescriptor(methods[i].descriptor);
            methods[i].calsArgSlotCount(parsedDescriptor);
            if(methods[i].isNative()){
                methods[i].injetCodeAttribute(parsedDescriptor.returnType);
            }
        }

        return methods;
    }

    private void injetCodeAttribute(String returnType) {
        this.maxStack = 4;
        this.maxLocals = this.argSlotCount;
        switch (returnType.charAt(0)){
            case 'V': this.code = new byte[]{(byte)0xfe, (byte)0xb1};break;
            case 'D': this.code = new byte[]{(byte)0xfe, (byte)0xaf};break;
            case 'F': this.code = new byte[]{(byte)0xfe, (byte)0xae};break;
            case 'J': this.code = new byte[]{(byte)0xfe, (byte)0xad};break;
            case 'L' : case '[': this.code = new byte[]{(byte)0xfe, (byte)0xb0};break;
            default:
                this.code = new byte[]{(byte)0xfe, (byte)0xac};
        }
    }

    private void calsArgSlotCount(JVMMethodDescriptor parsedDescriptor) {
        for(String paramType : parsedDescriptor.getParameterTypes()){
            this.argSlotCount++;

            if(paramType.equals("J") || paramType.equals("D")){
                this.argSlotCount++;
            }
        }

        if(!this.IsStatic()){
            ++this.argSlotCount;
        }
    }

    private static JVMMethodDescriptor parseMethodDescriptor(String descriptor) {
        JVMMethodDescriptorParser mdp = new JVMMethodDescriptorParser(descriptor);
        JVMMethodDescriptor methodDescriptor = mdp.parse();
        return methodDescriptor;
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

    public int argSlotCount() {
        return this.argSlotCount;
    }

    public boolean isNative() {
        return (this.accessFlag&ACCESSFLAG.ACC_NATIVE) != 0;

    }
}
