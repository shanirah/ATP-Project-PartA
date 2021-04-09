package algorithms.mazeGenerators;

public class Position {
    private int columnIndex;
    private int rowIndex;

    public Position(int columnIndex, int rowIndex) {
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public String toString(){
        return ("{" + this.rowIndex + "," + this.columnIndex + "}");

    }

}
