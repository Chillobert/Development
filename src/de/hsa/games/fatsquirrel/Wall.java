package de.hsa.games.fatsquirrel;

import java.util.logging.Level;

import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.logger.GameLogger;


// TODO: Auto-generated Javadoc
/**
 * The Class Wall.
 */
public class Wall extends Entity{
	
	/** The Constant logger. */
	private static final GameLogger logger = new GameLogger();
    
    /** The Constant energy. */
    public final static int energy = -10; 	
	
	/**
	 * Instantiates a new wall.
	 *
	 * @param id the id
	 * @param x the x
	 * @param y the y
	 */
	public Wall(int id, int x, int y) {
		super(id, energy, x, y);
		logger.log(Level.FINEST, "Objekt der Klasse Wall wurde erstellt");
	}

    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.Entity#nextStep(de.hsa.games.fatsquirrel.core.EntityContext, de.hsa.games.fatsquirrel.XY)
     */
    @Override
    public void nextStep(EntityContext entCon,XY input) {
    } 

}
