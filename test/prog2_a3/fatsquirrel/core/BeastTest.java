package prog2_a3.fatsquirrel.core;

import static org.junit.Assert.*;

import org.junit.Test;

import prog2_a3.fatsquirrel.core.FlattenedBoard;
import org.easymock.*;
public class BeastTest {

	@Test
	public void BadBeastNextStepPenaltyCheck() {
		FlattenedBoard flattenedBoard = EasyMock.createMock(FlattenedBoard.class);
		BadBeast badTest = new BadBeast(1, 1, 1);
		badTest.setTimeout(4);
		
        for (int j = 0; j <= 20; ++j) {		
		badTest.nextStep(flattenedBoard);
		System.out.println(badTest.getLocation().getX());
		System.out.println(badTest.getLocation().getY());
		System.out.println("/////////////");
        }
		badTest.getEnergy();
		
		//badTest.
		
		fail("Not yet implemented");
	}

}
