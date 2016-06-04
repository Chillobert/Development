package prog2_a3.fatsquirrel.core_Unit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import prog2_a3.fatsquirrel.core.Board;
import prog2_a3.fatsquirrel.core.BoardConfig;
import prog2_a3.fatsquirrel.core.FlattenedBoard;

public class BoardTest {
	
    private BoardConfig boardConfig;
    private Board board;		

    @Before
    public void setup(){
    	boardConfig = new BoardConfig();
    	board = new Board(boardConfig);
    }
    
    //Überprüfe ob die Methode FillBoard auch mit der Anzahl an Entitys befüllt, wie laut BoardConfig vorgegeben sind
	@Test
	public void checkFillBoard() {
		int amount = 0; //Anzahl der Entitys die von der Boardconfig vorgeschrieben werden (1 für MasterSquirrel, 1 für MAsterSquirrelBot)
		amount = boardConfig.getAmountBadBeasts() + boardConfig.getAmountBadPlants() + boardConfig.getAmountGoodBeasts() + boardConfig.getAmountGoodPlants() + boardConfig.getAmountWalls() + 2;
		assertEquals(amount, board.getEntityCount());
	}
	
	//Überprüfe ob die Methode randLoc ein zufälliges int array zurückliefert
	@Test
	public void checkRandLoc(){
		int[] random_One = board.randLoc();	
		assertNotNull(random_One);	
	}
	
	//Überprüfe ob die Methode getEntityCount die richtige Anzahl an Entitys übergibt
	@Test
	public void checkGetEntityCount(){
		int entityCounter = 0;
	for (int i = 0; i < board.getEntitySet().getEntityArray().length; i++) {
		if(board.getEntitySet().getEntityArray()[i] != null){
			entityCounter = entityCounter + 1;	
		}	
	}	
	assertEquals(entityCounter, board.getEntityCount());  
	}
	
	//Überprüfe die Methode flatten ein flattenedBoard generiert dass das selbe Board Objekt enthält
	@Test
	public void testFlatten(){
		FlattenedBoard flattenedBoard = board.flatten();
		assertEquals(this.board, flattenedBoard.getCreatorBoard());
	}
	
	

}
