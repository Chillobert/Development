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
    MiniSquirrel miniSquirrel = new MiniSquirrel(4, 100, 6, 6, 3);
    
    
    //Diese Methode soll überprüfen ob beim Aufruf der nextStep Methoden der verschiedenen Entitys auch die dazugehörige tryMove Methode im Entity Context aufgerufen wird
	@Test
	public void checkRightMethodCalls() {
        FlattenedBoard entityContext = EasyMock
                .createMockBuilder(FlattenedBoard.class)
                .withConstructor(board)
                //.addMockedMethod
                .createNiceMock();

		//Überprüfe BadBeast
		badBeast.setTimeout(0);
		badBeast.nextStep(entityContext);
		assertEquals("tryMove(BadBeast badBeast, XY moveDirection)", entityContext.getParams());
		
		//Überprüfe GoodBeast
		goodBeast.setTimeout(0);
		goodBeast.nextStep(entityContext);
		assertEquals("tryMove(GoodBeast goodBeast, XY moveDirection)", entityContext.getParams());

		//Überprüfe MasterSquirrel
		XY direction = new XY(new int[]{0,-1});
		masterSquirrel.nextStep(entityContext, direction);
		assertEquals("tryMove(Mastersquirrel masterBot, XY moveDirection)", entityContext.getParams());
		
		//Überprüfe MiniSquirrel
		miniSquirrel.nextStep(entityContext);
		assertEquals("tryMove(MiniSquirrel miniSquirrel, XY moveDirection)", entityContext.getParams());


		

	}

}
