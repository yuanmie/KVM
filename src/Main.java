import classfile.ClassFile;
import classfile.MemberInfo;
import classpath.Classpath;
import classpath.Result;
import cmdline.Cmd;
import rtda.JVMFrame;
import rtda.LocalVars;
import rtda.OperandStack;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        Cmd cmd = new Cmd(args);
        startJVM(cmd);
    }

    public static void startJVM(Cmd cmd) throws IOException {
        System.out.printf("jre:%s classpath:%s class:%s args:%s\n"
                , cmd.getXjreOption(), cmd.getCpOption(), cmd.getClassName(), cmd.getArgs());

        Classpath cp = Classpath.parse(cmd.getXjreOption(), cmd.getCpOption());

        String className = cmd.getClassName().replaceAll("\\.", "/");
        ClassFile cf = loadClass(className, cp);
        printClassInfo(cf);

        MemberInfo _main = getMainMethod(cf);
        new Interpret(_main);
        /*
        success to read object.class.good job

        Result r = cp.readClass(className);
        System.out.println(Arrays.toString(r.getData()));
        //FileUtils.writeByteArrayToFile(new File("pathname.class"), r.getData());
        */
        JVMFrame frame = new JVMFrame(100, 100);
        testLocalVars(frame.getLocalVars());
        System.out.println("===========================");
        testOperandStack(frame.getOperandStack());
    }

    private static MemberInfo getMainMethod(ClassFile cf) {
        for(MemberInfo m :cf.getMethods()){
            if(m.Name().equals("main")
                    && m.Desciptor().equals("([Ljava/lang/String;)V")){
                return m;
            }
        }
        return null;
    }

    private static void printClassInfo(ClassFile cf) {
        System.out.printf("version: %d %d\n", cf.getMajorVersion(), cf.getMinorVersion());
        System.out.printf("constants count: %d\n", cf.getConstantPool().length());
        System.out.printf("access flags: %x\n", cf.getAccessFlag());
        System.out.printf("this class: %s \n", cf.className());
        System.out.printf("super class: %s \n", cf.superClassName());
        System.out.printf("interfaces: %s\n", Arrays.toString(cf.InterfaceNames()));
        System.out.printf("fields count: %d\n", cf.getFields().length);
        for(int i = 0; i < cf.getFields().length; i++){
            System.out.printf("\t%s\n", cf.getFields()[i].Name());
        }
        System.out.printf("methods count: %d\n", cf.getMethods().length);
        for(int i = 0; i < cf.getMethods().length; i++){
            System.out.printf("\t%s\t%s\n", cf.getMethods()[i].Name(), cf.getMethods()[i].Desciptor());
        }


    }

    private static ClassFile loadClass(String className, Classpath cp) {
        Result r = cp.readClass(className);
        ClassFile cf = new ClassFile(r.getData());
        return cf;
    }


    static void testLocalVars(LocalVars vars) {
        vars.setInt(0, 100);
        vars.setInt(1, -100);
        vars.setLong(2, 2997924580l);
        System.out.println("2997924580l show is :" + 2997924580l);
        vars.setLong(4, -2997924580l);
        vars.setFloat(6, 3.1415926f);
        vars.setDouble(7, 2.71828182845);
        vars.setRef(9, null);
        println(vars.getInt(0));
        println(vars.getInt(1));
        println(vars.getLong(2));
        println(vars.getLong(4));
        println(vars.getFloat(6));
        println(vars.getDouble(7));
        println(vars.getRef(9));
    }

    static void testOperandStack(OperandStack ops) {
        ops.pushInt(100);
        ops.pushInt(-100);
        ops.pushLong(2997924580l);
        ops.pushLong(-2997924580l);
        ops.pushFloat(3.1415926f);
        ops.pushDouble(2.71828182845);
        ops.pushRef(null);
        println(ops.popRef());
        println(ops.popDouble());
        println(ops.popFloat());
        println(ops.popLong());
        println(ops.popLong());
        println(ops.popInt());
        println(ops.popInt());
    }

    static void  println(Object s){
        System.out.println(s);
    }


}
