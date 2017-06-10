package classfile;

import java.io.DataInputStream;
import java.io.UnsupportedEncodingException;

public class ConstantUtf8Info extends ConstantInfo {
    String str;
    @Override
    public void readInfo(ClassReader cr) {
        int length = Short.toUnsignedInt(cr.readUint16());
        byte[] data = cr.readBytes(length);
        try {
            this.str = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String toString(){
        return this.str;
    }
}
