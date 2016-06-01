package prog2_a3.fatsquirrel.core;

import java.util.logging.Level;

public class BoardConfig {
private final XY size = new XY(new int[]{20,20});
private final int amountGoodBeasts = 2;
private final int amountBadBeasts = 2;
private final int amountGoodPlants = 2;
private final int amountBadPlants = 2;
private final int amountWalls = 0;
private static final GameLogger logger = new GameLogger();
private final String[] MasterBotImpls = new String[]{"MasterBotImpl1","MasterBotImpl2"};
private final String[] MiniBotImpl = new String[]{"MiniBotImpl1"};

public BoardConfig(){
	logger.log(Level.FINEST, "Object der Klasse BoardConfig erstellt");
}

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
        public String[] getMasterBotImpls(){
            return this.MasterBotImpls;
        }
}
