package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import com.sun.xml.internal.ws.util.StringUtils;

public class Maze3D {
    private int rows;
    private int columns;
    private int depth;
    private int[][][] matrix;
    private Position3D startPosition;
    private Position3D goalPosition;

    public Maze3D(int depth, int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.depth = depth;
        this.matrix = new int[depth][rows][columns];
        this.startPosition = new Position3D(0, 0, 0);
        this.goalPosition = new Position3D(depth - 1, rows - 1, columns - 1);
    }

    public int[][][] getMap() {
        return matrix;
    }

    public Position3D getStartPosition() {
        return startPosition;
    }

    public Position3D getGoalPosition() {
        return goalPosition;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getDepth() {
        return depth;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void print() {
        int[][][] mat = this.getMap();
        for (int z = 0; z < depth; z++) {
            for (int x = 0; x < rows; x++) {
                System.out.print("{");
                for (int y = 0; y < columns; y++) {
                    if (x == 0 && y == 0 && z == 0)
                        System.out.print(" S");
                    else if (x == rows - 1 && y == columns - 1 && z == depth - 1)
                        System.out.print(" E");
                    else
                        System.out.print(" " + mat[z][x][y]);
                }
                System.out.println(" }");
            }
            for (int y = 0; y < columns * 2 + 3; y++) {
                System.out.print("-");
            }
            System.out.println();
        }
    }
}





