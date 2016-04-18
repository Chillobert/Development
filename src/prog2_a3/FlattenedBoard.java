
package prog2_a3;



public class FlattenedBoard implements prog2_a3.interfaces.BoardView {
    
    private final prog2_a3.fatsquirrel.core.Board board;
    private final String[][] flattenedBoard;
    public FlattenedBoard(){
        board = new prog2_a3.fatsquirrel.core.Board();
             flattenedBoard = board.flatten();
    }
    
    public String[][] getBoard(){
        return flattenedBoard;
    }
}
