package algorithms.search;

import java.util.PriorityQueue;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    /**
     * searching in specific problem according to bfs algorithm
     * @param s problem we want to solve
     * @return goal state of the problem
     */
    @Override
    public AState search(ISearchable s) throws Exception {
        if (s == null){
            throw new Exception("ISearchable should not be null");
        }
        initialize(s);
        AState current = s.getStartState();
        openList.add(current); // add start state to the queue
        s.setVisited(current);
        while (!openList.isEmpty()) {
            AState popped = popOpenList(); // pop the first state in the queue
            AState.setOrder(AState.getOrder() + 1);
            if (popped.samePosition(s.getGoalState()))
                return popped; // there is no need to continue
            for (AState state : s.getAllSuccessors(popped)) {
                if (!s.isVisited(state)) {
                    state.setCameFrom(popped);
                    updateCost(state);
                    openList.add(state);
                    s.setVisited(state);
                }
            }
        }
        return null;
    }

    /**
     * update cost of state to 0
     * @param state the state we want to update
     */
    protected void updateCost(AState state){
        state.setCost(AState.getOrder() + 1);
    }


    /**
     * algorithm name
     * @return breadth first search
     */
    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }
}
