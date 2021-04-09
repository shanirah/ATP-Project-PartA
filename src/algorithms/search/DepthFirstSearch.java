package algorithms.search;

public class DepthFirstSearch extends ASearchingAlgorithm {
    /**
     * searching in specific problem according to dfs algorithm
     * @param s the problem
     * @return the goal state of the problem
     */
    @Override
    public AState search(ISearchable s) {
        AState current = s.getStartState();
        this.visitedStates.add(current); // start state is visited
        for (AState state : s.getAllSuccessors(current)) {
            state.setCameFrom(current);
            openStack.push(state); // push start state successors to the stack
            this.visitedStates.add(state);
        }
        while (!openStack.isEmpty()) {
            AState popped = popOpenStack(); // pop the first state in the stack
            if (popped.samePosition(s.getGoalState()))
                return popped; // there is no need to continue
            for (AState state : s.getAllSuccessors(popped)) {
                if (!isVisitedState(visitedStates,state)){
                    state.setCameFrom(popped);
                    this.visitedStates.add(state);
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
