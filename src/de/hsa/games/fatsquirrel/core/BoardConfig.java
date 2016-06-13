package de.hsa.games.fatsquirrel.core;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.logger.GameLogger;

// TODO: Auto-generated Javadoc
/**
 * The Class BoardConfig.
 */
public class BoardConfig {
	
	/** The prop. */
	Properties prop = new Properties();
	
	/** The input. */
	InputStream input = null;
	
	/** The size. */
	private XY size;
	
	/** The amount good beasts. */
	private int amountGoodBeasts;
	
	/** The amount bad beasts. */
	private int amountBadBeasts;
	
	/** The amount good plants. */
	private int amountGoodPlants;
	
	/** The amount bad plants. */
	private int amountBadPlants;
	
	/** The amount walls. */
	private int amountWalls;
	
	/** The master bot impls location. */
	private String masterBotImplsLocation;
	
	/** The master bot impls. */
	private String[] masterBotImpls;
	
	/** The steps per round. */
	private int stepsPerRound;
        
        /** The File for HighScore with complete path*/
        private String highScoreFile;
	
	/** The log. */
	private GameLogger log = new GameLogger();
	
	/**
	 * Instantiates a new board config.
	 */
	public BoardConfig(){

		this.getProps();
		
	}

	/**
	 * Gets the props.
	 *
	 * @return the props
	 */
	public void getProps(){
		try {
			//Stream erstellen
			input = new FileInputStream("config.properties");
			// Auslesen
			prop.load(input);
			// Verarbeiten
			this.setSize(Integer.parseInt(prop.getProperty("size_length")), Integer.parseInt(prop.getProperty("size_width")));
			this.setAmountGoodBeasts(Integer.parseInt(prop.getProperty("amountGoodBeasts")));
			this.setAmountBadBeasts(Integer.parseInt(prop.getProperty("amountBadBeasts")));
			this.setAmountGoodPlants(Integer.parseInt(prop.getProperty("amountGoodPlants")));
			this.setAmountBadPlants(Integer.parseInt(prop.getProperty("amountBadPlants")));
			this.setAmountWalls();
			this.setStepsPerRound(Integer.parseInt(prop.getProperty("stepsPerRound")));
			this.setMasterBotImplsLocation(prop.getProperty("masterBotImplsLocation"));
			this.setMasterBotImpls(prop.getProperty("masterBotImpls"));
                        this.setHighscoreFile(prop.getProperty("HighscoreFile"));
		} catch (IOException ex) {
			log.log(Level.WARNING, "config.properties konnte nicht geladen werden, alternativ-Klasse wird geladen");
			BoardConfig_old alternative = new BoardConfig_old();
			this.setSize(alternative.getSize().getX(), alternative.getSize().getY());
			this.setAmountGoodBeasts(alternative.getAmountGoodBeasts());
			this.setAmountBadBeasts(alternative.getAmountBadBeasts());
			this.setAmountGoodPlants(alternative.getAmountGoodPlants());
			this.setAmountBadPlants(alternative.getAmountBadPlants());
			this.setAmountWalls();
			this.setStepsPerRound(alternative.getStepsPerRounds());
			this.setMasterBotImplsLocation(alternative.getMasterBotImplsLocation());
			this.setMasterBotImpls(alternative.getMasterBotImpls());
                        this.setHighscoreFile(alternative.getHighscoreFile());
			log.log(Level.INFO, "alternativ-Klasse erfolgreich geladen");
		} finally {
			if (input != null) {
				try {
					input.close();
					log.log(Level.INFO, "config.properties erfolgreich geladen");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
		
  }
	
	/**
	 * Prints the properties.
	 */
	public void printProperties(){
		System.out.println("size:" + this.size.toString());
		System.out.println("goodbeasts:" + this.amountGoodBeasts);
		System.out.println("badbeasts:" + this.amountBadBeasts);
		System.out.println("goodplants" + this.amountGoodPlants);
		System.out.println("badplants" + this.amountBadPlants);
		System.out.println("walls:" + this.amountWalls);
		System.out.println("stepsPerRound" + this.stepsPerRound);
		System.out.println("masterBotLocation" + this.masterBotImplsLocation);
		System.out.println("masterBots:" + this.masterBotImpls.toString());
		
	}
	
	/**
	 * Sets the size.
	 *
	 * @param length the length
	 * @param width the width
	 */
	private void setSize(int length, int width){
		this.size = new XY(new int[]{length,width});
	}
	
	/**
	 * Sets the amount good beasts.
	 *
	 * @param amount the new amount good beasts
	 */
	private void setAmountGoodBeasts(int amount){
		this.amountGoodBeasts = amount;
	}
	
	/**
	 * Sets the amount bad beasts.
	 *
	 * @param amount the new amount bad beasts
	 */
	private void setAmountBadBeasts(int amount){
		this.amountBadBeasts = amount;
	}	
	
	/**
	 * Sets the amount good plants.
	 *
	 * @param amount the new amount good plants
	 */
	private void setAmountGoodPlants(int amount){
		this.amountGoodPlants = amount;
	}
	
	/**
	 * Sets the amount bad plants.
	 *
	 * @param amount the new amount bad plants
	 */
	private void setAmountBadPlants(int amount){
		this.amountBadPlants = amount;
	}	
	
	/**
	 * Sets the amount walls.
	 */
	private void setAmountWalls(){
		this.amountWalls = size.getX()*2 + size.getY()*2;
	}		
	
	/**
	 * Sets the steps per round.
	 *
	 * @param amount the new steps per round
	 */
	private void setStepsPerRound(int amount){
		this.stepsPerRound = amount;
	}	
	
	/**
	 * Sets the master bot impls location.
	 *
	 * @param path the new master bot impls location
	 */
	private void setMasterBotImplsLocation(String path){
		this.masterBotImplsLocation = path;
	}
	
	/**
	 * Sets the master bot impls.
	 *
	 * @param masterBotImpls the new master bot impls
	 */
        
        private void setMasterBotImpls(String masterBotImpls){
            String[] splitter = masterBotImpls.split(",");
            this.masterBotImpls = new String[splitter.length];
		for (int i = 0; i < splitter.length; i++) {
                    this.masterBotImpls[i] = splitter[i];
		}
        }
	private void setMasterBotImpls(String[] masterBotImpls){

                this.masterBotImpls = masterBotImpls;
		
	}
        
        private void setHighscoreFile(String highscoreFile){
            this.highScoreFile = highscoreFile;
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
    public String[] getMasterBotImpls(){
        String[] output = new String[masterBotImpls.length];

        for(int i = 0; i < masterBotImpls.length;i++){
            output[i] = this.masterBotImplsLocation+"."+masterBotImpls[i];
        }
        return output;
    }
    
    /**
     * Gets the path of master bot impls.
     *
     * @return the path of master bot impls
     */
    public String getMasterBotImplsLocation(){
        return this.masterBotImplsLocation;
    }
    
    /**
     * Gets the steps per rounds.
     *
     * @return the steps per rounds
     */
    public int getStepsPerRounds(){
        return stepsPerRound;
    }
    
    public String getHighscoreFile(){
        return this.highScoreFile;
    }
	
	
}
