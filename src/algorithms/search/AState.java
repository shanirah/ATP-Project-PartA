package algorithms.search;


import java.io.Serializable;

public abstract class AState implements Serializable {
    private String state;
    private double cost = 0;
    private AState cameFrom;
    private static int order = 0;

    public AState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public AState getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    protected abstract boolean samePosition (AState s);

    public static int getOrder() {
        return order;
    }

    public static void setOrder(int order) {
        AState.order = order;
    }
}

