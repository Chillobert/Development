package prog2_a3.fatsquirrel.core_Unit;

import static org.easymock.EasyMock.createMockBuilder;
import static org.junit.Assert.*;

import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;

import prog2_a3.fatsquirrel.core.BadBeast;
import prog2_a3.fatsquirrel.core.BadPlant;
import prog2_a3.fatsquirrel.core.Board;
import prog2_a3.fatsquirrel.core.BoardConfig;
import prog2_a3.fatsquirrel.core.FlattenedBoard;
import prog2_a3.fatsquirrel.core.GoodBeast;
import prog2_a3.fatsquirrel.core.GoodPlant;
import prog2_a3.fatsquirrel.core.GuidedMasterSquirrel;
import prog2_a3.fatsquirrel.core.MiniSquirrel;
import prog2_a3.fatsquirrel.core.XY;

public class CollisionTest {
	 private BoardConfig boardConfig;
	    private Board board;		

	    @Before
	    public void setup(){

	    }
	    
	    @TestSubject
	    BadBeast badBeast = new BadBeast(1,3,3);
	    GoodBeast goodBeast = new GoodBeast(2,3,3);
	    GuidedMasterSquirrel masterSquirrel = new GuidedMasterSquirrel(3,4,3);
	    MiniSquirrel miniSquirrel = new MiniSquirrel(4, 100, 6, 6, 3);
	    
	//Diese Methode �berpr�ft die Kollision zwischen einem MasterSquirrel und einem BadBeast    
	@Test
	public void CollisionBetweenMasterAndBadBeast() {
	    BadBeast badBeast = new BadBeast(1,3,3);
	    GuidedMasterSquirrel masterSquirrel = new GuidedMasterSquirrel(3,3,4);
	    
    	boardConfig = new BoardConfig();
    	board = new Board(boardConfig,masterSquirrel,null,null,badBeast,null,null,null);
		
    	
		FlattenedBoard flattenedBoard = createMockBuilder(FlattenedBoard.class)
                .withConstructor(board)
                .createMock();
		XY moveUp = new XY(new int[]{0,-1});	
    	
		
		try {
			flattenedBoard.getEntitySet().nextStepAll(flattenedBoard, moveUp);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	//Sofern eine Kollision erfolgt is muss das GuidedMasterSquirrel 150 Energie verloren haben und das BadBeast -150 Energie haben
		assertEquals(850, flattenedBoard.getEntitySet().getEntityArray()[0].getEnergy());
		assertEquals(-150,flattenedBoard.getEntitySet().getEntityArray()[1].getEnergy());
		
		
	}
	
	//Diese Methode �berpr�ft die Kollision zwischen einem BadBeast und einem MasterSquirrel
	@Test
	public void CollisionBetweenBadBeastandMaster(){
	    BadBeast badBeast = new BadBeast(1,3,4);
	    GuidedMasterSquirrel masterSquirrel = new GuidedMasterSquirrel(2,3,3);
	    
    	boardConfig = new BoardConfig();
    	board = new Board(boardConfig,masterSquirrel,null,null,badBeast,null,null,null);
		
    	
		FlattenedBoard flattenedBoard = createMockBuilder(FlattenedBoard.class)
                .withConstructor(board)
                .createMock();
		
		flattenedBoard.getEntitySet().getEntityArray()[1].setTimeout(0); // Setze TimeOut von BadBeast auf 0 damit es sich beim ersten Aufruf bewegt
		try {
			flattenedBoard.getEntitySet().nextStepAll(flattenedBoard, null);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	//Sofern eine Kollision erfolgt is muss das GuidedMasterSquirrel 150 Energie verloren haben und das BadBeast -150 Energie haben
		assertEquals(850, flattenedBoard.getEntitySet().getEntityArray()[0].getEnergy());
		assertEquals(-150,flattenedBoard.getEntitySet().getEntityArray()[1].getEnergy());
				
	}

	
	//Diese Methode �berpr�ft die Kollision zwischen einem Mastersquirrel und einem GoodBeast
	@Test
	public void CollisionBetweenMasterandGoodBeast(){
	    GoodBeast goodBeast = new GoodBeast(1,3,3);
	    GuidedMasterSquirrel masterSquirrel = new GuidedMasterSquirrel(3,3,4);
	    
    	boardConfig = new BoardConfig();
    	board = new Board(boardConfig,masterSquirrel,null,goodBeast,null,null,null,null);
    	
		FlattenedBoard flattenedBoard = createMockBuilder(FlattenedBoard.class)
                .withConstructor(board)
                .createMock();
		
		XY moveUp = new XY(new int[]{0,-1});	
    	
		try {
			flattenedBoard.getEntitySet().nextStepAll(flattenedBoard, moveUp);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	//Sofern eine Kollision erfolgt is muss das GuidedMasterSquirrel 200 Energie erhalten haben, das bisherige GoodBeast gestorben und an einem anderen Ort neu gespawnt sein
		assertEquals(1200, flattenedBoard.getEntitySet().getEntityArray()[0].getEnergy());
		assertEquals("Wall", flattenedBoard.getEntitySet().getEntityArray()[1].getName()); //An der Stelle wo urspr�nglich das GoodBeast war ist nun eine Wall		
	}
	
	
	//Diese Methode �berpr�ft die Kollision zwischen einem MasterSquirrel und einer GoodPlant
	@Test
	public void CollisionBetweenMasterAndGoodPlant(){
	    GoodPlant goodPlant = new GoodPlant(1,3,3);
	    GuidedMasterSquirrel masterSquirrel = new GuidedMasterSquirrel(3,3,4);
	    
    	boardConfig = new BoardConfig();
    	board = new Board(boardConfig,masterSquirrel,null,null,null,goodPlant,null,null);
    	
		FlattenedBoard flattenedBoard = createMockBuilder(FlattenedBoard.class)
                .withConstructor(board)
                .createMock();
		
		XY moveUp = new XY(new int[]{0,-1});		
		
		try {
			flattenedBoard.getEntitySet().nextStepAll(flattenedBoard, moveUp);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Sofern eine Kollision erfolgt is muss das GuidedMasterSquirrel 100 Energie erhalten haben, die bisherige GoodPlant gestorben und an einem anderen Ort neu gespawnt sein
		assertEquals(1100, flattenedBoard.getEntitySet().getEntityArray()[0].getEnergy());
		assertEquals("Wall", flattenedBoard.getEntitySet().getEntityArray()[1].getName());
		
	}

	//Diese Methode �berpr�ft die Kollision zwischen einem MasterSquirrel und einer BadPlant
	@Test
	public void CollisionBetweenMasterAndBadPlant(){
		BadPlant badPlant = new BadPlant(1,3,3);
	    GuidedMasterSquirrel masterSquirrel = new GuidedMasterSquirrel(3,3,4);
	    
    	boardConfig = new BoardConfig();
    	board = new Board(boardConfig,masterSquirrel,null,null,null,null,badPlant,null);
    	
		FlattenedBoard flattenedBoard = createMockBuilder(FlattenedBoard.class)
                .withConstructor(board)
                .createMock();
		
		XY moveUp = new XY(new int[]{0,-1});		
		
		try {
			flattenedBoard.getEntitySet().nextStepAll(flattenedBoard, moveUp);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Sofern eine Kollision erfolgt is muss das GuidedMasterSquirrel 200 Energie verloren haben, die bisherige BadPlant gestorben und an einem anderen Ort neu gespawnt sein
		assertEquals(900, flattenedBoard.getEntitySet().getEntityArray()[0].getEnergy());
		assertEquals("Wall", flattenedBoard.getEntitySet().getEntityArray()[1].getName());
		
	}	
	
	
	//Diese Methode �berpr�ft die Kollision zwischen einem MasterSquirrel und SEINEM MiniSquirrel
	@Test
	public void CollisionBetweenMasterAndMini(){
		MiniSquirrel miniSquirrel = new MiniSquirrel(1,200,3,3,1);
	    GuidedMasterSquirrel masterSquirrel = new GuidedMasterSquirrel(2,3,4);
	    
    	boardConfig = new BoardConfig();
    	board = new Board(boardConfig,masterSquirrel,null,null,null,null,null,miniSquirrel);
    	
		FlattenedBoard flattenedBoard = createMockBuilder(FlattenedBoard.class)
                .withConstructor(board)
                .createMock();
		
		XY moveUp = new XY(new int[]{0,-1});		
		
		try {
			flattenedBoard.getEntitySet().nextStepAll(flattenedBoard, moveUp);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Sofern eine Kollision erfolgt is muss das GuidedMasterSquirrel 200 Energie bekommen haben, das MiniSquirrel kehrt zur�ck zu seinem Erschaffer
		assertEquals(1200, flattenedBoard.getEntitySet().getEntityArray()[0].getEnergy());
		assertEquals("Wall", flattenedBoard.getEntitySet().getEntityArray()[1].getName());
		
	}	
}
