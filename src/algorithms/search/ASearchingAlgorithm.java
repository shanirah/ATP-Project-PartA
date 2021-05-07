package algorithms.search;

import java.util.*;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {

    protected PriorityQueue<AState> openList;
    protected Stack<AState> openStack;
    private int visitedNodes;

    Comparator<AState> comp = new Comparator<AState>() {
        /**
         * comparing States according to the cost
         * @param o1 the first state
         * @param o2 the second state
         * @return the result of the comparison
         */
        @Override
        public int compare(AState o1, AState o2) {
            if (o1.getCost() > o2.getCost())
                return 1;
            else if (o1.getCost() == o2.getCost())
                return 0;
            return -1;
        }
    };

    public ASearchingAlgorithm() {
        openList = new PriorityQueue<AState>(comp);
        openStack = new Stack<AState>();
        visitedNodes = 0;
    }

    /**
     * pops the first state in the queue. this function is used in bfs and best-first-search algorithms
     *
     * @return the first state in the queue
     */
    protected AState popOpenList() {
        visitedNodes++;
        return openList.poll();
    }

    /**
     * pops the first state in the stack. this function is used in dfs algorithms.
     *
     * @return the last state in the stack
     */
    protected AState popOpenStack() {
        visitedNodes++;
        return openStack.pop();
    }

    public PriorityQueue<AState> getOpenList() {
        return openList;
    }

    public int getNumberOfNodesEvaluated() {
        return visitedNodes;
    }

    /**
     * updates the solution of the problem by using search function
     *
     * @param problem the problem we want to solve
     * @return solution to the problem
     */
    @Override
    public Solution solve(ISearchable problem) throws Exception {
        if (problem == null){
            throw new Exception("ISearchable should not be null");
        }
        AState goal = search(problem); // the function search solves problem
        ArrayList<AState> solPath = new ArrayList<AState>();
        AState current = goal;
        while (!current.samePosition(problem.getStartState())) {
            solPath.add(current); // add states to the solution path from goal state until start state
            current = current.getCameFrom();
        }
        solPath.add(problem.getStartState());
        Collections.reverse(solPath);   // change the order to start - goal
        Solution sol = new Solution(solPath); // create solution with updated solution path
        return sol;
    }


    /**
     * with every new solution we want to initialize the visited states
     * @param s problem
     */
    @Override
    public void initialize(ISearchable s) {
        s.newSearch();
    }
}
