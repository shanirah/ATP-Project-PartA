package algorithms.search;

import java.util.ArrayList;

public class DepthFirstSearch extends ASearchingAlgorithm {
    /**
     * searching in specific problem according to dfs algorithm
     * @param s the problem
     * @return the goal state of the problem
     */
    @Override
    public AState search(ISearchable s) {
        initialize(s);
        AState current = s.getStartState();
        s.setVisited(current); // start state is visited
        for (AState state : s.getAllSuccessors(current)) {
            state.setCameFrom(current);
            openStack.push(state); // push start state successors to the stack
            s.setVisited(state);
        }
        while (!openStack.isEmpty()) {
            AState popped = popOpenStack(); // pop the first state in the stack
            if (popped.samePosition(s.getGoalState()))
                return popped; // there is no need to continue
            for (AState state : s.getAllSuccessors(popped)) {
                if (!s.isVisited(state)){
                    state.setCameFrom(popped);
                    s.setVisited(state);
                    openStack.push(state);
                }
            }
        }
        return null;
    }

    /**
     * algorithm name
     * @return depth first search
     */
    @Override
    public String getName() {
        return "DepthFirstSearch";
    }
}
