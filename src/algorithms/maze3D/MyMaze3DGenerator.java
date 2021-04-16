package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.Wall;

import java.util.ArrayList;
import java.util.Random;

public class MyMaze3DGenerator extends AMaze3DGenerator{

    /**
     * generating new maze according to Prim algorithm
     * @param depth of the maze
     * @param row number of rows in the maze
     * @param column number of columns in the maze
     * @return finished maze
     */
    @Override
    public Maze3D generate(int depth, int row, int column) throws Exception {
        if (depth <=1 || row <=1 || column <=1){
            throw new Exception("minimum size is 2X2");
        }
        Maze3D newMaze = generateEmptyOrFull(depth, row, column, 1);
        Position3D curr = new Position3D(newMaze.getStartPosition().getDepthIndex(), newMaze.getStartPosition().getRowIndex(), newMaze.getStartPosition().getColumnIndex());
        newMaze.getMap()[curr.getDepthIndex()][curr.getRowIndex()][curr.getColumnIndex()] = 0;
        ArrayList<Wall> walls = new ArrayList<Wall>();
        walls = addNeighborWalls(newMaze, curr, walls);
        Random rand = new Random();
        while (walls.size() > 0) {
            if (newMaze.getMap()[newMaze.getGoalPosition().getDepthIndex()][newMaze.getGoalPosition().getRowIndex()][newMaze.getGoalPosition().getColumnIndex()] == 0)
                break;
            Wall chosen = walls.get(rand.nextInt(walls.size()));
            if (legalWall(newMaze, chosen)) {
                newMaze.getMap()[((Position3D) chosen.getWall()).getDepthIndex()][chosen.getWall().getRowIndex()][chosen.getWall().getColumnIndex()] = 0;
                if (!inBorders(( (Position3D) chosen.getPositionB()).getDepthIndex(),( (Position3D) chosen.getPositionB()).getRowIndex(), ( (Position3D) chosen.getPositionB()).getColumnIndex(), newMaze.getDepth(), newMaze.getRows(), newMaze.getColumns()) || !inBorders(((Position3D) chosen.getPositionA()).getDepthIndex(),((Position3D) chosen.getPositionA()).getRowIndex(), chosen.getPositionA().getColumnIndex(), newMaze.getDepth(), newMaze.getRows(), newMaze.getColumns())) {
                    walls = addNeighborWalls(newMaze, (Position3D)chosen.getWall(), walls);
                }
                if (inBorders(((Position3D) chosen.getPositionB()).getDepthIndex(), chosen.getPositionB().getRowIndex(), chosen.getPositionB().getColumnIndex(), newMaze.getDepth(), newMaze.getRows(), newMaze.getColumns()) && newMaze.getMap()[((Position3D) chosen.getPositionB()).getDepthIndex()][chosen.getPositionB().getRowIndex()][chosen.getPositionB().getColumnIndex()] == 1) {
                    walls = addNeighborWalls(newMaze, (Position3D) chosen.getPositionB(), walls);
                    newMaze.getMap()[((Position3D) chosen.getPositionB()).getDepthIndex()][chosen.getPositionB().getRowIndex()][chosen.getPositionB().getColumnIndex()] = 0;
                }
                else if (inBorders(((Position3D) chosen.getPositionA()).getDepthIndex(), chosen.getPositionA().getRowIndex(), chosen.getPositionA().getColumnIndex(), newMaze.getDepth(), newMaze.getRows(), newMaze.getColumns()) && newMaze.getMap()[((Position3D) chosen.getPositionA()).getDepthIndex()][chosen.getPositionA().getRowIndex()][chosen.getPositionA().getColumnIndex()] == 1) {
                    walls = addNeighborWalls(newMaze, (Position3D) chosen.getPositionA(), walls);
                    newMaze.getMap()[((Position3D) chosen.getPositionA()).getDepthIndex()][chosen.getPositionA().getRowIndex()][chosen.getPositionA().getColumnIndex()] = 0;
                }
            }
            walls.remove(chosen);
        }
        return newMaze;
    }

    /**
     * adding walls around specific position if it is not out of the maze borders.
     * @param m the relevant maze
     * @param curr the specific cell we want to add the walls around it
     * @param walls list of existed walls
     * @return updated list of walls after the insertions
     */
    private ArrayList<Wall> addNeighborWalls (Maze3D m, Position3D curr, ArrayList<Wall> walls) throws Exception {
        if (inBorders(curr.getDepthIndex(),curr.getRowIndex()-1,curr.getColumnIndex(), m.getDepth(), m.getRows(), m.getColumns())){
            Wall up = new Wall(curr, new Position3D(curr.getDepthIndex(),curr.getRowIndex()-1, curr.getColumnIndex()), new Position3D(curr.getDepthIndex(), curr.getRowIndex()-2, curr.getColumnIndex()));
            walls.add(up);
        }
        if (inBorders(curr.getDepthIndex(),curr.getRowIndex()+1,curr.getColumnIndex(), m.getDepth(), m.getRows(), m.getColumns())){
            Wall down = new Wall(curr, new Position3D(curr.getDepthIndex(),curr.getRowIndex()+1, curr.getColumnIndex()), new Position3D(curr.getDepthIndex(), curr.getRowIndex()+2, curr.getColumnIndex()));
            walls.add(down);
        }
        if (inBorders(curr.getDepthIndex(), curr.getRowIndex(),curr.getColumnIndex()-1, m.getDepth(), m.getRows(), m.getColumns())){
            Wall left = new Wall(curr, new Position3D(curr.getDepthIndex(),curr.getRowIndex(), curr.getColumnIndex()-1), new Position3D(curr.getDepthIndex(), curr.getRowIndex(), curr.getColumnIndex()-2));
            walls.add(left);
        }
        if (inBorders(curr.getDepthIndex(), curr.getRowIndex(),curr.getColumnIndex()+1, m.getDepth(), m.getRows(), m.getColumns())){
            Wall right = new Wall(curr, new Position3D(curr.getDepthIndex(),curr.getRowIndex(), curr.getColumnIndex()+1), new Position3D(curr.getDepthIndex(), curr.getRowIndex(), curr.getColumnIndex()+2));
            walls.add(right);
        }
        if (inBorders(curr.getDepthIndex()+1,curr.getRowIndex(),curr.getColumnIndex(), m.getDepth(), m.getRows(), m.getColumns())){
            Wall high = new Wall(curr, new Position3D(curr.getDepthIndex()+1,curr.getRowIndex(), curr.getColumnIndex()), new Position3D(curr.getDepthIndex()+2, curr.getRowIndex(), curr.getColumnIndex()));
            walls.add(high);
        }
        if (inBorders(curr.getDepthIndex()-1,curr.getRowIndex(),curr.getColumnIndex(), m.getDepth(), m.getRows(), m.getColumns())){
            Wall low = new Wall(curr, new Position3D(curr.getDepthIndex()-1,curr.getRowIndex(), curr.getColumnIndex()), new Position3D(curr.getDepthIndex()-2, curr.getRowIndex(), curr.getColumnIndex()));
            walls.add(low);
        }
        return walls;
    }

    /**
     * the function checks if one side of the wall is visited and the other is not.
     * @param m the relevant maze
     * @param w the wall we want to check
     * @return true if the wall is legal (one side visited)
     */
    private boolean legalWall(Maze3D m, Wall w){
        if (!inBorders(((Position3D)w.getPositionA()).getDepthIndex(), (w.getPositionA()).getRowIndex(), (w.getPositionA()).getColumnIndex(), m.getDepth(), m.getRows(), m.getColumns()) || m.getMap()[((Position3D) w.getPositionA()).getDepthIndex()][w.getPositionA().getRowIndex()][w.getPositionA().getColumnIndex()] == 1)
            if (m.getMap()[((Position3D)w.getPositionB()).getDepthIndex()][w.getPositionB().getRowIndex()][w.getPositionB().getColumnIndex()]==0 && m.getMap()[((Position3D)w.getWall()).getDepthIndex()][w.getWall().getRowIndex()][w.getWall().getColumnIndex()] == 1)
                return true;
        if (!inBorders(((Position3D)w.getPositionB()).getDepthIndex(), (w.getPositionB()).getRowIndex(), (w.getPositionB()).getColumnIndex(), m.getDepth(), m.getRows(), m.getColumns()) || m.getMap()[((Position3D) w.getPositionB()).getDepthIndex()][w.getPositionB().getRowIndex()][w.getPositionB().getColumnIndex()] == 1)
            if (m.getMap()[((Position3D)w.getPositionA()).getDepthIndex()][w.getPositionA().getRowIndex()][w.getPositionA().getColumnIndex()]==0 && m.getMap()[((Position3D)w.getWall()).getDepthIndex()][w.getWall().getRowIndex()][w.getWall().getColumnIndex()] == 1)
                return true;
        return false;
    }
}

