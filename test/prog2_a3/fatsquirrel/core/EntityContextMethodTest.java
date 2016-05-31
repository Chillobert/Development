package prog2_a3.fatsquirrel.core;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.easymock.TestSubject;
import org.hamcrest.core.IsAnything;
import org.junit.*;
import org.junit.Test;

import prog2_a3.fatsquirrel.core.FlattenedBoard;
import prog2_a3.fatsquirrel.core.Board;
import prog2_a3.fatsquirrel.core.BoardConfig;
import prog2_a3.interfaces.EntityContext;
//Dieser Testcase soll überprüfen ob die richtigen Methoden im EntityContext aufgerufen werden => tryMove mit richtigen Parametern
public class EntityContextMethodTest {

    private BoardConfig boardConfig;
    private Board board;	
    
    
    @Before
    public void setup() {
        boardConfig = new BoardConfig();
        board = new Board(boardConfig);

    }
    
    @TestSubject
    BadBeast badBeast = new BadBeast(1,1, 1);
    GoodBeast goodBeast = new GoodBeast(2,3,3);
    GuidedMasterSquirrel masterSquirrel = new GuidedMasterSquirrel(3,5,5);
    
    
    //Diese Methode soll überprüfen ob beim Aufruf der nextStepAll Methode auch die richtigen tryMove Methoden im EntityContext(flattenedBoard) aufgerufen werden
	@Test
	public void checkRightMethodCalls() {
        FlattenedBoard entityContext = EasyMock
                .createMockBuilder(FlattenedBoard.class)
                .withConstructor(board)
                //.addMockedMethod
                .createNiceMock();
        
		boolean fu = false;
		
		badBeast.setTimeout(0);
		
		EasyMock.replay(entityContext);
		
		entityContext.getEntitySet().getEntityArray()[0].nextStep(entityContext);
		
		//badBeast.nextStep(entityContext);
		EasyMock.expectLastCall().once();
		//EasyMock.expectLastCall().anyTimes();

		

		
		
		
 		//entityContext.tryMove((BadBeast)EasyMock.anyObject(), EasyMock.anyObject());
		//EasyMock.expectLastCall();

		

	}

}
