package test;

import algorithms.maze3D.IMazeGenerator3D;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.mazeGenerators.*;

public class RunMaze3DGenerator {
    public static void main(String[] args) {
        testMazeGenerator(new MyMaze3DGenerator());
    }

    private static void testMazeGenerator(IMazeGenerator3D mazeGenerator3D) {
        // prints the time it takes the algorithm to run
        System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator3D.measureAlgorithmTimeMillis(100,100, 100)));
        // generate another maze
        Maze3D maze = mazeGenerator3D.generate(100, 100, 100);
        // prints the maze
        maze.print();
        // get the maze entrances
        Position startPosition = maze.getStartPosition();
        // print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{depth,row,column}"
        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));

    }

}