package prog2_a3.fatsquirrel.core_Unit;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.easymock.TestSubject;
import org.hamcrest.core.IsAnything;
import org.junit.*;
import org.junit.Test;

import prog2_a3.fatsquirrel.core.FlattenedBoard;
import prog2_a3.fatsquirrel.core.GoodBeast;
import prog2_a3.fatsquirrel.core.GuidedMasterSquirrel;
import prog2_a3.fatsquirrel.core.MiniSquirrel;
import prog2_a3.fatsquirrel.core.XY;
import prog2_a3.fatsquirrel.core.BadBeast;
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
        FlattenedBoard flattenedBoard = EasyMock
                .createMockBuilder(FlattenedBoard.class)
                .withConstructor(board)
                .createNiceMock();
        
        EntityMethodProxy proxy = new EntityMethodProxy();
        
		//Überprüfe BadBeast
        assertEquals("BadBeast", proxy.generateNextStep(badBeast)); 
		
		//Überprüfe GoodBeast
        assertEquals("GoodBeast", proxy.generateNextStep(goodBeast));
        
        //Überprüfe MasterSquirrel
		assertEquals("MasterSquirrel", proxy.generateNextStep(masterSquirrel));
		
		//Überprüfe MiniSquirrel
		assertEquals("MiniSquirrel", proxy.generateNextStep(miniSquirrel));
	}

}
