
package de.hsa.games.fatsquirrel;

import java.util.logging.Level;

import de.hsa.games.fatsquirrel.logger.GameLogger;

// TODO: Auto-generated Javadoc
/**
 * The Class Beast.
 */
public abstract class Beast extends Entity {
	
	/** The Constant logger. */
	private static final GameLogger logger = new GameLogger();
    
    /**
     * Instantiates a new beast.
     *
     * @param id the id
     * @param energy the energy
     * @param x the x
     * @param y the y
     */
    public Beast(int id, int energy, int x, int y){
        super(id,energy,x,y);
        logger.log(Level.FINEST, "Object der Klasse Beast erstellt");
    }
}
