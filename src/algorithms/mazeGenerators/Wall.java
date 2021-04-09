package algorithms.mazeGenerators;

public class Wall {
    Position positionA;
    Position wall;
    Position positionB;

    public Wall(Position positionA, Position wall, Position positionB) {
        this.positionA = positionA;
        this.positionB = positionB;
        this.wall = wall;
    }
    public void setPositionA(Position positionA) {
        this.positionA = positionA;
    }

    public void setPositionB(Position positionB) {
        this.positionB = positionB;
    }

    public Position getPositionA() {
        return positionA;
    }

    public Position getPositionB() {
        return positionB;
    }

    public Position getWall() {
        return wall;
    }
}
