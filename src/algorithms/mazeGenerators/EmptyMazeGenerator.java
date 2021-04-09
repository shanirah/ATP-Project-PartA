package algorithms.mazeGenerators;

    public class EmptyMazeGenerator extends AMazeGenerator{
        @Override
        public Maze generate(int rows, int columns) {
            return generateEmptyOrFull (rows, columns, 0);
        }
}
