package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {
    @Override
    /**
     * creating new maze
     * @param rows - the number of rows in the maze
     * @param columns - the number of columns in the maze
     * @return the maze that was created
     **/
    public Maze generate(int rows, int columns){
        Maze newMaze;
        newMaze = generateEmptyOrFull (rows, columns, 1); //creating a maze full of walls
        Position curr = new Position(newMaze.getStartPosition().getColumnIndex(), newMaze.getStartPosition().getRowIndex());
        Random rand = new Random();
        while (newMaze.getMatrix()[newMaze.getGoalPosition().getRowIndex()][newMaze.getGoalPosition().getColumnIndex()] != 0) { //if the goal position is 0 we a have a legal path and we can stop
            newMaze.getMatrix()[curr.getRowIndex()][curr.getColumnIndex()] = 0;
            ArrayList<String> options = getPotentialNeighbors(curr.getRowIndex(), curr.getColumnIndex(), newMaze);
            if (options.size() == 0) { //if there are no legal neighbors with value 1
                curr = goRandom(rand, curr, newMaze); //randomly choose a neighbor (even one with value 0)
                continue;
            }
            String chosen = options.get(rand.nextInt(options.size()));
            if (chosen == "right")
                curr.setColumnIndex(curr.getColumnIndex() + 1);
            else if (chosen == "left")
                curr.setColumnIndex(curr.getColumnIndex() - 1);
            else if (chosen == "up")
                curr.setRowIndex(curr.getRowIndex() - 1);
            else if (chosen == "down")
                curr.setRowIndex(curr.getRowIndex() + 1);
        }
        addRandomZeros(rand, newMaze); //after creating a legal path add random zeros in the maze
        return newMaze;
    }

    /**
     * a function that choose a random neighbor of a current position
     * @param rand the random choosing tool
     * @param curr the position we are choosing one of its neighbors
     * @param newMaze the maze we are now creating
     * @return  the position of the neighbor that was chosen
     */
        private Position goRandom(Random rand,Position curr,Maze newMaze){
            int r = rand.nextInt(4);
            if (r==0 && inBorders(curr.getRowIndex(),curr.getColumnIndex()+1, newMaze.getRows(), newMaze.getColumns())){  //moving right
                curr.setColumnIndex(curr.getColumnIndex()+1);}
            else if (r==1 && inBorders(curr.getRowIndex(),curr.getColumnIndex()-1,newMaze.getRows(), newMaze.getColumns())){  //moving left
                curr.setColumnIndex(curr.getColumnIndex()-1);}
            else if (r==2 && inBorders(curr.getRowIndex()-1,curr.getColumnIndex(),newMaze.getRows(), newMaze.getColumns())){  //moving up
                curr.setRowIndex(curr.getRowIndex()-1);}
            else if (r==3 && inBorders(curr.getRowIndex()+1,curr.getColumnIndex(),newMaze.getRows(), newMaze.getColumns())){  //moving down
                curr.setRowIndex(curr.getRowIndex()+1);}
            return curr;
        }


    /**
     * adding random zeros in the maze
     * @param rand the random choosing func
     * @param m the maze we want to add zeros to
     */
    private void addRandomZeros(Random rand, Maze m) {
        int q = m.getColumns() * m.getRows() / 5;
        for (int i = 0; i < q; i++) {
            int r1 = rand.nextInt(m.getColumns());
            int r2 = rand.nextInt(m.getRows());
            m.getMatrix()[r2][r1] = 0;
        }

    }

    /**
     * getting a specific cell and returning a list of the directions names of its neighbors that contain value 1
     * @param rowIndex the rowindex of the specific cell
     * @param colIndex the colindex of the specific cell
     * @param m the maze that contains the cell
     * @return a list of the directions names of the neighbors with value 1
     */
    private ArrayList<String> getPotentialNeighbors (int rowIndex, int colIndex, Maze m){
        ArrayList<String> options = new ArrayList<String>();
        if(inBorders(rowIndex, colIndex+1, m.getRows(), m.getColumns()) && m.getMatrix()[rowIndex][colIndex+1]==1){
            options.add("right");
            options.add("right");}
        if(inBorders(rowIndex, colIndex-1, m.getRows(), m.getColumns()) && m.getMatrix()[rowIndex][colIndex-1]==1)
            options.add("left");
        if(inBorders(rowIndex-1, colIndex, m.getRows(), m.getColumns()) && m.getMatrix()[rowIndex-1][colIndex]==1)
            options.add("up");
        if(inBorders(rowIndex+1, colIndex, m.getRows(), m.getColumns()) && m.getMatrix()[rowIndex+1][colIndex]==1){
            options.add("down");
            options.add("down");}
        return (options);
    }



}