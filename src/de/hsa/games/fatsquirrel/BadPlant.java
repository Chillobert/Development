package de.hsa.games.fatsquirrel;

import java.util.logging.Level;

import de.hsa.games.fatsquirrel.logger.GameLogger;


// TODO: Auto-generated Javadoc
/**
 * The Class BadPlant.
 * This Class creates a BadPlant Entity.
 */
public class BadPlant extends Plant{
	
	/** The Constant energy. */
	public final static int energy = -100; 
	
	/** The Constant logger. */
	private static final GameLogger logger = new GameLogger();
	
	/**
	 * Instantiates a new bad plant.
	 *
	 * @param id the id
	 * @param x the x
	 * @param y the y
	 */
	public BadPlant(int id, int x, int y) {
		super(id, energy, x, y);
		logger.log(Level.FINEST, "Object der Klasse BadPlant erstellt");
		// TODO Auto-generated constructor stub
	}


}
