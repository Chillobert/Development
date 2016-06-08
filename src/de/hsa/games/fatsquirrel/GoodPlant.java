package de.hsa.games.fatsquirrel;

import java.util.logging.Level;

import de.hsa.games.fatsquirrel.logger.GameLogger;


public class GoodPlant extends Plant{
	private static final GameLogger logger = new GameLogger();
	public final static int energy = 100; 

	public GoodPlant(int id, int x, int y) {
		super(id, energy, x, y);
		logger.log(Level.FINEST, "Objekt der Klasse GoodPlant wurde erstellt");
	}
}
