package rtda.heap;

import classfile.MemberInfo;

public class JVMClassMember {
    public int accessFlag;
    public String name;
    public String descriptor;
    public JVMClass klass;

    public void copyMemberInfo(MemberInfo mi){
        this.accessFlag = mi.getAccessFlags();
        this.name = mi.Name();
        this.descriptor = mi.Desciptor();
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

    public boolean IsStatic(){
        return (this.accessFlag&ACCESSFLAG.ACC_STATIC) != 0;
    }

    public boolean isAccessibleTo(JVMClass d) {
        if(this.IsPublic()){
            return true;
        }
        JVMClass c = this.klass;
        if(this.IsProtected()){
            return d == c || d.isSubClassOf(c)
                    || c.getPackageName().equals(d.getPackageName());
        }

        if(!this.isPrivate()){
            return c.getPackageName().equals(d.getPackageName());
        }

        return d == c;
    }

    public boolean IsProtected() {
        return (this.accessFlag&ACCESSFLAG.ACC_PROTECTED) != 0;

    }

    public boolean isPrivate() {
        return (this.accessFlag&ACCESSFLAG.ACC_PRIVATE) != 0;

    }
}
