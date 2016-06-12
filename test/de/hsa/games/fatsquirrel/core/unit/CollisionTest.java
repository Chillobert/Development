package de.hsa.games.fatsquirrel.core.unit;

import static org.easymock.EasyMock.createMockBuilder;
import static org.junit.Assert.*;

import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;

import de.hsa.games.fatsquirrel.BadBeast;
import de.hsa.games.fatsquirrel.BadPlant;
import de.hsa.games.fatsquirrel.GoodBeast;
import de.hsa.games.fatsquirrel.GoodPlant;
import de.hsa.games.fatsquirrel.GuidedMasterSquirrel;
import de.hsa.games.fatsquirrel.MiniSquirrel;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.core.Board;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.core.FlattenedBoard;

public class CollisionTest {
	 private BoardConfig boardConfig;
	 private Board board;		

	    @TestSubject
	    BadBeast badBeast = new BadBeast(1,3,3);
	    GoodBeast goodBeast = new GoodBeast(2,3,3);
	    GoodPlant goodPlant = new GoodPlant(6,4,4);
	    BadPlant badPlant = new BadPlant(7,5,5);
	    GuidedMasterSquirrel masterSquirrel = new GuidedMasterSquirrel(3,4,3);
	    MiniSquirrel miniSquirrel = new MiniSquirrel(4, 100, 6, 6, 1);
	    MiniSquirrel miniSquirrel_Foreign = new MiniSquirrel (5,100,6,6,2);
	    
	//Diese Methode �berpr�ft die Kollision zwischen einem MasterSquirrel und einem BadBeast    
	@Test
	public void CollisionBetweenMasterAndBadBeast() {
	    badBeast = new BadBeast(1,3,3);
	    masterSquirrel = new GuidedMasterSquirrel(3,3,4);
	    
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
	    badBeast = new BadBeast(1,3,4);
	    masterSquirrel = new GuidedMasterSquirrel(2,3,3);
	    
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
	    goodBeast = new GoodBeast(1,3,3);
	    masterSquirrel = new GuidedMasterSquirrel(3,3,4);
	    
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
	    goodPlant = new GoodPlant(1,3,3);
	    masterSquirrel = new GuidedMasterSquirrel(3,3,4);
	    
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
		badPlant = new BadPlant(1,3,3);
	    masterSquirrel = new GuidedMasterSquirrel(3,3,4);
	    
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
	
	
	//Diese Methode �berpr�ft die Kollision zwischen einem MasterSquirrel und seinem MiniSquirrel(MiniSquirrel hat 200Energie bei der Erzeugung)
	@Test
	public void CollisionBetweenMasterAndOwnMini(){
		miniSquirrel = new MiniSquirrel(1,200,3,3,1);
                masterSquirrel = new GuidedMasterSquirrel(2,3,4);
	    
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
	
	//Diese Methode �berpr�ft die Kollision zwischen einem MasterSquirrel und einer Wall
	@Test
	public void CollisionBetweenMasterAndWall(){

	    masterSquirrel = new GuidedMasterSquirrel(2,3,1);
	    
    	boardConfig = new BoardConfig();
    	board = new Board(boardConfig,masterSquirrel,null,null,null,null,null,null);
    	
		FlattenedBoard flattenedBoard = createMockBuilder(FlattenedBoard.class)
                .withConstructor(board)
                .createMock();
		
		XY moveUp = new XY(new int[]{0,-1});		
		
		try {
			flattenedBoard.getEntitySet().nextStepAll(flattenedBoard, moveUp);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Sofern eine Kollision erfolgt is muss das GuidedMasterSquirrel 10 Energie verloren haben und f�r 3 Runden bet�ubt sein
		assertEquals(990, flattenedBoard.getEntitySet().getEntityArray()[0].getEnergy());
		assertEquals(3, flattenedBoard.getEntitySet().getEntityArray()[0].getTimeout());
		
	}	
	
	
}
