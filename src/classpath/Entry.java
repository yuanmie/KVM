package classpath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * @author yuanmie
 */
public interface Entry {

    Result readClass(String className);
    default Entry newEntry(String path){
        if (path.contains(File.pathSeparator)) {
            return new CompositeEntry(path);
        }
        if (path.endsWith("*")) {
            return new WildcardEntry(path);
        }
        if (path.endsWith(".jar") || path.endsWith(".JAR") ||
                path.endsWith(".zip") || path.endsWith(".ZIP")) {
            return new ZipEntry(path);
        }
        return new DirEntry(path);
    }

    default byte[] file2byteArray(String fileName){
        Path path = Paths.get(fileName);
        byte[] data = null;
        try {
             data = Files.readAllBytes(path);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }



}
