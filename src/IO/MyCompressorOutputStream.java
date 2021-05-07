package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;


    @Override
    public void write(int b) throws IOException {
        this.out.write(b);
    }

    public void write(byte[] b) throws IOException {
        int count = 1;

        for (int i = 0; i < 10; i++) {
            write(b[i]);
        }
        boolean same = true;
        int[] binary = new int[7];
        for (int i = 10; i < b.length; i++) {
            if (count < 8)
                binary[count-1] = b[i];
            if (i == b.length - 1 ||(b[i] != b[i + 1] && same == true) || count == 70 ) { //we got to the end of sequence from the same number
                if (count < 7) { //we would like to use the binary method
                    if (i != b.length - 1) {
                        same = false;
                        count++;
                        continue;
                    } else //this is the end of the input
                        writebinary(binary);
                } else if (b[i] == 0) //we got a sequence of zeros
                    write(count + 121); //if the number we got is between 128 and 191 we are adding n-121 0's
                else //we got a sequence of ones
                    write(count + 185); //if the number (n) we got is >=192 we are adding n-185 1's
                count = 0;
                binary = new int[7];
                same = true;
            }
            if (count == 7 && !same) {
                writebinary(binary);
                count = 0;
                binary = new int[7];
                same = true;
            }
            count++;

        }
    }

    /**
     * get a binary number in array and write it as a decimal
     *
     * @param binary int array that contains the binary num
     * @throws IOException
     */
    public void writebinary(int[] binary) throws IOException {
        int toWrite = 0;
        int loc = 0;
        for (int i = binary.length-1; i >= 0; i--) {
            if (binary[i] == 1)
                toWrite += Math.pow(2, loc);
            loc++;
        }
        write(toWrite);

    }

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }
}