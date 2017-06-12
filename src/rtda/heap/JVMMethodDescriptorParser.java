package rtda.heap;

import tool.Tool;

public class JVMMethodDescriptorParser {
    String raw;
    int offset;
    JVMMethodDescriptor methodDescriptor;

    public JVMMethodDescriptorParser(String descriptor){
        this.raw = descriptor;
    }

    public JVMMethodDescriptor parse(){
        this.methodDescriptor = new JVMMethodDescriptor();
        this.startParams();
        this.parseParams();
        this.endParams();
        this.parseReturn();
        this.finish();
        return this.methodDescriptor;
    }

    private void parseReturn() {
        if(this.readUint8() == 'V'){
            this.methodDescriptor.returnType = "V";
            return;
        }

        this.unReadUint8();
        String t = this.parseFieldType();
        if(!"".equals(t)){
            this.methodDescriptor.returnType = t;
            return;
        }

        panic();
    }

    private String parseFieldType() {
        switch (this.readUint8()){
            case 'B': return "B";
            case 'C': return "C";
            case 'S': return "S";
            case 'I': return "I";
            case 'J': return "J";
            case 'D': return "D";
            case 'F': return "F";
            case 'Z': return "Z";
            case 'L': return parseObjectType();
            case '[': return parseArrayType();
            default:
                this.unReadUint8();
                return "";
        }
    }

    private String parseArrayType() {
        int arrayStart = this.offset - 1;
        this.parseFieldType();
        int arrayEnd = this.offset;
        return this.raw.substring(arrayStart, arrayEnd);
    }

    private String parseObjectType() {
        int unread = this.offset;
        int semicolonIndex = this.raw.indexOf(';', unread);
        if(semicolonIndex == -1){
            panic();
            return "";
        }else{
            int objStart = this.offset - 1;
            int objEnd = semicolonIndex + 1;
            this.offset = objEnd;
            return this.raw.substring(objStart, objEnd);
        }
        
    }

    private void unReadUint8() {
        --this.offset;
    }

    private void parseParams() {
        while(true){
            String t = this.parseFieldType();
            if(!"".equals(t)){
                this.methodDescriptor.addParameterType(t);
            }else{
                break;
            }
        }
    }

    private void finish() {
        if(this.offset != this.raw.length()){
            panic();
        }
    }

    private void panic() {
        Tool.panic("bad descriptor : " + this.raw);
    }

    private void endParams() {
        if(this.readUint8() != ')'){
            panic();
        }
    }

    private int readUint8() {
        int b = this.raw.charAt(this.offset);
        ++this.offset;
        return b;
    }

    private void startParams() {
        if(this.readUint8() != '('){
            panic();
        }
    }
}
