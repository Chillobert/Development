package de.hsa.games.fatsquirrel;

import java.util.logging.Level;

import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.logger.GameLogger;


public class Wall extends Entity{
	private static final GameLogger logger = new GameLogger();
    public final static int energy = -10; 	
	
	public Wall(int id, int x, int y) {
		super(id, energy, x, y);
		logger.log(Level.FINEST, "Objekt der Klasse Wall wurde erstellt");
	}

    @Override
    public void nextStep(EntityContext entCon,XY input) {
    } 

}
