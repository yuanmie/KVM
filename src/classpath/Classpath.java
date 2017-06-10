package classpath;

import java.io.File;


public class Classpath implements Entry{
    private Entry bootClasspath;
    private Entry extClasspath;
    private Entry userClasspath;

    public static Classpath parse(String jreOption, String cpOption){
        Classpath cp = new Classpath();
        cp.parseBootAndExtClasspath(jreOption);
        cp.parseUserClasspath(cpOption);
        return cp;
    }

    private void parseUserClasspath(String cpOption) {
        if ("".equals(cpOption)){
            cpOption = ""; //set current dir
        }
        userClasspath = newEntry(cpOption);
    }

    private void parseBootAndExtClasspath(String jreOption) {
        String jreDir = getJreDir(jreOption);
        String jreLibPath = join(jreDir, "lib", "*");
        bootClasspath = new WildcardEntry(jreLibPath);
        String jreExtPath = join(jreDir, "lib", "ext", "*");
        extClasspath = new WildcardEntry(jreExtPath);
    }

    private String join(String dir, String... subDir){
        String completePath = dir;
        for(String s : subDir){
            completePath = completePath + File.separator + s;
        }
        return completePath;
    }

    private String getJreDir(String jreOption){

        if(!"".equals(jreOption) && new File(jreOption).exists()){
            return jreOption;
        }

        if(new File("./jre").exists()){
            return "./jre";
        }

        String osJrePath = System.getenv("JAVA_HOME");
        if(!"".equals(osJrePath)){
            return join(osJrePath, "jre");
        }
        return "";
    }

    @Override
    public Result readClass(String className) {
        className = className + ".class";
        Result r;

        if((r = bootClasspath.readClass(className)) != null){
            return r;
        }

        if((r = extClasspath.readClass(className)) != null){
            return r;
        }

        r = userClasspath.readClass(className);
        return r;
    }

    public String toString(){
        return userClasspath.toString();
    }


}
