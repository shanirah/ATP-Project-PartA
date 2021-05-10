package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import com.sun.corba.se.spi.activation.Activator;
import algorithms.mazeGenerators.*;

import java.io.*;
import java.util.ArrayList;

public class ServerStrategyGenerateMaze implements IServerStrategy{
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
                ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
                ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                // receive maze dimensions from the client
                int[] mazeDim = (int[]) fromClient.readObject();

                // generate maze according to the maze dimensions
                Class cl = Class.forName("algorithms.mazeGenerators." + Server.prop.getProperty("mazeGeneratingAlgorithm"));
                java.lang.reflect.Constructor con = cl.getConstructor();
                AMazeGenerator sGenerator = (AMazeGenerator)con.newInstance();

                Maze newMaze = sGenerator.generate(mazeDim[0], mazeDim[1]);


                // compress the maze
                MyCompressorOutputStream compressor = new MyCompressorOutputStream(byteArrayOutputStream);
                byte[] mazeBytes = newMaze.toByteArray();
                compressor.write(mazeBytes);
                // compressor.flush();

                // send to client
                toClient.writeObject(byteArrayOutputStream.toByteArray());
                toClient.flush();

                fromClient.close();
                toClient.close();
                byteArrayOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
