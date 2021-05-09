package IO;

import algorithms.search.Solution;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;

    @Override
    public int read() throws IOException {
        return 0;
    }

    public int read(byte b[]) throws IOException {
        int i;
        int read=0;
        int loc = 10;
        int intTurn = 0;
        for (int k=0; k<10; k++){
            b[k] = (byte) in.read();}
        read++;
        byte[] b7 = new byte[7];
        while ((i = in.read()) != -1) {
            read++;
            if (i < 128) { //binary method
                b7 = readBinary(i);
                if (b.length-1>loc+6){ //not the end of the output
                    for (int j = 0; j < 7; j++) {
                        b[loc + j] = b7[j];
                    }
                    loc +=7;}
                else {//the end of the output
                    for (int j = 0; j <=b.length-1-loc; j++) {
                        b[loc+j] = b7[j]; //adding only the end of the array (the zeros in the beginning are not part of the output in this case)
                    }
                    loc+=b.length-1-loc;}
            }
            else if (i < 192) { // adding zeros
                for (int j = 0; j < i - 121; j++) {
                    b[loc + j] = 0;
                }
                loc+=i-121;
            } else {
                for (int j = 0; j < i - 185; j++) { //adding ones
                    b[loc + j] = 1;
                }
                loc+=i - 185;

            }
        }
        return read;
    }


    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    public byte[] readBinary(int binary) throws IOException {
        byte[] b = new byte[7];
        int left;
        int loc = 0;
        while (loc < 7) {
            left = binary % 2;
            binary = (int) Math.floor((double) (binary / 2));
            b[6 - loc] = (byte) left;
            loc++;
        }
        return b;
    }
}