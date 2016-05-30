package prog2_a3.fatsquirrel.core;

import static org.junit.Assert.*;

import org.junit.Test;

import prog2_a3.interfaces.EntityContext;

import org.easymock.*;
public class BeastTest {
	private int IdCounter = 0;

	@Test
	public void BadBeastShouldMoveUp() {
		EntityContext flattenedBoard = (EntityContext)EasyMock.createMock(FlattenedBoard.class);
		BadBeast badTest = new BadBeast(IdCounter++, 2, 2);
		int[] ResultLoc = new int[]{2,1}; //Vermeintlicher Zielvektor 
		
		badTest.move(new XY(new int[]{0, -1}));
		//flattenedBoard.tryMove(badTest, new XY(new int[]{0,-1})); //Bewege BadBeast nach oben
		
		badTest.nextStep(flattenedBoard);
		
		assertEquals(ResultLoc, badTest.getLocation());
		
		//badTest.setTimeout(4);
		
		

	}

}
