package algorithms.search;

import java.util.PriorityQueue;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    /**
     * searching in specific problem according to bfs algorithm
     * @param s problem we want to solve
     * @return goal state of the problem
     */
    @Override
    public AState search(ISearchable s) {
        AState current = s.getStartState();
        openList.add(current); // add start state to the queue
        visitedStates.add(current);
        while (!openList.isEmpty()) {
            AState popped = popOpenList(); // pop the first state in the queue
            if (popped.samePosition(s.getGoalState()))
                return popped; // there is no need to continue
            for (AState state : s.getAllSuccessors(popped)) {
                if (!isVisitedState(visitedStates,state)) {
                    state.setCameFrom(popped);
                    updateCost(state);
                    openList.add(state);
                    visitedStates.add(state);
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
        state.setCost(0);
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
