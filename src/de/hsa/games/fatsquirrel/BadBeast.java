package de.hsa.games.fatsquirrel;

import java.util.logging.Level;
import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.logger.GameLogger;


// TODO: Auto-generated Javadoc
/**
 * The Class BadBeast.
 */
public class BadBeast extends Beast{
	
	/** The Constant logger. */
	private static final GameLogger logger = new GameLogger();

/** The Constant energy. */
public final static int energy = -150;

    /**
     * Instantiates a new bad beast.
     *
     * @param id the id
     * @param x the x
     * @param y the y
     */
    public BadBeast(int id, int x, int y) {
		super(id, energy, x, y);
		logger.log(Level.FINEST, "Object der Klasse BadBeast erstellt");        
		super.setTimeout(4);
	}
    
    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.Entity#nextStep(de.hsa.games.fatsquirrel.core.EntityContext, de.hsa.games.fatsquirrel.XY)
     */
    @Override
    public void nextStep(EntityContext entCon, XY input) {
	entCon.tryMove((BadBeast)this, this.getLocation().getRandomVector());
    }
}