package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {

    public abstract Maze generate(int rows, int columns) throws Exception;

    /**
     * measuring the time of an action
     * @param rows the number of rows in the maze
     * @param columns the number of columns in the maze
     * @return the time
     */
    public long measureAlgorithmTimeMillis(int rows, int columns) throws Exception {
        if (rows <=1 || columns <=1){
            throw new Exception("minimum size is 2X2");
        }
        long start;
        long end;
        start = System.currentTimeMillis();
        generate(rows, columns);
        end = System.currentTimeMillis();
        return (end - start);
    }

    /**
     * getting a cell and checking if it is between the maze borders
     * @param rowIndex the cell row number
     * @param colIndex the cell column number
     * @param rows the number of the rows in the maze
     * @param columns the number of the columns in the maze
     * @return true if it is in the borders and false if it isn't
     */
    public boolean inBorders(int rowIndex, int colIndex, int rows, int columns) {
        if (rowIndex >= rows || colIndex >= columns || rowIndex < 0 || colIndex < 0)
            return false;
        return true;
    }

    /**
     * creating new maze with all zeros or all ones
     * @param rows the number of the rows in the maze
     * @param columns the number of the columns in the maze
     * @param fill the value that all the cells in the maze wil get (0/1)
     * @return a new maze filled with ones or with zeros.
     */
    protected Maze generateEmptyOrFull(int rows, int columns, int fill) throws Exception {
        Maze newMaze;
        newMaze = new Maze(rows, columns);
        int[][] mat = newMaze.getMatrix();
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                mat[x][y] = fill;
            }
        }
        return newMaze;
    }
}