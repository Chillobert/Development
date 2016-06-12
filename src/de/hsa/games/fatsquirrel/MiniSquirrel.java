package de.hsa.games.fatsquirrel;

import java.util.logging.Level;

import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.logger.GameLogger;

// TODO: Auto-generated Javadoc
/**
 * The Class MiniSquirrel.
 */
public class MiniSquirrel extends PlayerEntity{
	
	/** The patron id. */
	private int patronId;
	
	/** The Constant logger. */
	private static final GameLogger logger = new GameLogger();
    
    /**
     * Instantiates a new mini squirrel.
     *
     * @param id the id
     * @param energy the energy
     * @param x the x
     * @param y the y
     * @param patronId the patron id
     */
    public MiniSquirrel(int id, int energy, int x, int y, int patronId) {
        super(id, energy, x, y);
        this.patronId = patronId;
        logger.log(Level.FINEST, "Objekt der Klasse MiniSquirrel wurde erstellt");
    }
        
    /**
     * Gets the patron id.
     *
     * @return the patron id
     */
    public int getPatronId(){
        return patronId;
    }

    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.Entity#nextStep(de.hsa.games.fatsquirrel.core.EntityContext, de.hsa.games.fatsquirrel.XY)
     */
    @Override
    public void nextStep(EntityContext entCon, XY input) {
        entCon.tryMove(this, this.getLocation().getRandomVector());
    } 
}
