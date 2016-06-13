package de.hsa.games.fatsquirrel;

import java.util.logging.Level;

import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.core.MiniSquirrelBot;
import de.hsa.games.fatsquirrel.logger.GameLogger;

// TODO: Auto-generated Javadoc
/**
 * The Class MasterSquirrel.
 * This Class creates a MasterSquirrel Entity.
 */
public abstract class MasterSquirrel extends PlayerEntity{
	
	/** The Constant logger. */
	private static final GameLogger logger = new GameLogger();
	
	/** The Constant energy. */
	public static final int energy = 1000; 
	
	/**
	 * Instantiates a new master squirrel.
	 *
	 * @param id the id
	 * @param x the x
	 * @param y the y
	 */
	public MasterSquirrel(int id, int x, int y) {
		super(id, energy, x, y);
		logger.log(Level.FINEST, "Objekt der Klasse MasterSquirrel wurde erstellt");
	}
	
	/**
	 * Creates the descendant.
	 *
	 * @param latestId the latest id
	 * @param energy the energy
	 * @param x the x
	 * @param y the y
	 * @return the mini squirrel
	 */
	// Erstellt ein Minisquirrel
        public MiniSquirrel createDescendant(int latestId, int energy, int x, int y){
            MiniSquirrelBot Norbert = new MiniSquirrelBot(latestId+1, energy, getLocation().getX()+x, getLocation().getY()+y, this.getId());
            this.updateEnergy(-energy);
            return Norbert;
	}
	
	/**
	 * Check descendant.
	 *
	 * @param mini the mini
	 * @return true, if successful
	 */
	//Pr�fe ob �bergebenes Entity Object ein Nachfahre ist
	public boolean checkDescendant(MiniSquirrel mini){
            return mini.getPatronId()==this.getId();
                    }
 
    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.Entity#nextStep(de.hsa.games.fatsquirrel.core.EntityContext, de.hsa.games.fatsquirrel.XY)
     */
    public void nextStep(EntityContext entCon,XY direction){
        entCon.tryMove(this, direction);
    }
}

