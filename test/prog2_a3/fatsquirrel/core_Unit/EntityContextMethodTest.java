package prog2_a3.fatsquirrel.core_Unit;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.easymock.TestSubject;
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
//Dieser Testcase soll �berpr�fen ob die richtigen Methoden im EntityContext aufgerufen werden => tryMove mit richtigen Parametern
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
    
    
    
    //Diese Methode soll �berpr�fen ob beim Aufruf der nextStep Methoden der verschiedenen Entitys auch die dazugeh�rige tryMove Methode im Entity Context aufgerufen wird
	@Test
	public void checkRightMethodCalls() {
        FlattenedBoard flattenedBoard = EasyMock
                .createMockBuilder(FlattenedBoard.class)
                .withConstructor(board)
                .createNiceMock();
        
        EntityMethodProxy proxy = new EntityMethodProxy();
        
		//�berpr�fe BadBeast
        assertEquals("BadBeast", proxy.generateNextStep(badBeast)); 
		
		//�berpr�fe GoodBeast
        assertEquals("GoodBeast", proxy.generateNextStep(goodBeast));
        
        //�berpr�fe MasterSquirrel
		assertEquals("MasterSquirrel", proxy.generateNextStep(masterSquirrel));
		
		//�berpr�fe MiniSquirrel
		assertEquals("MiniSquirrel", proxy.generateNextStep(miniSquirrel));
	}

}
