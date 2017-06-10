package classpath;

import java.io.File;

public class DirEntry implements Entry{
    String absDir;

    public DirEntry(String path){
        File tmpFile = new File(path);
        this.absDir = tmpFile.getAbsolutePath();
    }

    @Override
    public Result readClass(String className) {
        String fileName = this.absDir + File.separator + className;
        byte[] data = file2byteArray(fileName);
        Result r = new Result(this, data);
        return r;
    }

    public String toString(){
        return this.absDir;
    }

}
