package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {

    AState getStartState();

    AState getGoalState();

    ArrayList<AState> getAllSuccessors(AState e);

    boolean isVisited(AState e);

    void setVisited(AState e);

    void newSearch();
}
