package de.hsa.games.fatsquirrel;

import java.util.logging.Level;

import de.hsa.games.fatsquirrel.logger.GameLogger;


/**
 * The Class GuidedMasterSquirrel.
 * This Class creates a GuidedMasterSquirrel Entity.
 */
public class GuidedMasterSquirrel extends MasterSquirrel {
	
	/** The Constant logger. */
	private static final GameLogger logger = new GameLogger();
    
    /** The in. */
    public String in;
    
    /**
     * Instantiates a new guided master squirrel.
     *
     * @param id the id
     * @param x the x
     * @param y the y
     */
    public GuidedMasterSquirrel(int id, int x, int y){
        super(id,x,y);
        logger.log(Level.FINEST, "Objekt der Klasse GuidedMasterSquirrel wurde erstellt");
    }    
}
