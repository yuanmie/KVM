package rtda.heap;

import classfile.ConstantInterfaceMethodrefInfo;
import classfile.MemberInfo;

public class JVMInterfaceMethodRef extends JVMMemberRef{
    public JVMInterfaceMethodRef(JVMConstantPool cp, ConstantInterfaceMethodrefInfo info) {
        this.cp = cp;
        this.copyMemberRefInfo(info);
    }
}
