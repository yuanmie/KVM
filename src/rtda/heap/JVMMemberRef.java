package rtda.heap;

import classfile.ConstantClassInfo;
import classfile.ConstantMemberrefInfo;

public class JVMMemberRef extends JVMSymRef{
    String name;
    String descriptor;

    public JVMMemberRef() {
    }

    public void copyMemberRefInfo(ConstantMemberrefInfo info){
        this.className = info.ClassName();
        String[] nameAndDescriptor = info.NameAndDescriptor();
        this.name = nameAndDescriptor[0];
        this.descriptor = nameAndDescriptor[1];
    }

    public String getName() {
        return name;
    }

    public String getDescriptor() {
        return descriptor;
    }
}
