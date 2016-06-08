package de.hsa.games.fatsquirrel;

import java.util.logging.Level;

import de.hsa.games.fatsquirrel.logger.GameLogger;


public class GuidedMasterSquirrel extends MasterSquirrel {
	private static final GameLogger logger = new GameLogger();
    public String in;
    public GuidedMasterSquirrel(int id, int x, int y){
        super(id,x,y);
        logger.log(Level.FINEST, "Objekt der Klasse GuidedMasterSquirrel wurde erstellt");
    }    
}
