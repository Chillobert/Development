package prog2_a3.fatsquirrel.core;

import java.util.logging.Level;

public class BoardConfig_old {
private XY size = new XY(new int[]{20,20});
private final int amountGoodBeasts = 2;
private final int amountBadBeasts = 2;
private final int amountGoodPlants = 2;
private final int amountBadPlants = 2;
private final int amountWalls = 0;
private static final GameLogger logger = new GameLogger();
private final String masterBotImplsLocation = "prog2_a3.fatsquirrel.botimpls.";
private final String masterBotImpls = "MasterBotImpl1,MasterBotImpl2";
private final int stepsPerRound = 100;

public BoardConfig_old(){
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
            return size.getX()*2 + size.getY()*2;
	}
        public String getMasterBotImpls(){ //war String[]
          //  String[] output = new String[masterBotImpls.length];
          //  for(int i = 0; i < masterBotImpls.length;i++){
             //   output[i] = this.masterBotImplsLocation+masterBotImpls[i];
            //}
            return masterBotImpls;
        }
        public int getStepsPerRounds(){
            return stepsPerRound;
        }
        public String getMasterBotImplsLocation(){
        	return masterBotImplsLocation;
        }
}
