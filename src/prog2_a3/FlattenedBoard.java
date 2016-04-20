
package prog2_a3;

import java.util.Arrays;
import prog2_a3.fatsquirrel.core.*;


public class FlattenedBoard implements prog2_a3.interfaces.BoardView, prog2_a3.interfaces.EntityContext {
    
 
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
    	String output = "";
    	
        for (int row = 0; row < flattenedBoard.length; row++) {
            for (int column = 0; column < flattenedBoard[row].length; column++) {
                output += flattenedBoard[row][column] + "\n";
            }
        }
            return output;
    	
    	
    	
        //return Arrays.deepToString(flattenedBoard);
    }
    
    public Entity getField(int x, int y){
        return this.flattenedBoard[x][y];
    }

	@Override
	public XY getSize() {
		return null;
	}

	@Override
	public XY getEntityType(XY xy) {
		
		// Überprüfe Typ der Entity, die an den übergebenen Koordinaten liegt
		return null;
	}

	@Override
	public PlayerEntity nearestPlayerEntity(XY xy) {
		//Übergabe eines XY Objekts => Durchsuche FlattenedBoard nach am nächstenliegenden Object (Return Object)
		
		
		return null;
	}

	@Override
	public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection) {
		// ÜBergabe eines MiniSquirrels und einem Bewegungsvekotr(XY) =>Prüfe FlattenedBoard ob an zu bewegender Stelle ein Object ist.
		
	}

	@Override
	public void tryMove(GoodBeast goodBeast, XY moveDirection) {
		// ÜBergabe eines Goodbeasts und einem Bewegungsvekotr(XY) =>Prüfe FlattenedBoard ob an zu bewegender Stelle ein Object ist.
		
	}

	@Override
	public void tryMove(BadBeast badBeast, XY moveDirection) {
		// ÜBergabe eines BadBeasts und einem Bewegungsvekotr(XY) =>Prüfe FlattenedBoard ob an zu bewegender Stelle ein Object ist.
		
	}

	@Override
	public void tryMove(MasterSquirrel masterBot, XY moveDirection) {
		// ÜBergabe eines MasterSquirrels und einem Bewegungsvekotr(XY) =>Prüfe FlattenedBoard ob an zu bewegender Stelle ein Object ist.
				
	}

	@Override
	public void spawnChildBot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kill(Entity entity) {
		// Lösche Übergebene Entity
		
	}

	@Override
	public void killAndReplace(Entity entity) {
		// Lösche Übergebene Entity und erstelle eine neue Entity vom selben typ an einem zufälligen Ort
	}
}
