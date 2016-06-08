package de.hsa.games.fatsquirrel;

import java.util.logging.Level;

import de.hsa.games.fatsquirrel.logger.GameLogger;


public class BadPlant extends Plant{
	public final static int energy = -100; 
	private static final GameLogger logger = new GameLogger();
	public BadPlant(int id, int x, int y) {
		super(id, energy, x, y);
		logger.log(Level.FINEST, "Object der Klasse BadPlant erstellt");
		// TODO Auto-generated constructor stub
	}


}
