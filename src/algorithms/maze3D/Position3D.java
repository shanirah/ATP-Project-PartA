package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

public class Position3D extends Position {

    private int depthIndex;

    public Position3D(int depthIndex, int rowIndex, int columnIndex) {
        super(columnIndex, rowIndex);
        this.depthIndex = depthIndex;
    }

    public int getDepthIndex() {
        return depthIndex;
    }

    public void setDepthIndex(int depthIndex) {
        this.depthIndex = depthIndex;
    }

    public String toString() {
        return ("{" + this.depthIndex + "," + this.getRowIndex() + "," + this.getColumnIndex() + "}");
    }
}
