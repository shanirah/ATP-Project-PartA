package algorithms.mazeGenerators;

    public class EmptyMazeGenerator extends AMazeGenerator{
        @Override
        public Maze generate(int rows, int columns) throws Exception {
        if (rows <=1 || columns <=1){
            throw new Exception("minimum size is 2X2");
        }
            return generateEmptyOrFull (rows, columns, 0);
        }
}
