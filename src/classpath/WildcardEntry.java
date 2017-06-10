package classpath;

import java.io.File;
import java.util.ArrayList;

public class WildcardEntry implements Entry {
    private String basePath;
    private ArrayList<Entry> entrys;
    public WildcardEntry(String path) {
        this.basePath = path.substring(0, path.length() - 1);
        entrys = new ArrayList<Entry>();
        File dir = new File(basePath);
        if(dir.isDirectory()){
            for(File f: dir.listFiles()){
                if(f.isDirectory()){
                    continue;
                }else if(f.getName().endsWith(".jar") || f.getName().endsWith(".JAR")){
                    entrys.add(new ZipEntry(f.getAbsolutePath()));
                }
            }
        }else{

        }
    }

    @Override
    public Result readClass(String className) {
        Result r = null;
        for(Entry e : entrys){
            r = e.readClass(className);
            if(r != null){
                return r;
            }
        }
        return null;
    }

    public String toString(){
        String[] strs = new String[entrys.size()];
        int index = -1;
        for(Entry e : entrys){
            strs[++index] = e.toString();
        }
        return String.join(File.pathSeparator, strs);
    }
}
