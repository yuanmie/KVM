package classpath;

public class Result {
    private byte[] data;
    private Entry entry;

    public Result(Entry entry, byte[] data) {
        this.entry = entry;
        this.data = data;
    }


    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
