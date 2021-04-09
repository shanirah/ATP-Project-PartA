package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator {
    /**
     * generating new maze according to Prim algorithm
     * @param rows number of rows in the maze
     * @param columns number of columns in the maze
     * @return finished maze
     */
    @Override
    public Maze generate(int rows, int columns) {
        Maze newMaze = generateEmptyOrFull (rows, columns, 1); // create maze full of walls
        Position curr = new Position(newMaze.getStartPosition().getColumnIndex(), newMaze.getStartPosition().getRowIndex());
        newMaze.getMatrix()[curr.getRowIndex()][curr.getColumnIndex()] = 0; // change the start position to 0
        ArrayList<Wall> walls= new ArrayList<Wall>();
        walls = addNeighborWalls (newMaze,curr, walls); // add the walls of start position
        Random rand = new Random();
        while (walls.size()>0) {
            if (newMaze.getMatrix()[newMaze.getGoalPosition().getRowIndex()][newMaze.getGoalPosition().getColumnIndex()] == 0)
                break; // if we found the goal position there is no need to continue
            Wall chosen = walls.get(rand.nextInt(walls.size()));
            if (legalWall(newMaze, chosen)) {
                newMaze.getMatrix()[chosen.wall.getRowIndex()][chosen.wall.getColumnIndex()] = 0; // make the wall a passage
                // check if positionA or positionB is the visited one.
                if (inBorders(chosen.positionB.getRowIndex(), chosen.positionB.getColumnIndex(), newMaze.getRows(), newMaze.getColumns()) && newMaze.getMatrix()[chosen.positionB.getRowIndex()][chosen.positionB.getColumnIndex()] == 1)
                {
                    newMaze.getMatrix()[chosen.positionB.getRowIndex()][chosen.positionB.getColumnIndex()] = 0;
                    walls = addNeighborWalls(newMaze, chosen.positionB, walls);
                    newMaze.getMatrix()[chosen.positionB.getRowIndex()][chosen.positionB.getColumnIndex()] = 0;
                }
                else if (inBorders(chosen.positionA.getRowIndex(), chosen.positionA.getColumnIndex(), newMaze.getRows(), newMaze.getColumns())){
                    walls = addNeighborWalls(newMaze, chosen.positionA, walls);
                    newMaze.getMatrix()[chosen.positionA.getRowIndex()][chosen.positionA.getColumnIndex()] = 0;}
                }

            walls.remove(chosen);
        }
        return newMaze;
    }


    /**
     * adding walls around specific position if it is not out of the maze borders.
     * @param m the relevant maze
     * @param curr the specific cell we want to add the walls around it
     * @param walls list of existed walls
     * @return updated list of walls after the insertions
     */
    private ArrayList<Wall> addNeighborWalls (Maze m, Position curr, ArrayList<Wall> walls){
       if (inBorders(curr.getRowIndex()-1,curr.getColumnIndex(),m.getRows(), m.getColumns())){
            Wall up = new Wall(curr, new Position(curr.getColumnIndex() , curr.getRowIndex()-1), new Position(curr.getColumnIndex() , curr.getRowIndex()-2));
            walls.add(up);
       }
       if (inBorders(curr.getRowIndex()+1,curr.getColumnIndex(),m.getRows(), m.getColumns())) {
            Wall down = new Wall(curr, new Position(curr.getColumnIndex() , curr.getRowIndex()+1), new Position(curr.getColumnIndex() , curr.getRowIndex()+2));
            walls.add(down);
       }
       if (inBorders(curr.getRowIndex(),curr.getColumnIndex()-1,m.getRows(), m.getColumns())) {
            Wall left = new Wall(curr, new Position(curr.getColumnIndex()-1 , curr.getRowIndex()), new Position(curr.getColumnIndex()-2 , curr.getRowIndex()));
            walls.add(left);
       }
       if (inBorders(curr.getRowIndex(),curr.getColumnIndex()+1,m.getRows(), m.getColumns())) {
            Wall right = new Wall(curr, new Position(curr.getColumnIndex()+1 , curr.getRowIndex()), new Position(curr.getColumnIndex()+2 , curr.getRowIndex()));
            walls.add(right);
       }
       return walls;
    }

    /**
     * the function checks if one side of the wall is visited and the other is not.
     * @param m the relevant maze
     * @param w the wall we want to check
     * @return true if the wall is legal (one side visited)
     */
    private boolean legalWall(Maze m, Wall w){
        if (!inBorders(w.positionA.getRowIndex(), w.positionA.getColumnIndex(),m.getRows(),m.getColumns()) || m.getMatrix()[w.positionA.getRowIndex()][w.positionA.getColumnIndex()]==1)
            if (m.getMatrix()[w.positionB.getRowIndex()][w.positionB.getColumnIndex()]==0 && m.getMatrix()[w.wall.getRowIndex()][w.wall.getColumnIndex()] == 1)
                return true; // position A is not visited yet
        if (!inBorders(w.positionB.getRowIndex(),w.positionB.getColumnIndex(),m.getRows(),m.getColumns()) || m.getMatrix()[w.positionB.getRowIndex()][w.positionB.getColumnIndex()]==1)
            if (m.getMatrix()[w.positionA.getRowIndex()][w.positionA.getColumnIndex()]==0 && m.getMatrix()[w.wall.getRowIndex()][w.wall.getColumnIndex()] == 1)
                return true; // position B is not visited yet
        return false;
    }
}




