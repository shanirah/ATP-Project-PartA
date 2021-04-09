package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable{
    private Maze maze;

    public SearchableMaze(Maze maze) {
        this.maze = maze;
    }


    @Override
    public AState getStartState() {
        return new MazeState("Start", maze, maze.getStartPosition().getRowIndex(), maze.getStartPosition().getColumnIndex());
    }

    @Override
    public AState getGoalState() {
        return new MazeState("Goal", maze, maze.getGoalPosition().getRowIndex(), maze.getGoalPosition().getColumnIndex());
    }

    /**
     * check if the specific cell is in borders and it is a wall
     * @param rowIndex of the position
     * @param colIndex of the position
     * @return true or false
     */
    private boolean possiblePass(int rowIndex, int colIndex) {
        if (rowIndex >= maze.getRows() || colIndex >= maze.getColumns() || rowIndex < 0 || colIndex < 0 || maze.getMatrix()[rowIndex][colIndex] == 1)
            return false;
        return true;
    }

    /**
     * get all successors of specific state
     * @param e specific state
     * @return updated arraylist with all the successors
     */
    @Override
    public ArrayList<AState> getAllSuccessors(AState e) {
        MazeState ms = (MazeState)e;
        int checkUpLeft = 0;
        int checkDownRight = 0;
        int checkUpRight = 0;
        int checkDownLeft = 0;
        ArrayList<AState> successors = new ArrayList<AState>();
        if (possiblePass(ms.getPosition().getRowIndex()-1,ms.getPosition().getColumnIndex())){
            MazeState up = new MazeState("up",maze,ms.getPosition().getRowIndex()-1,ms.getPosition().getColumnIndex());
            successors.add(up);
            checkUpLeft++; // in order check diagonal moves
            checkUpRight++;
        }
        if (possiblePass(ms.getPosition().getRowIndex()+1,ms.getPosition().getColumnIndex())) {
            MazeState down = new MazeState("down",maze,ms.getPosition().getRowIndex()+1,ms.getPosition().getColumnIndex());
            successors.add(down);
            checkDownLeft++;
            checkDownRight++;
        }
        if (possiblePass(ms.getPosition().getRowIndex(),ms.getPosition().getColumnIndex()-1)) {
            MazeState left = new MazeState("left",maze,ms.getPosition().getRowIndex(),ms.getPosition().getColumnIndex()-1);
            successors.add(left);
            checkDownLeft++;
            checkUpLeft++;
        }
        if (possiblePass(ms.getPosition().getRowIndex(),ms.getPosition().getColumnIndex()+1)) {
            MazeState right = new MazeState("right",maze,ms.getPosition().getRowIndex(),ms.getPosition().getColumnIndex()+1);
            successors.add(right);
            checkDownRight++;
            checkUpRight++;
        }
        if (possiblePass(ms.getPosition().getRowIndex()-1,ms.getPosition().getColumnIndex()-1) && checkUpLeft > 0) {
            MazeState upLeft = new MazeState("upLeft", maze, ms.getPosition().getRowIndex() - 1, ms.getPosition().getColumnIndex() - 1);
            successors.add(upLeft);
        }
        if (possiblePass(ms.getPosition().getRowIndex()+1,ms.getPosition().getColumnIndex()+1) && checkDownRight > 0) {
            MazeState downRight = new MazeState("downRight", maze, ms.getPosition().getRowIndex() + 1, ms.getPosition().getColumnIndex() + 1);
            successors.add(downRight);
        }
        if (possiblePass(ms.getPosition().getRowIndex()-1,ms.getPosition().getColumnIndex()+1) && checkUpRight > 0) {
            MazeState upRight = new MazeState("upRight", maze, ms.getPosition().getRowIndex() - 1, ms.getPosition().getColumnIndex() + 1);
            successors.add(upRight);
        }
        if (possiblePass(ms.getPosition().getRowIndex()+1,ms.getPosition().getColumnIndex()-1) && checkDownLeft > 0) {
            MazeState downLeft = new MazeState("downLeft", maze, ms.getPosition().getRowIndex() + 1, ms.getPosition().getColumnIndex() - 1);
            successors.add(downLeft);
        }
        return successors;
    }
}
