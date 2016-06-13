package de.hsa.games.fatsquirrel;

import java.util.logging.Level;

import de.hsa.games.fatsquirrel.logger.GameLogger;


// TODO: Auto-generated Javadoc
/**
 * The Class GoodPlant.
 * This Class creates a GoodPlant Entity.
 */
public class GoodPlant extends Plant{
	
	/** The Constant logger. */
	private static final GameLogger logger = new GameLogger();
	
	/** The Constant energy. */
	public final static int energy = 100; 

	/**
	 * Instantiates a new good plant.
	 *
	 * @param id the id
	 * @param x the x
	 * @param y the y
	 */
	public GoodPlant(int id, int x, int y) {
		super(id, energy, x, y);
		logger.log(Level.FINEST, "Objekt der Klasse GoodPlant wurde erstellt");
	}
}
