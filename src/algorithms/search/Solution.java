package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

public class Solution implements Serializable {
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
