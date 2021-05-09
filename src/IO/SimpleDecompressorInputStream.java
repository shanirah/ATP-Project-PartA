package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {
    private InputStream in;


    @Override
    public int read() throws IOException {
        return in.read();
    }

    public int read(byte b[]) throws IOException {
        int i;
        int read=0;
        int loc = 10;
        int intTurn = 0;
        for (int k=0; k<10; k++){
            b[k] = (byte) in.read();
            read++;
        }
        while ((i = in.read())!=-1){
            read++;
            for (int j = 0; j < i; j++){
                b[loc+j]=(byte)intTurn;
            }
            loc=loc+i;
            if (intTurn==0)
                intTurn=1;
            else
                intTurn=0;
        }
        return read;
    }

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }
}