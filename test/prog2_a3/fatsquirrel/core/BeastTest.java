package prog2_a3.fatsquirrel.core;

import static org.junit.Assert.*;

import org.junit.Test;

import prog2_a3.interfaces.EntityContext;
import org.easymock.*;
import static org.easymock.EasyMock.createMockBuilder;
import org.junit.Assert;

//Diese TestCase überprüft ob der PenaltyCounter des BadBeasts durch den Aufruf der NextStepAll Methode richtig dekrementiert wird und die Bewegung bei Penalty = 0 durchgeführt wird:
//Bei jedem Aufruf der nextStepAll wird der PenaltyCounter des BadBeasts um 1 verringert. 
public class BeastTest {
        private Board board;
        public BeastTest(){
            BoardConfig boardConfig = new BoardConfig();
            this.board = new Board(boardConfig);
        }
	@Test
	public void BadBeastShouldMoveWhenPenaltyIsZero() {
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
		
		
		assertNotEquals(oldLoc_Bad, newLoc_Bad);
		assertNotEquals(oldLoc_Good, newLoc_Good);

	}

}
