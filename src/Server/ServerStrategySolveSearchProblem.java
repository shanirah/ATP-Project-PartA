package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;


public class ServerStrategySolveSearchProblem implements IServerStrategy{
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            Path m = Paths.get("java.io.tmpdir");
            Path ppath = Files.createDirectories(m);
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            File directory = new File(tempDirectoryPath);
            int numFiles = 0;
            boolean solved = false;


            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);

            // receive maze from the client
            Maze maze = (Maze) fromClient.readObject();


            // remove the file mazeToCheck
            File[] f = new File("java.io.tmpdir").listFiles();
            for (File file: f) {
                if (file.exists()) {
                    if (file.getName().equals("MazeToCheck.maze"))
                        file.delete();
                }
            }

            File[] files = new File("java.io.tmpdir").listFiles();

            //create file with the maze bytes in the folder
            String mFileName = "MazeToCheck.maze";
            String path = "java.io.tmpdir" + File.separator + mFileName;
            File fileMaze = new File(path);
            fileMaze.getParentFile().mkdirs();
            fileMaze.createNewFile();
            OutputStream outFile = new MyCompressorOutputStream(new FileOutputStream(path));
            outFile.write(maze.toByteArray());
            outFile.flush();
            outFile.close();


            // search in the folder - compare the file with the other files in the folder
            byte[] mazeBytesCheck = Files.readAllBytes(Paths.get(path));
            String foundMazeFile = "";
            for (File file : files){
                if (file.exists()) {
                    String currentPath = "java.io.tmpdir" + File.separator + file.getName();
                    byte[] currentFile = Files.readAllBytes(Paths.get(currentPath));
                    if (Arrays.equals(mazeBytesCheck, currentFile)) {
                        solved = true;
                        foundMazeFile = file.getName();
                    }
                }
            }


            if (!solved) {
                // find solution
                SearchableMaze searchableMaze = new SearchableMaze(maze);
                Class cl = Class.forName("algorithms.search." + Server.prop.getProperty("mazeSearchingAlgorithm"));
                java.lang.reflect.Constructor con = cl.getConstructor();
                ASearchingAlgorithm searcher = (ASearchingAlgorithm)con.newInstance();
                Solution solution = searcher.solve(searchableMaze);
                ArrayList<AState> solutionPath = solution.getSolutionPath();

                // find the next available number for a maze file
                while (true){
                    File c = new File("java.io.tmpdir" + File.separator + numFiles + "Maze.maze");
                    if (!c.exists())
                        break;
                    numFiles++;
                }


                // add the maze and the solution to the folder
                String mazeFileName = numFiles + "Maze.maze";
                File newMazeFile = new File("java.io.tmpdir" + File.separator + mazeFileName);
                newMazeFile.getParentFile().mkdirs();
                newMazeFile.createNewFile();
                OutputStream out = new MyCompressorOutputStream(new FileOutputStream("java.io.tmpdir" + File.separator + mazeFileName));
                out.write(maze.toByteArray());
                out.flush();
                out.close();

                // add the solution to the folder
                String solFileName = numFiles + "Sol.maze";
                File newSolFile = new File("java.io.tmpdir" + File.separator + solFileName);
                newSolFile.getParentFile().mkdirs();
                newSolFile.createNewFile();
                OutputStream outSol = new FileOutputStream("java.io.tmpdir" + File.separator + solFileName);

                for (int i = 0; i < solution.getSolutionPath().size(); i++){
                    outSol.write(((MazeState)solutionPath.get(i)).getPosition().getRowIndex());
                    outSol.write(((MazeState)solutionPath.get(i)).getPosition().getColumnIndex());
                }
                outSol.flush();
                outSol.close();

                // send to client
                ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
                toClient.writeObject(solution);
                toClient.flush();

                fromClient.close();
                toClient.close();
            }
            else{ // the maze is already solved, we should restore the relevant solution from the folder
                String mazeNum = foundMazeFile.substring(0,foundMazeFile.length() - 9);
                String solFileName = mazeNum + "Sol.maze";

                // read the solution from the file and send it to the client
                ArrayList<AState> solPath = new ArrayList<AState>();
                File solutionFile = new File("java.io.tmpdir" + File.separator + solFileName).getAbsoluteFile();
                InputStream i = new FileInputStream(solutionFile);
                BufferedReader reader = new BufferedReader(new InputStreamReader(i));
                int j;
                while ((j = reader.read())!=-1) {
                    int row = j;
                    int col = reader.read();
                    AState currentState = new MazeState("x", maze, row, col);
                    solPath.add(currentState);
                }
                i.close();
                Solution restoredSolution = new Solution(solPath);

                // send to client
                ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
                toClient.writeObject(restoredSolution);
                toClient.flush();

                fromClient.close();
                toClient.close();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}