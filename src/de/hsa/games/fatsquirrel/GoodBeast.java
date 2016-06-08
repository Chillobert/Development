package de.hsa.games.fatsquirrel;

import java.util.logging.Level;

import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.logger.GameLogger;


public class GoodBeast extends Beast{
	private static final GameLogger logger = new GameLogger();
    public final static int energy = 200; 	
    public GoodBeast(int id, int x, int y) {
		super(id, energy, x, y);
		logger.log(Level.FINEST, "Objekt der Klasse GoodBeast wurde erstellt");
	}
    
    @Override
    public void nextStep(EntityContext entCon, XY input) {
	entCon.tryMove((GoodBeast)this, this.getLocation().getRandomVector());
    }
}
