package rtda.heap;

import classfile.*;
import tool.Tool;

public class JVMConstantPool {
    JVMClass klass;
    JVMConstant[] consts;

    public JVMConstant getContant(int index){
        JVMConstant constant = this.consts[index];
        if(constant != null){
            return constant;
        }
        Tool.panic("no constant in there!");
        return null;
    }

    public JVMConstantPool(JVMClass klass, ConstantPool cp){
        int length = cp.length();
        this.consts = new JVMConstant[length];
        this.klass = klass;

        for(int i = 1; i < length; i++){
            ConstantInfo ci = cp.getConstantInfo(i);
            JVMConstant constant = new JVMConstant();
            this.consts[i] = constant;
            if(ci instanceof ConstantIntegerInfo){
                ConstantIntegerInfo intInfo = (ConstantIntegerInfo) ci;
                this.consts[i].setIntVal(intInfo.value());
            }else if(ci instanceof ConstantFloatInfo){
                ConstantFloatInfo Info = (ConstantFloatInfo) ci;
                this.consts[i].setFloatVal(Info.value());
            }
            else if(ci instanceof ConstantLongInfo){
                ConstantLongInfo info = (ConstantLongInfo) ci;
                this.consts[i].setLongVal(info.value());
                i++;
            }
            else if(ci instanceof ConstantDoubleInfo){
                ConstantDoubleInfo info = (ConstantDoubleInfo) ci;
                this.consts[i].setDoubleVal(info.value());
                i++;
            }
            else if(ci instanceof ConstantStringInfo){
                ConstantStringInfo info = (ConstantStringInfo) ci;
                this.consts[i].setStrVal(info.string());
            }
            else if(ci instanceof ConstantClassInfo){
                ConstantClassInfo info = (ConstantClassInfo) ci;
                this.consts[i].setClassRef(new JVMClassRef(this, info));
            }
            else if(ci instanceof ConstantFieldrefInfo){
                ConstantFieldrefInfo info = (ConstantFieldrefInfo) ci;
                this.consts[i].setFieldRef(new JVMFieldRef(this, info));
            }
            else if(ci instanceof ConstantMethodrefInfo){
                ConstantMethodrefInfo info = (ConstantMethodrefInfo) ci;
                this.consts[i].setMethodRef(new JVMMethodRef(this, info));
            }
            else if(ci instanceof ConstantInterfaceMethodrefInfo){
                ConstantInterfaceMethodrefInfo info = (ConstantInterfaceMethodrefInfo) ci;
                this.consts[i].setInterfaceMethodRef(new JVMInterfaceMethodRef(this, info));
            }else{
                //
            }
        }
    }
}
