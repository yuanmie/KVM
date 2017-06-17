package rtda.heap;

import rtda.LocalVars;
import rtda.Slot;
import tool.Tool;

public class JVMObject implements Cloneable{
    public JVMClass klass;
    public LocalVars fields;
    public Object extra;


    public JVMObject(JVMClass klass) {
        this.klass = klass;
        this.fields = new LocalVars(klass.instanceSlotCount);
    }

    public JVMObject(JVMClass klass ,int count, int arrayType){
        this.klass = klass;
        this.fields = new LocalVars();
        switch (arrayType){
            case LocalVars.BTYPE: fields.setBarray(new byte[count]); break;
            case LocalVars.STYPE: fields.setSarray(new short[count]); break;
            case LocalVars.ITYPE: fields.setIarray(new int[count]); break;
            case LocalVars.LTYPE: fields.setLarray(new long[count]); break;
            case LocalVars.CTYPE: fields.setCarray(new char[count]); break;
            case LocalVars.FTYPE: fields.setFarray(new float[count]); break;
            case LocalVars.DTYPE: fields.setDarray(new double[count]); break;
            case LocalVars.REFTYPE: fields.setRefarray(new JVMObject[count]); break;
            default:
                Tool.panic("fuck array");
        }
    }

    public JVMObject(JVMClass klass, char[] chars) {
        this.klass = klass;
        this.fields = new LocalVars(klass.instanceSlotCount);
        fields.setCarray(chars);

    }

    public JVMObject(JVMClass klass, LocalVars fields) {
        this.klass = klass;
        this.fields = fields;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new JVMObject(this.klass, this.fields);
    }

    public boolean isInstanceOf(JVMClass klass) {
        return klass.isAssignableFrom(this.klass);
    }

    public byte[] Bytes(){
        return fields.getBarray();
    }

    public short[] Shorts(){
        return fields.getSarray();
    }

    public int[] Ints(){
        return fields.getIarray();
    }

    public char[] Chars(){
            return fields.getCarray();
    }

    public float[] Floats(){
        return fields.getFarray();
    }

    public double[] Doubles(){
        return fields.getDarray();
    }

    public long[] Longs(){
        return fields.getLarray();
    }

    public JVMObject[] Refs(){
        return fields.getRefarray();
    }

    public int arrayLength(){
        switch (fields.getArrayType()){
            case LocalVars.BTYPE: return fields.getBarray().length;
            case LocalVars.STYPE: return fields.getSarray().length;
            case LocalVars.ITYPE: return fields.getIarray().length;
            case LocalVars.LTYPE: return fields.getLarray().length;
            case LocalVars.CTYPE: return fields.getCarray().length;
            case LocalVars.FTYPE: return fields.getFarray().length;
            case LocalVars.DTYPE: return fields.getDarray().length;
            case LocalVars.REFTYPE: return fields.getRefarray().length;
            default:
                Tool.panic("fuck array");

        }
        return -1;
    }

    public void setRefVar(String name, String desciptor, JVMObject ref) {
        JVMField field = this.klass.getField(name, desciptor, false);
        this.fields.setRef(field.slotId, ref);
    }

    public JVMObject getRefVar(String name, String descriptor) {
        JVMField field = this.klass.getField(name ,descriptor, false);
        LocalVars vars = this.fields;
        return vars.getRef(field.slotId);
    }

    public JVMObject jclone() {
        JVMObject result;
        try {
            result =  (JVMObject) clone();
        } catch (CloneNotSupportedException e) {
            result = null;
        }

        return result;
    }
}
