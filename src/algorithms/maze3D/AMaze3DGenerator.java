package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMaze3DGenerator {
    /**
     * measuring the time of an action
     * @param depth the depth of the maze
     *  @param row the number of rows in the maze
     * @param column the number of columns in the maze
     * @return the time
     */
    public long measureAlgorithmTimeMillis(int depth, int row, int column) throws Exception {
        if (depth <=1 || row <=1 || column <=1){
            throw new Exception("minimum size is 2X2");
        }
        long start;
        long end;
        start = System.currentTimeMillis();
        generate(depth, row, column);
        end = System.currentTimeMillis();
        return (end - start);
    }

    /**
     * creating new maze with all zeros or all ones
     * @param depth the depth of the maze
     * @param rows the number of the rows in the maze
     * @param columns the number of the columns in the maze
     * @param fill the value that all the cells in the maze wil get (0/1)
     * @return a new maze filled with ones or with zeros.
     */
    protected Maze3D generateEmptyOrFull(int depth, int rows, int columns, int fill) throws Exception {
        Maze3D newMaze;
        newMaze = new Maze3D(depth, rows, columns);
        int[][][] mat = newMaze.getMap();
        for (int z = 0; z < depth; z++) {
            for (int x = 0; x < rows; x++) {
                for (int y = 0; y < columns; y++) {
                    mat[z][x][y] = fill;
                }
            }
        }
        return newMaze;
    }

    /**
     * getting a cell and checking if it is between the maze borders
     * @param depthIndex the cell depth number
     * @param rowIndex the cell row number
     * @param colIndex the cell column number
     * @param depth the depth of the maze
     * @param rows the number of the rows in the maze
     * @param columns the number of the columns in the maze
     * @return true if it is in the borders and false if it isn't
     */
    public boolean inBorders(int depthIndex, int rowIndex, int colIndex,int depth, int rows, int columns) {
        if (depthIndex >= depth || rowIndex >= rows || colIndex >= columns || depthIndex < 0 || rowIndex < 0 || colIndex < 0)
            return false;
        return true;
    }


}
