package algorithms.search;

public class BestFirstSearch extends BreadthFirstSearch{


    /**
     * updating the cost of state. there is priority to move diagonal.
     * @param state state we want to update
     */
    @Override
    protected void updateCost(AState state) {
        if (state.getState() == "up" || state.getState() == "down" || state.getState() == "left" || state.getState() == "right"){
            state.setCost(state.getCameFrom().getCost()+1);
        }
        else{
            state.setCost(state.getCameFrom().getCost()+1.5);
        }
    }

    /**
     * algorithm name
     * @return best first search
     */
    @Override
    public String getName() {
        return "BestFirstSearch";
    }

}
