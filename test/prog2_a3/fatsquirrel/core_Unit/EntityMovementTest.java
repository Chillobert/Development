package prog2_a3.fatsquirrel.core_Unit;

import static org.junit.Assert.*;

import org.junit.Test;

import prog2_a3.fatsquirrel.core.Board;
import prog2_a3.fatsquirrel.core.BoardConfig;
import prog2_a3.fatsquirrel.core.EntitySet;
import prog2_a3.fatsquirrel.core.FlattenedBoard;
import prog2_a3.fatsquirrel.core.XY;
import prog2_a3.interfaces.EntityContext;
import org.easymock.*;
import static org.easymock.EasyMock.createMockBuilder;
import org.junit.Assert;
import org.junit.Before;
import prog2_a3.fatsquirrel.core.Entity;

//Dieser Testcase �berpr�ft die Bewegungen der verschiedenen Entitys 
public class EntityMovementTest {
    private BoardConfig boardConfig;
    private Board board;	
    
    
    @Before
    public void setup() {
        boardConfig = new BoardConfig();
        board = new Board(boardConfig);

    }
        
        
//Diese Methode �berpr�ft das Bewegunsverhalten der Beasts, diese d�rfen sich nur alle 4 Runden bewegen        
	@Test
	public void BeastsShouldMoveWhenPenaltyIsZero() {
		FlattenedBoard flattenedBoard = createMockBuilder(FlattenedBoard.class)
                        .withConstructor(board)
                        .createMock();
		
		EntitySet entitySet = new EntitySet();
		entitySet.add("BadBeast", 2, 2);
		entitySet.add("GoodBeast", 4, 4);
		
		entitySet.getEntityArray()[1].setTimeout(4); //setze auf 4 damit beides in einer Schleife abgehandelt werden kann, Funktion ist die selbe
		
		//Pr�fe ob Penalty richtig dekrementiert wird
		for (int i = 3; i >=0; i--) {
			try {				
				entitySet.nextStepAll(flattenedBoard, null,1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//Mann k�nnte hier ebenfalls noch die Location abfragen ==> vor nextStepAll abspeicher und danach => dann assertEquals => falls beides gleich haben sie sich nicht bewegt
			assertEquals(i, entitySet.getEntityArray()[0].getTimeout());	
			assertEquals(i, entitySet.getEntityArray()[1].getTimeout());	
		}
		
		
		//Pr�fe ob Penalty richtig ist um eine Bewegung durchzuf�hren (Penalty muss 0 sein)
		assertEquals(0, entitySet.getEntityArray()[0].getTimeout());
		assertEquals(0, entitySet.getEntityArray()[1].getTimeout());
		
		//Sofern die Logik funktioniert, sollten die Beasts sich nun bewegen
		//Vor der Bewegung wird die Location der Beasts gespeichert und nach dem aufruf der nextStepAll mit der neuen location verglichen
		int[] oldLoc_Bad = new int[]{entitySet.getEntityArray()[0].getLocation().getX(),entitySet.getEntityArray()[0].getLocation().getY()};
		int[] oldLoc_Good = new int[]{entitySet.getEntityArray()[1].getLocation().getX(),entitySet.getEntityArray()[1].getLocation().getY()};
		try {
			entitySet.nextStepAll(flattenedBoard, null,1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int[] newLoc_Bad = new int[]{entitySet.getEntityArray()[0].getLocation().getX(),entitySet.getEntityArray()[0].getLocation().getY()};
		int[] newLoc_Good = new int[]{entitySet.getEntityArray()[1].getLocation().getX(),entitySet.getEntityArray()[1].getLocation().getY()};
		
		//Pr�fe ob neue Position nicht null ist.
		assertNotNull(newLoc_Good);
		assertNotNull(newLoc_Bad);
		
		//Pr�fe ob neue_Position != alte_Position (=> Bewegung)
		assertNotEquals(oldLoc_Bad, newLoc_Bad);
		assertNotEquals(oldLoc_Good, newLoc_Good);

	}
//Diese Methode �berpr�ft das Bewegungsverhalten des MasterSquirrels, dieses sollte sich jede Runde bewegen	
	@Test
	public void SquirrelShouldMoveEveryLoopRunTrough(){
		FlattenedBoard flattenedBoard = createMockBuilder(FlattenedBoard.class)
                .withConstructor(board)
                .createMock();

		EntitySet entitySet = new EntitySet();		
		entitySet.add("GuidedMasterSquirrel", 2, 2);

		XY input = new XY(new int[]{0,+1});
		
		for (int i = 0; i < 5; i++) {
			try {
				int[] oldLoc_Master = new int[]{((Entity)entitySet.getEntityArray()[0]).getLocation().getX(),entitySet.getEntityArray()[0].getLocation().getY()};
				entitySet.nextStepAll(flattenedBoard, input,1000);
				int[] newLoc_Master = new int[]{((Entity)entitySet.getEntityArray()[0]).getLocation().getX(),entitySet.getEntityArray()[0].getLocation().getY()};
				
				assertNotNull(newLoc_Master); //Pr�fe ob neue Location nicht Null ist
				assertNotEquals(oldLoc_Master, newLoc_Master); //Pr�fe ob oldLoc != newLoc (Falls ja => Bewegung durchgef�hrt)
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			
		}


	}

	
	
}
