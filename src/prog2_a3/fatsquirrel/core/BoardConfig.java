package prog2_a3.fatsquirrel.core;

public class BoardConfig {
private final XY size = new XY(new int[]{10,10});
private final int amountGoodBeasts = 2;
private final int amountBadBeasts = 2;
private final int amountGoodPlants = 2;
private final int amountBadPlants = 2;
private final int amountWalls = 0;

	public int getLength(){
            return size.getX();
	}
	public int getWidth(){
            return size.getY();
	}
        public XY getSize(){
            return size;
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
