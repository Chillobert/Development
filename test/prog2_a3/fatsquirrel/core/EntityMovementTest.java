package prog2_a3.fatsquirrel.core;

import static org.junit.Assert.*;

import org.junit.Test;

import prog2_a3.interfaces.EntityContext;
import org.easymock.*;
import static org.easymock.EasyMock.createMockBuilder;
import org.junit.Assert;

//Dieser Testcase überprüft die Bewegungen der verschiedenen Entitys 
public class EntityMovementTest {
        private Board board;
        public EntityMovementTest(){
            BoardConfig boardConfig = new BoardConfig();
            this.board = new Board(boardConfig);            
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
//Diese Methode überprüft das Bewegungsverhalten des MasterSquirrels, im Falle einer Kollision mit einer Mauer sollte es sich 3 Runden lang nicht mehr bewegen können
	@Test
	public void SquirrelShouldWaitThreeRoundsAfterWallCollisionTillMovement(){
		FlattenedBoard flattenedBoard = createMockBuilder(FlattenedBoard.class)
                .withConstructor(board)
                .createMock();
		XY input = new XY(new int[]{0,-1});	
		int coll_Counter = 0;
		
		//MasterSquirrel wird solange nach oben bewegt bis es mit einer Wand kollidiert => Bei Kollision wird Timeout auf 3 gesetzt
		for (int i = 0; i < 100; i++) {
			if(flattenedBoard.getEntitySet().getEntityArray()[0].getLocation().getY() == 1){
				coll_Counter = coll_Counter + 1;
				if(coll_Counter == 2){
					assertEquals(3, flattenedBoard.getEntitySet().getEntityArray()[0].getTimeout()); //Timeout = 3?
					i = 100;
				}
			}
			try {
				flattenedBoard.getEntitySet().nextStepAll(flattenedBoard, input);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
			
		//MasterSquirrel Penalty sollte mit jedem nextStepAll um 1 verringert werden	
		input = new XY(new int[]{0, 1}); //Squirrel Bewegung wird in die entgegengesetzte Richtung gesetzt
		flattenedBoard.getEntitySet().getEntityArray()[0].setTimeout(3); //setze Timeout auf 3 (Simuliert Kollision mit Wand)
		int penalty_Counter = 3;
		for (int j = 0; j < 3; j++){
			try {
				flattenedBoard.getEntitySet().nextStepAll(flattenedBoard, input);
				penalty_Counter = penalty_Counter - 1;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			assertEquals(penalty_Counter, flattenedBoard.getEntitySet().getEntityArray()[0].getTimeout()); //Timeout soll pro Schleifendurchlauf um 1 verringert werden
			
		};

		
		//Wenn MasterSquirrel Penalty gleich 0 ist sollte das MasterSquirrel beim nächsten Aufruf von nextStepAll seine Location ändern;
		assertEquals(0, flattenedBoard.getEntitySet().getEntityArray()[0].getTimeout());
		
		try {
			int[] oldLoc_Master = new int[]{flattenedBoard.getEntitySet().getEntityArray()[0].getLocation().getX(),flattenedBoard.getEntitySet().getEntityArray()[0].getLocation().getY()};
			flattenedBoard.getEntitySet().nextStepAll(flattenedBoard, input);
			int[] newLoc_Master = new int[]{flattenedBoard.getEntitySet().getEntityArray()[0].getLocation().getX(),flattenedBoard.getEntitySet().getEntityArray()[0].getLocation().getY()};
			
			assertNotNull(newLoc_Master); //neue Loc sollte nicht null sein
			assertNotEquals(oldLoc_Master, newLoc_Master); //Location sollte unterschiedlich sein
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
