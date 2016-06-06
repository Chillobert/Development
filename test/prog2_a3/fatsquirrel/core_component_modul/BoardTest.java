package prog2_a3.fatsquirrel.core_component_modul;

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
    
    //�berpr�fe ob die Methode FillBoard auch mit der Anzahl an Entitys bef�llt, wie laut BoardConfig vorgegeben wird-
	@Test
	public void testFillBoard() {
		int amount = 0; //Anzahl der Entitys die von der Boardconfig vorgeschrieben werden (+1 f�r MasterSquirrel, +1 f�r MAsterSquirrelBot)
		amount = boardConfig.getAmountBadBeasts() + boardConfig.getAmountBadPlants() + boardConfig.getAmountGoodBeasts() + boardConfig.getAmountGoodPlants() + boardConfig.getAmountWalls() + boardConfig.getMasterBotImpls().length + 1;
		assertEquals(amount, board.getEntityCount());
	}
	
	//�berpr�fe ob die Methode randLoc ein zuf�lliges int array zur�ckliefert
	@Test
	public void testRandLoc(){
		int[] random_One = board.randLoc();	
		assertNotNull(random_One);	
	}
	
	//�berpr�fe ob die Methode getEntityCount die richtige Anzahl an Entitys �bergibt
	@Test
	public void testGetEntityCount(){
		int entityCounter = 0;
	for (int i = 0; i < board.getEntitySet().getEntityArray().length; i++) {
		if(board.getEntitySet().getEntityArray()[i] != null){
			entityCounter = entityCounter + 1;	
		}	
	}	
	assertEquals(entityCounter, board.getEntityCount());  
	}
	
	//�berpr�fe die Methode flatten ein flattenedBoard generiert dass das selbe Board Objekt enth�lt
	@Test
	public void testFlatten(){
		FlattenedBoard flattenedBoard = board.flatten();
		assertNotNull(flattenedBoard);
		assertEquals(this.board, flattenedBoard.getBoard());
	}
	
	//�berpr�fe ob die Methode getSize() die richtige Gr��e, die von der BoardConfig vorgeschrieben wird �bergibt.
	@Test
	public void testGetSize(){
		assertNotNull(board.getSize()); //Pr�fe ob Board Objekt initalisiert wird
		assertEquals(boardConfig.getSize(),  board.getSize()); //Pr�fe Gr��e
	}
	
	//�berpr�ft ob die Methode getEntitySet() die entitySet soll die Anzahl an Entitys enthalten die vond er BoardConfig vorgegeben werden.
	@Test
	public void testGetEntitySet(){
		assertNotNull(board.getEntitySet());

		int goodBeast_Counter = 0;
	    int badBeast_Counter = 0;
		int goodPlant_Counter = 0;
		int badPlant_Counter = 0;
		int wall_Counter = 0;
		
	    
	for (int i = 0; i < board.getEntitySet().getEntityArray().length; i++) {
		if(board.getEntitySet().getEntityArray()[i] != null){
			switch (board.getEntitySet().getEntityArray()[i].getName()) {
			case "GoodBeast":
				goodBeast_Counter +=1;
				break;
			case "BadBeast":
				badBeast_Counter +=1;
				break;
			case "GoodPlant":
				goodPlant_Counter +=1;
				break;
			case "BadPlant":
				badPlant_Counter +=1;
				break;
			case "Wall":
				wall_Counter +=1;
				break;
			}

			}
		}	
	assertEquals(boardConfig.getAmountGoodBeasts(), goodBeast_Counter);
	assertEquals(boardConfig.getAmountBadBeasts(), badBeast_Counter);
	assertEquals(boardConfig.getAmountGoodPlants(), goodPlant_Counter);
	assertEquals(boardConfig.getAmountBadPlants(), badPlant_Counter);
	assertEquals(boardConfig.getAmountWalls(), wall_Counter);
	}
	

}
