package prog2_a3;

public class BoardConfig {
private final int length = 40;
private final int width = 20;
private final int amountGoodBeasts = 2;
private final int amountBadBeasts = 2;
private final int amountGoodPlants = 2;
private final int amountBadPlants = 2;
private final int amountWalls = 2*length + 2*width;

	public int getLength(){
		return length;
	}
	public int getWidth(){
		return width;
	}
	public int getAmountGoodBeasts(){
		return amountGoodBeasts;
	}
	public int getAmountBadBeasts(){
		return amountBadBeasts;
	}
	public int getAmountGoodPlants(){
		return amountGoodPlants;
	}
	public int getAmountBadPlants(){
		return amountBadPlants;
	}
	public int getAmountWalls(){
		return amountWalls;
	}
}
