package prog2_a3.fatsquirrel.core;

import java.util.logging.Level;

import prog2_a3.interfaces.EntityContext;


public class GoodBeast extends Beast{
	private static final GameLogger logger = new GameLogger();
    public final static int energy = 200; 	
    public GoodBeast(int id, int x, int y) {
		super(id, energy, x, y);
		logger.log(Level.FINEST, "Objekt der Klasse GoodBeast wurde erstellt");
	}
    
    @Override
    public void nextStep(EntityContext entCon, XY input) {
	entCon.tryMove((GoodBeast)this, this.getLocation().getRandomVector());
    }
}
