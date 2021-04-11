package algorithms.search;

public interface ISearchingAlgorithm {

    AState search(ISearchable s);

    Solution solve(ISearchable problem);

    String getName();

    int getNumberOfNodesEvaluated();

    void initialize(ISearchable s);
}

