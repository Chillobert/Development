package de.hsa.games.fatsquirrel.core;

import java.util.logging.Level;

import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.logger.GameLogger;

// TODO: Auto-generated Javadoc
/**
 * The Class BoardConfig_old.
 */
public class BoardConfig_old {

/** The size. */
private XY size = new XY(new int[]{20,20});

/** The amount good beasts. */
private final int amountGoodBeasts = 2;

/** The amount bad beasts. */
private final int amountBadBeasts = 2;

/** The amount good plants. */
private final int amountGoodPlants = 2;

/** The amount bad plants. */
private final int amountBadPlants = 2;

/** The amount walls. */
private final int amountWalls = 0;

/** The Constant logger. */
private static final GameLogger logger = new GameLogger();

/** The master bot impls location. */
private final String masterBotImplsLocation = "prog2_a3.fatsquirrel.botimpls.";

/** The master bot impls. */
private final String masterBotImpls = "MasterBotImpl1,MasterBotImpl2";

/** The steps per round. */
private final int stepsPerRound = 100;

/**
 * Instantiates a new board config_old.
 */
public BoardConfig_old(){
	logger.log(Level.FINEST, "Object der Klasse BoardConfig erstellt");
}

	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public int getLength(){
            return size.getX();
	}
	
	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth(){
            return size.getY();
	}
        
        /**
         * Gets the size.
         *
         * @return the size
         */
        public XY getSize(){
            return size;
        }
	
	/**
	 * Gets the amount good beasts.
	 *
	 * @return the amount good beasts
	 */
	public int getAmountGoodBeasts(){
            return amountGoodBeasts;
	}
	
	/**
	 * Gets the amount bad beasts.
	 *
	 * @return the amount bad beasts
	 */
	public int getAmountBadBeasts(){
            return amountBadBeasts;
	}
	
	/**
	 * Gets the amount good plants.
	 *
	 * @return the amount good plants
	 */
	public int getAmountGoodPlants(){
            return amountGoodPlants;
	}
	
	/**
	 * Gets the amount bad plants.
	 *
	 * @return the amount bad plants
	 */
	public int getAmountBadPlants(){
            return amountBadPlants;
	}
	
	/**
	 * Gets the amount walls.
	 *
	 * @return the amount walls
	 */
	public int getAmountWalls(){
            return size.getX()*2 + size.getY()*2;
	}
        
        /**
         * Gets the master bot impls.
         *
         * @return the master bot impls
         */
        public String getMasterBotImpls(){ //war String[]
          //  String[] output = new String[masterBotImpls.length];
          //  for(int i = 0; i < masterBotImpls.length;i++){
             //   output[i] = this.masterBotImplsLocation+masterBotImpls[i];
            //}
            return masterBotImpls;
        }
        
        /**
         * Gets the steps per rounds.
         *
         * @return the steps per rounds
         */
        public int getStepsPerRounds(){
            return stepsPerRound;
        }
        
        /**
         * Gets the master bot impls location.
         *
         * @return the master bot impls location
         */
        public String getMasterBotImplsLocation(){
        	return masterBotImplsLocation;
        }
}
