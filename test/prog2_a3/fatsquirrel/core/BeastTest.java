package prog2_a3.fatsquirrel.core;

import static org.junit.Assert.*;

import org.junit.Test;

import prog2_a3.interfaces.EntityContext;
import org.easymock.*;
import static org.easymock.EasyMock.createMockBuilder;
import org.junit.Assert;

public class BeastTest {
	private int IdCounter = 0;
        private Board board;
        public BeastTest(){
            BoardConfig boardConfig = new BoardConfig();
            this.board = new Board(boardConfig);
        }
	@Test
	public void BadBeastShouldMoveWithPenaltyIsZero() {
		FlattenedBoard flattenedBoard = createMockBuilder(FlattenedBoard.class)
                        .withConstructor(board)
                        .createMock();
		
		EntitySet entitySet = new EntitySet();
		
		BadBeast badTest = new BadBeast(IdCounter++, 2, 2);
		int[] ResultLoc = new int[]{2,1}; //Vermeintlicher Zielvektor 
		
		//System.out.println(badTest.getLocation().getX()+","+badTest.getLocation().getY());
		badTest.setTimeout(0);
		//System.out.println(badTest.getTimeout());
		//flattenedBoard.tryMove(badTest, new XY(new int[]{0,-1})); //Bewege BadBeast nach oben
		
		entitySet.add("BadBeast", 2, 2);
		
		
		for (int i = 3; i >=0; i--) {
			
			
			try {				
				entitySet.nextStepAll(flattenedBoard, null);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			assertEquals(i, entitySet.getEntityArray()[0].getTimeout());
			

		//	badTest.nextStep(flattenedBoard);
		//	System.out.println(badTest.getTimeout());
			
		}
	
                
		//badTest.nextStep(flattenedBoard);
        //System.out.println(badTest.getLocation().getX()+","+badTest.getLocation().getY());
		Assert.assertArrayEquals(ResultLoc, badTest.getLocation().getPos());
		
		//badTest.setTimeout(4);
		
		

	}

}
