package rtda;

public class StackTraceElement {
    private String fileName;
    private String className;
    private String methodName;
    private int lineNumber;

    public StackTraceElement(String fileName, String className, String methodName, int lineNumber) {
        this.fileName = fileName;
        this.className = className;
        this.methodName = methodName;
        this.lineNumber = lineNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String toString(){
        return String.format("%s.%s(%s:%d)",
                this.className, this.methodName, this.fileName, this.lineNumber);
    }
}
