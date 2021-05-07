package algorithms.maze3D;

import algorithms.search.AState;
import algorithms.search.ISearchable;
import algorithms.search.MazeState;

import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable {
    private Maze3D maze;
    private int[][][] visitedMat;


    public SearchableMaze3D(Maze3D maze) throws Exception {
        if (maze == null){
            throw new Exception("maze can't be null");
        }
        this.maze = maze;
        visitedMat = new int[maze.getDepth()][maze.getRows()][maze.getColumns()];
        this.newSearch();
    }

    @Override
    public AState getStartState() throws Exception {
        return new Maze3DState("Start", maze, maze.getStartPosition().getDepthIndex(),maze.getStartPosition().getRowIndex(),maze.getStartPosition().getColumnIndex());
    }

    @Override
    public AState getGoalState() throws Exception {
        return new Maze3DState("Goal", maze, maze.getGoalPosition().getDepthIndex(),maze.getGoalPosition().getRowIndex(),maze.getGoalPosition().getColumnIndex());
    }

    /**
     * check if the specific cell is in borders and it is a wall
     *
     * @param rowIndex of the position
     * @param colIndex of the position
     * @return true or false
     */
    private boolean possiblePass(int depthIndex, int rowIndex, int colIndex) {
        if (depthIndex >= maze.getDepth() || rowIndex >= maze.getRows() || colIndex >= maze.getColumns() || rowIndex < 0 || colIndex < 0 || depthIndex < 0 || maze.getMap()[depthIndex][rowIndex][colIndex] == 1)
            return false;
        return true;
    }

    /**
     * get all successors of specific state
     *
     * @param e specific state
     * @return updated arraylist with all the successors
     */
    @Override
    public ArrayList<AState> getAllSuccessors(AState e) throws Exception {
        if (e == null){
            throw new Exception("state can't be null");
        }
        Maze3DState ms = (Maze3DState)e;
        ArrayList<AState> successors = new ArrayList<AState>();
        if (possiblePass(ms.getPosition3D().getDepthIndex(), ms.getPosition3D().getRowIndex() - 1, ms.getPosition3D().getColumnIndex())) {
            Maze3DState up = new Maze3DState("up", maze, ms.getPosition3D().getDepthIndex(), ms.getPosition3D().getRowIndex() - 1, ms.getPosition3D().getColumnIndex());
            successors.add(up);
        }
        if (possiblePass(ms.getPosition3D().getDepthIndex(), ms.getPosition3D().getRowIndex() + 1, ms.getPosition3D().getColumnIndex())){
            Maze3DState down = new Maze3DState("down",maze,ms.getPosition3D().getDepthIndex(), ms.getPosition3D().getRowIndex() + 1, ms.getPosition3D().getColumnIndex());
            successors.add(down);
        }
        if (possiblePass(ms.getPosition3D().getDepthIndex(), ms.getPosition3D().getRowIndex() , ms.getPosition3D().getColumnIndex() - 1)){
            Maze3DState left = new Maze3DState("left",maze,ms.getPosition3D().getDepthIndex(), ms.getPosition3D().getRowIndex(), ms.getPosition3D().getColumnIndex() - 1);
            successors.add(left);
        }
        if (possiblePass(ms.getPosition3D().getDepthIndex(), ms.getPosition3D().getRowIndex() , ms.getPosition3D().getColumnIndex() + 1)){
            Maze3DState right = new Maze3DState("left",maze,ms.getPosition3D().getDepthIndex(), ms.getPosition3D().getRowIndex(), ms.getPosition3D().getColumnIndex() + 1);
            successors.add(right);
        }
        if (possiblePass(ms.getPosition3D().getDepthIndex()+1, ms.getPosition3D().getRowIndex() , ms.getPosition3D().getColumnIndex())){
            Maze3DState high = new Maze3DState("high",maze,ms.getPosition3D().getDepthIndex()+1, ms.getPosition3D().getRowIndex(), ms.getPosition3D().getColumnIndex());
            successors.add(high);
        }
        if (possiblePass(ms.getPosition3D().getDepthIndex()-1, ms.getPosition3D().getRowIndex() , ms.getPosition3D().getColumnIndex())){
            Maze3DState low = new Maze3DState("low",maze,ms.getPosition3D().getDepthIndex()-1, ms.getPosition3D().getRowIndex(), ms.getPosition3D().getColumnIndex());
            successors.add(low);
        }
        return successors;
    }

    /**
     * check if a specific state is visited
     * @param e the state we want to check
     * @return true if visited else false
     */
    @Override
    public boolean isVisited(AState e) {
        if (this.visitedMat[((Maze3DState)e).getPosition3D().getDepthIndex()][((Maze3DState)e).getPosition3D().getRowIndex()][((Maze3DState)e).getPosition3D().getColumnIndex()] == 1) {
            return true;
        }
        return false;
    }

    /**
     * update a specific state as visited
     * @param e the state we want to update
     */
    @Override
    public void setVisited(AState e) {
        this.visitedMat[((Maze3DState)e).getPosition3D().getDepthIndex()][((Maze3DState)e).getPosition3D().getRowIndex()][((Maze3DState)e).getPosition3D().getColumnIndex()] = 1;
    }

    /**
     * initialize the visited matrix with zeros
     */
    @Override
    public void newSearch() {
        for (int z = 0; z < maze.getDepth(); z++) {
            for (int x = 0; x < maze.getRows(); x++) {
                for (int y = 0; y < maze.getColumns(); y++) {
                    visitedMat[z][x][y] = 0;
                }
            }
        }
    }
}

