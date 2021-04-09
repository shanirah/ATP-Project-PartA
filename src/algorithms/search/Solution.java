package algorithms.search;

import java.util.ArrayList;

public class Solution {
    private ArrayList<AState> solutionPath;

    public ArrayList<AState> getSolutionPath(){
        return solutionPath;
    }

    public void setSolPath(ArrayList<AState> solutionPath) {
        this.solutionPath = solutionPath;
    }

    public Solution(ArrayList<AState> solutionPath) {
        this.solutionPath = solutionPath;
    }
}
