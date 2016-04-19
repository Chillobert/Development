
package prog2_a3;

import java.util.Arrays;


public class FlattenedBoard implements prog2_a3.interfaces.BoardView {
    
    private final prog2_a3.fatsquirrel.core.Board board;
    private final prog2_a3.fatsquirrel.core.Entity[][] flattenedBoard;
    public FlattenedBoard(){
        board = new prog2_a3.fatsquirrel.core.Board();
             flattenedBoard = board.flatten();
    }
    
    @Override
    public prog2_a3.fatsquirrel.core.Entity[][] getBoard(){
        return flattenedBoard;
    }
    
    @Override
    public String toString(){
        return Arrays.deepToString(flattenedBoard);
    }
}
