package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

public class Position3D extends Position {

    private int depthIndex;

    public Position3D(int depthIndex, int rowIndex, int columnIndex) throws Exception {
        super(columnIndex, rowIndex);
        if (depthIndex < 0 || rowIndex < 0 || columnIndex < 0){
            throw new Exception("illegal index");
        }
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
