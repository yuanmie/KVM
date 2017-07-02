package rtda;

import tool.Tool;

import java.awt.*;

public class JVMStack {
    long maxSize;
    int size;
    JVMFrame _top;

    public JVMStack(long maxSize) {
        this.maxSize = maxSize;
    }

    public void push(JVMFrame frame) {
        if(this.size >= this.maxSize){
            Tool.panic("java.lang.StackOverflowError");
        }

        if(this._top != null){
            frame.lower = this._top;
        }

        this._top = frame;
        this.size++;
    }

    public JVMFrame pop() {
        if(this._top == null){
            Tool.panic("jvm stack is empty!");
        }
        JVMFrame r = this._top;
        this._top = this._top.lower;
        r.lower = null;
        this.size--;
        return r;
    }

    public JVMFrame top() {
        if(this._top == null){
            Tool.panic("jvm stack is empty!");
        }
        return this._top;
    }

    public boolean isEmpty() {
        return this._top == null;
    }

    public void clear() {
        while(!this.isEmpty()){
            this.pop();
        }
    }

    public JVMFrame[] getFrames() {
        JVMFrame[] frames = new JVMFrame[this.size];
        int index = 0;
        JVMFrame current = _top;
        while(current != null){
            frames[index++] = current;
            current = current.getLower();
        }
        return frames;
    }
}
