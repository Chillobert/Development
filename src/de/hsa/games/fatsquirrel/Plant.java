
package de.hsa.games.fatsquirrel;

import java.util.logging.Level;

import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.logger.GameLogger;

// TODO: Auto-generated Javadoc
/**
 * The Class Plant.
 */
public abstract class Plant extends Entity{
	
	/** The Constant logger. */
	private static final GameLogger logger = new GameLogger();
    
    /**
     * Instantiates a new plant.
     *
     * @param id the id
     * @param energy the energy
     * @param x the x
     * @param y the y
     */
    public Plant(int id, int energy, int x, int y){
        super(id,energy,x,y);
        logger.log(Level.FINEST, "Objekt der Klasse Plant wurde erstellt");
    }
    
    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.Entity#nextStep(de.hsa.games.fatsquirrel.core.EntityContext, de.hsa.games.fatsquirrel.XY)
     */
    @Override
    public void nextStep(EntityContext entCon, XY input) {
       
    } 
}
