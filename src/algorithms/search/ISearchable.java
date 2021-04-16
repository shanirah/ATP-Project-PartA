package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {

    AState getStartState() throws Exception;

    AState getGoalState() throws Exception;

    ArrayList<AState> getAllSuccessors(AState e) throws Exception;

    boolean isVisited(AState e);

    void setVisited(AState e);

    void newSearch();
}
