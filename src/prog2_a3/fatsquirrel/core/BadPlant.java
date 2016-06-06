package prog2_a3.fatsquirrel.core;

import java.util.logging.Level;

import prog2_a3.interfaces.EntityContext;


public class BadPlant extends Plant{
	public final static int energy = -100; 
	private static final GameLogger logger = new GameLogger();
	public BadPlant(int id, int x, int y) {
		super(id, energy, x, y);
		logger.log(Level.FINEST, "Object der Klasse BadPlant erstellt");
		// TODO Auto-generated constructor stub
	}


}
