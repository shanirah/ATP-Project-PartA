package algorithms.mazeGenerators;

public class Maze {
    private int rows;
    private int columns;
    private int[][] matrix;
    private Position startPosition;
    private Position goalPosition;


    public Maze(int rows, int columns) throws Exception {
        if (rows <=1 || columns <=1){
            throw new Exception("minimum size is 2X2");
        }
        this.rows = rows;
        this.columns = columns;
        this.matrix = new int[rows][columns];
        this.startPosition= new Position(0,0);
        this.goalPosition= new Position(columns-1,rows-1);

    }
    public void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    public void setGoalPosition(Position goalPosition) {
        this.goalPosition = goalPosition;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getGoalPosition() {
        return goalPosition;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * printing the maze
     */
    public void print(){
        int [][] mat = this.getMatrix();
        for (int x = 0; x < rows; x++) { //moving over all the rows
            System.out.print("{");
            for (int y = 0; y < columns; y++) { //moving over all the columns
                if (x == 0 && y == 0)
                    System.out.print(" S");
                else if (x == rows - 1 && y == columns - 1)
                    System.out.print(" E");
                else
                    System.out.print(" "+mat[x][y]);
            }
            System.out.println(" }");
        }
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int[][] getMatrix() {
        return matrix;
    }

}
