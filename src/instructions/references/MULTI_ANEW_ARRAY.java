package instructions.references;

import instructions.base.ByteCodeReader;
import instructions.base.Instruction;
import rtda.JVMFrame;
import rtda.OperandStack;
import rtda.heap.JVMClass;
import rtda.heap.JVMClassRef;
import rtda.heap.JVMConstantPool;
import rtda.heap.JVMObject;
import tool.Tool;

import java.util.Arrays;

public class MULTI_ANEW_ARRAY implements Instruction{
    int index;
    int dimensions;
    @Override
    public void fetchOperands(ByteCodeReader reader) {
        this.index = reader.readUint16();
        this.dimensions = reader.readUint8();
    }

    @Override
    public void execute(JVMFrame frame) {
        JVMConstantPool cp = frame.getMethod().klass.getCp();
        JVMClassRef classRef = cp.getContant(this.index).getClassRef();
        JVMClass arrClass= classRef.resolvedClass();
        OperandStack stack = frame.getOperandStack();
        int[] counts = popAndCheckCounts(stack, this.dimensions);
        JVMObject arr = newMultiDimensionalArray(counts, arrClass);
        stack.pushRef(arr);
    }

    private int[] popAndCheckCounts(OperandStack stack, int dimensions) {
        int[] counts = new int[dimensions];
        for(int i = dimensions - 1; i >= 0; i--){
            counts[i] = stack.popInt();
            if(counts[i] < 0){
                Tool.panic("java.lang.NegativeArraySizeException");
            }
        }
        return counts;
    }

    public JVMObject newMultiDimensionalArray(int[] counts, JVMClass arrClass){
        int count = counts[0];
        JVMObject arr = arrClass.newArray(count);
        if(counts.length > 1){
            JVMObject[] refs = arr.Refs();
            for(int i = 0; i < refs.length; i++){
                refs[i] = newMultiDimensionalArray(
                        Arrays.copyOfRange(counts, 1, counts.length),
                        arrClass.componentClass()
                );
            }
        }
        return arr;
    }
}

