package prog2_a3.fatsquirrel_integration;

import static org.easymock.EasyMock.createMockBuilder;
import static org.junit.Assert.*;

import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;

import prog2_a3.fatsquirrel.core.Board;
import prog2_a3.fatsquirrel.core.BoardConfig;
import prog2_a3.fatsquirrel.core.FlattenedBoard;
import prog2_a3.fatsquirrel.core.GuidedMasterSquirrel;
import prog2_a3.fatsquirrel.core.XY;



public class MasterCollisionWithWallTest {
    private BoardConfig boardConfig;
    private Board board;		

    @Before
    public void setup(){
    	boardConfig = new BoardConfig();
    	board = new Board(boardConfig,masterSquirrel,null,null,null,null,null,null);

    }

    @TestSubject
    GuidedMasterSquirrel masterSquirrel = new GuidedMasterSquirrel(1,3,3);
    
	//Diese Methode �berpr�ft das Bewegungsverhalten des MasterSquirrels im Falle einer Kollision mit einer Mauer sollte es sich 3 Runden lang nicht mehr bewegen k�nnen
		@Test
		public void SquirrelShouldWaitThreeRoundsAfterWallCollisionTillMovement(){
			FlattenedBoard flattenedBoard = createMockBuilder(FlattenedBoard.class)
	                .withConstructor(board)
	                .createMock();
			XY input = new XY(new int[]{0,-1});	
			int coll_Counter = 0;
			
			//MasterSquirrel wird solange nach oben bewegt bis es mit einer Wand kollidiert => Bei Kollision wird Timeout auf 3 gesetzt und das MasterSquirrel verliert 10 Energie
			for (int i = 0; i < 100; i++) {
				if(flattenedBoard.getEntitySet().getEntityArray()[0].getLocation().getY() == 1){
					coll_Counter = coll_Counter + 1;
					if(coll_Counter == 2){
						assertEquals(3, flattenedBoard.getEntitySet().getEntityArray()[0].getTimeout()); //Timeout = 3?
						assertEquals(990, flattenedBoard.getEntitySet().getEntityArray()[0].getEnergy()); //Energy = 990?
						i = 100;
					}
				}
				try {
					flattenedBoard.getEntitySet().nextStepAll(flattenedBoard, input);
				} catch (InterruptedException e) {
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
					e.printStackTrace();
				}
				assertEquals(penalty_Counter, flattenedBoard.getEntitySet().getEntityArray()[0].getTimeout()); //Timeout soll pro Schleifendurchlauf um 1 verringert werden
				
			};

			
			//Wenn MasterSquirrel Penalty gleich 0 ist sollte das MasterSquirrel beim n�chsten Aufruf von nextStepAll seine Location �ndern;
			assertEquals(0, flattenedBoard.getEntitySet().getEntityArray()[0].getTimeout());
			
			try {
				int[] oldLoc_Master = new int[]{flattenedBoard.getEntitySet().getEntityArray()[0].getLocation().getX(),flattenedBoard.getEntitySet().getEntityArray()[0].getLocation().getY()};
				flattenedBoard.getEntitySet().nextStepAll(flattenedBoard, input);
				int[] newLoc_Master = new int[]{flattenedBoard.getEntitySet().getEntityArray()[0].getLocation().getX(),flattenedBoard.getEntitySet().getEntityArray()[0].getLocation().getY()};
				
				assertNotNull(newLoc_Master); //neue Loc sollte nicht null sein
				assertNotEquals(oldLoc_Master, newLoc_Master); //Location sollte unterschiedlich sein
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}

}
