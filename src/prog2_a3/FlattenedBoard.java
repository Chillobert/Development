
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
        return Arrays.deepToString(flattenedBoard);
    }
    
    public Entity getField(int x, int y){
        return this.flattenedBoard[x][y];
    }

	@Override
	public XY getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XY getEntityType(XY xy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerEntity nearestPlayerEntity(XY xy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tryMove(GoodBeast goodBeast, XY moveDirection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tryMove(BadBeast badBeast, XY moveDirection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tryMove(MasterSquirrel masterBot, XY moveDirection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spawnChildBot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kill(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void killAndReplace(Entity entity) {
		// TODO Auto-generated method stub
		
	}
}
