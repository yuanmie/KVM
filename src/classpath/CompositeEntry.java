package classpath;

import java.io.File;
import java.util.ArrayList;

public class CompositeEntry implements Entry {
    private ArrayList<Entry> entrys;
    public CompositeEntry(String pathList) {
        for(String s : pathList.split(File.pathSeparator)){
            entrys.add(newEntry(s));
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
