package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.search.AState;
import algorithms.search.MazeState;

public class Maze3DState extends AState {

    Position3D position3D;

    public Maze3DState(String state, Maze3D maze,int depth, int row, int col) throws Exception {
        super(state);
        position3D = new Position3D(depth,row,col);
    }

    /**
     * check if two states are in the same position
     * @param s specific state
     * @return true if the states are in the same position else false
     */
    @Override
    protected boolean samePosition(AState s) {
        Maze3DState ms = (Maze3DState)s;
        if (this.getPosition3D().getColumnIndex() == ms.getPosition3D().getColumnIndex() && this.getPosition3D().getRowIndex() == ms.getPosition3D().getRowIndex() && this.getPosition3D().getDepthIndex() == ms.getPosition3D().getDepthIndex())
            return true;
        return false;
    }

    public Position3D getPosition3D() {
        return position3D;
    }

    public void setPosition3D(Position3D position3D) {
        this.position3D = position3D;
    }

    public String toString(){
        return ("{" + position3D.getDepthIndex() + "," + position3D.getRowIndex() + "," + position3D.getColumnIndex() + "}");
    }

}
