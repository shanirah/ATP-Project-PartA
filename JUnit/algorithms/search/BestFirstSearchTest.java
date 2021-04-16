package algorithms.search;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BestFirstSearchTest {

    @Test
    void search() throws Exception {
        BestFirstSearch best = new BestFirstSearch();
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(5,5);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        AState goal = best.search(searchableMaze);
        assertNotEquals(null, goal.getCameFrom());
        assertEquals(4, ((MazeState) goal).getPosition().getColumnIndex());
        assertEquals(4, ((MazeState) goal).getPosition().getRowIndex());
    }

    @Test
    void solve() throws Exception {
        BestFirstSearch best = new BestFirstSearch();
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(10,9);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        Solution sol = best.solve(searchableMaze);
        assertNotEquals(0, sol.getSolutionPath().size());
        assertEquals(0,sol.getSolutionPath().get(0).getCost());
    }


    @Test
    void getNumberOfNodesEvaluated() {
        BestFirstSearch best = new BestFirstSearch();
        assertEquals(0, best.getNumberOfNodesEvaluated());
    }


    @Test
    void getName() {
        BestFirstSearch best = new BestFirstSearch();
        assertEquals("BestFirstSearch", best.getName());
    }
}