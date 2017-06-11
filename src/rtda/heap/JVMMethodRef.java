package rtda.heap;

import classfile.ConstantMethodrefInfo;

public class JVMMethodRef extends JVMMemberRef{
    public JVMMethodRef(JVMConstantPool cp, ConstantMethodrefInfo info) {
        this.cp = cp;
        this.copyMemberRefInfo(info);
    }
}
