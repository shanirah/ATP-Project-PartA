package algorithms.mazeGenerators;

public class Maze {
    private int rows;
    private int columns;
    private int[][] matrix;
    private Position startPosition;
    private Position goalPosition;


    public Maze(int rows, int columns) {
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

    public byte[] toByteArray(){
        byte[] byteArray = new byte[rows * columns + 10];
        // represent number of columns
        byteArray[0] = (byte) (int)Math.floor((double)this.getColumns() / 10);
        byteArray[1] = (byte) (this.getColumns() % 10);

        // represent row index and column index of start position
        byteArray[2] = (byte) (int)Math.floor((double)this.getStartPosition().getRowIndex() / 10);
        byteArray[3] = (byte) (this.getStartPosition().getRowIndex() % 10);
        byteArray[4] = (byte) (int)Math.floor((double)this.getStartPosition().getColumnIndex() / 10);
        byteArray[5] = (byte) (this.getStartPosition().getColumnIndex() % 10);

        // represent row index and column index of goal position
        byteArray[6] = (byte) (int)Math.floor((double)this.getGoalPosition().getRowIndex() / 10);
        byteArray[7] = (byte) (this.getGoalPosition().getRowIndex() % 10);
        byteArray[8] = (byte) (int)Math.floor((double)this.getGoalPosition().getColumnIndex() / 10);
        byteArray[9] = (byte) (this.getGoalPosition().getColumnIndex() % 10);

        for (int x = 0; x < rows; x++){
            for (int y = 0; y < columns; y++){
                byteArray[(columns * x + y) + 10] = (byte) this.getMatrix()[x][y];
            }
        }
        return byteArray;
    }

    public Maze(byte[] byteArray) {
        this.columns = byteArray[0] * 10 + byteArray[1];
        this.rows = (byteArray.length - 10) / columns;
        this.matrix = new int[rows][columns];
        int startCol = byteArray[4] * 10 + byteArray[5];
        int startRow = byteArray[2] * 10 + byteArray[3];
        this.startPosition = new Position(startCol, startRow);
        int goalCol = byteArray[8] * 10 + byteArray[9];
        int goalRow = byteArray[6] * 10 + byteArray[7];
        this.goalPosition = new Position(goalCol, goalRow);

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                this.getMatrix()[x][y] = (int) byteArray[(columns * x + y) + 10];
            }
        }
    }
}
