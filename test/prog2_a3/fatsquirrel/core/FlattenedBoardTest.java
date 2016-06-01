package prog2_a3.fatsquirrel.core;

import static org.easymock.EasyMock.createMockBuilder;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FlattenedBoardTest {
	
    private BoardConfig boardConfig;
    private Board board;		
    private EntitySet entSet;

    @Before
    public void setup(){
    	boardConfig = new BoardConfig();
    	board = new Board(boardConfig);
    	entSet = new EntitySet();
    }
    
    
	@Test
	public void CheckCollisionHandling() {
		FlattenedBoard flattenedBoard = createMockBuilder(FlattenedBoard.class)
                .withConstructor(board)
                .createMock();
		
		entSet.add("BadBeast", 2, 2);
		entSet.add("GuidedMasterSquirrel", 2, 3);
		
		for (int i = 0; i < 4; i++) {
			
		
		try {
			entSet.getEntityArray()[0].setTimeout(0);
			entSet.nextStepAll(flattenedBoard, null);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		}
	}

}
