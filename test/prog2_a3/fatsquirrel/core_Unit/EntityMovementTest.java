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

//Dieser Testcase überprüft die Bewegungen der verschiedenen Entitys 
public class EntityMovementTest {
    private BoardConfig boardConfig;
    private Board board;	
    
    
    @Before
    public void setup() {
        boardConfig = new BoardConfig();
        board = new Board(boardConfig);

    }
        
        
//Diese Methode überprüft das Bewegunsverhalten der Beasts, diese dürfen sich nur alle 4 Runden bewegen        
	@Test
	public void BeastsShouldMoveWhenPenaltyIsZero() {
		FlattenedBoard flattenedBoard = createMockBuilder(FlattenedBoard.class)
                        .withConstructor(board)
                        .createMock();
		
		EntitySet entitySet = new EntitySet();
		entitySet.add("BadBeast", 2, 2);
		entitySet.add("GoodBeast", 4, 4);
		
		entitySet.getEntityArray()[1].setTimeout(4);
		
		//Prüfe ob Penalty richtig dekrementiert wird
		for (int i = 3; i >=0; i--) {
			try {				
				entitySet.nextStepAll(flattenedBoard, null);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Mann könnte hier ebenfalls noch die Location abfragen ==> vor nextStepAll abspeicher und danach => dann assertEquals => falls beides gleich haben sie sich nicht bewegt
			assertEquals(i, entitySet.getEntityArray()[0].getTimeout());	
			assertEquals(i, entitySet.getEntityArray()[1].getTimeout());	
		}
		
		
		//Prüfe ob Penalty richtig ist um eine Bewegung durchzuführen (Penalty muss 0 sein)
		assertEquals(0, entitySet.getEntityArray()[0].getTimeout());
		assertEquals(0, entitySet.getEntityArray()[1].getTimeout());
		
		//Sofern die Logik funktioniert, sollten die Beasts sich nun bewegen
		//Vor der Bewegung wird die Location der Beasts gespeichert und nach dem aufruf der nextStepAll mit der neuen location verglichen
		int[] oldLoc_Bad = new int[]{entitySet.getEntityArray()[0].getLocation().getX(),entitySet.getEntityArray()[0].getLocation().getY()};
		int[] oldLoc_Good = new int[]{entitySet.getEntityArray()[1].getLocation().getX(),entitySet.getEntityArray()[1].getLocation().getY()};
		try {
			entitySet.nextStepAll(flattenedBoard, null);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int[] newLoc_Bad = new int[]{entitySet.getEntityArray()[0].getLocation().getX(),entitySet.getEntityArray()[0].getLocation().getY()};
		int[] newLoc_Good = new int[]{entitySet.getEntityArray()[1].getLocation().getX(),entitySet.getEntityArray()[1].getLocation().getY()};
		
		//Prüfe ob neue Position nicht null ist.
		assertNotNull(newLoc_Good);
		assertNotNull(newLoc_Bad);
		
		//Prüfe ob neue_Position != alte_Position (=> Bewegung)
		assertNotEquals(oldLoc_Bad, newLoc_Bad);
		assertNotEquals(oldLoc_Good, newLoc_Good);

	}
//Diese Methode überprüft das Bewegungsverhalten des MasterSquirrels, dieses sollte sich jede Runde bewegen	
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
				int[] oldLoc_Master = new int[]{entitySet.getEntityArray()[0].getLocation().getX(),entitySet.getEntityArray()[0].getLocation().getY()};
				entitySet.nextStepAll(flattenedBoard, input);
				int[] newLoc_Master = new int[]{entitySet.getEntityArray()[0].getLocation().getX(),entitySet.getEntityArray()[0].getLocation().getY()};
				
				assertNotNull(newLoc_Master); //Prüfe ob neue Location nicht Null ist
				assertNotEquals(oldLoc_Master, newLoc_Master); //Prüfe ob oldLoc != newLoc (Falls ja => Bewegung durchgeführt)
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}


	}

	
	
}
