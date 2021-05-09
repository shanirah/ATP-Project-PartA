package IO;

import algorithms.search.AState;
import algorithms.search.MazeState;
import algorithms.search.Solution;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;


    @Override
    public void write(int b) throws IOException {
        this.out.write(b);
    }

    public void write(byte[] b) throws IOException {
        int count = 1;
        for (int i = 0; i < 10; i++){
            write(b[i]);
        }
        if (b[0] == 1){
            write(0);
        }
        for (int i = 10; i < b.length-1; i++){
            if (b[i] == b[i+1]) {
                if (count == 255) {
                    write(count);
                    count = 0;

                    if (i+2 <= b.length-1 && b[i+1] == b[i+2]){
                        write(0);
                    }
                }
                count++;
            }
            else {
                write(count);
                count = 1;
            }
        }
    }


    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }
}