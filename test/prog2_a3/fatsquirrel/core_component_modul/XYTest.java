package prog2_a3.fatsquirrel.core_component_modul;

import static org.junit.Assert.*;

import org.junit.Test;

import prog2_a3.fatsquirrel.core.XY;

public class XYTest {
	private int[] locTest;
	
	@Test
	public void getPosShouldReturnTheRightValue() {
		int[] locTest = new int[2];
		
		locTest[0] = 1; //X setzen
		locTest[1] = 1; //Y setzen
		
		XY xyTest = new XY(locTest);

		assertEquals(locTest, xyTest.getPos());
		
	}
	
	@Test
	public void moveUpShouldReturnTheRightValue(){
		int[] locTest = new int[2];
		int[] moveUpTestLoc = new int[2];
		
		locTest[0] = 1; //X setzen
		locTest[1] = 1; //Y setzen	
		
		moveUpTestLoc[0] = 1; //X setzen
		moveUpTestLoc[1] = 2; //Y setzen
		
		XY xyTest = new XY(locTest);
		XY moveUpTest = new XY(moveUpTestLoc);
		
		moveUpTest = moveUpTest.moveUp();
		
		assertEquals(xyTest.getY(), moveUpTest.getY());
		
	}
	
	@Test
	public void ReverseShouldReturnTheRightValue(){
		int[] locTest = new int[2];
		
		int[] moveUpTestLoc = new int[2];
		
		locTest[0] = -1; //X setzen
		locTest[1] = -1; //Y setzen	
		
		moveUpTestLoc[0] = 1; //X setzen
		moveUpTestLoc[1] = 1; //Y setzen
		
		XY xyTest = new XY(locTest);
		XY moveUpTest = new XY(moveUpTestLoc);
		
		moveUpTest = moveUpTest.reverse();
		
		assertEquals(xyTest.getX(), moveUpTest.getX());
		assertEquals(xyTest.getY(), moveUpTest.getY());
		
	}

}
