package classpath;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipFile;

public class ZipEntry implements Entry{
    private String absDir;
    public ZipEntry(String path){
        this.absDir = path;
    }

    @Override
    public Result readClass(String className) {
        ZipFile zip = null;
        try {
            zip = new ZipFile(this.absDir);
            for (Enumeration e = zip.entries(); e.hasMoreElements(); ) {
                java.util.zip.ZipEntry entry = (java.util.zip.ZipEntry) e.nextElement();
                if(className.equals(entry.getName())){
                    byte[] data = IOUtils.toByteArray(zip.getInputStream(entry));
                    return new Result(this, data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public String toString(){
        return this.absDir;
    }
}
