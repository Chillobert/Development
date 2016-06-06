package prog2_a3.fatsquirrel.core;

import java.util.logging.Level;

import prog2_a3.interfaces.EntityContext;


public class Wall extends Entity{
	private static final GameLogger logger = new GameLogger();
    public final static int energy = -10; 	
	
	public Wall(int id, int x, int y) {
		super(id, energy, x, y);
		logger.log(Level.FINEST, "Objekt der Klasse Wall wurde erstellt");
	}

    @Override
    public void nextStep(EntityContext entCon,XY input) {
    } 

}
