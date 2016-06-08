package de.hsa.games.fatsquirrel;

import java.util.logging.Level;

import de.hsa.games.fatsquirrel.logger.GameLogger;

public abstract class PlayerEntity extends Entity{
	private static final GameLogger logger = new GameLogger();
    
    public PlayerEntity(int id, int energy, int x, int y){
        super(id,energy,x,y);
        logger.log(Level.FINEST, "Objekt der Klasse PlayerEntity wurde erstellt");
    };
    

}
