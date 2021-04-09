package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

public class MazeState extends AState{
    Position position;


    public MazeState(String state, Maze maze, int row, int col) {
        super(state);
        this.position = new Position(col,row);
    }

    public Position getPosition() {
        return position;
    }


    /**
     * check if two states are in the same position
     * @param s specific state
     * @return true if the states are in the same position else false
     */
    @Override
    protected boolean samePosition(AState s) {
        MazeState ms = (MazeState)s;
        if (this.getPosition().getColumnIndex() == ms.getPosition().getColumnIndex() && this.getPosition().getRowIndex() == ms.getPosition().getRowIndex())
            return true;
        return false;
    }


    public String toString(){
        return ("{" + position.getRowIndex() + "," + position.getColumnIndex() + "}");
    }


}
