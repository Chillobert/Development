
package prog2_a3;

import java.util.Arrays;
import prog2_a3.fatsquirrel.core.*;


public class FlattenedBoard implements prog2_a3.interfaces.BoardView {
    
 
    private final Entity[][] flattenedBoard;
    public FlattenedBoard(Entity[][] flattenedBoard){
        this.flattenedBoard = flattenedBoard;
    }
    
    @Override
    public prog2_a3.fatsquirrel.core.Entity[][] getBoard(){
        return flattenedBoard;
    }
    
    @Override
    public String toString(){
        return Arrays.deepToString(flattenedBoard);
    }
    
    public Entity getField(int x, int y){
        return this.flattenedBoard[x][y];
    }
}
