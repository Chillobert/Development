package prog2_a3.fatsquirrel.core;

import java.util.logging.Level;

import prog2_a3.interfaces.EntityContext;


public class BadBeast extends Beast{
	private static final GameLogger logger = new GameLogger();
public final static int energy = -150;

    public BadBeast(int id, int x, int y) {
		super(id, energy, x, y);
		logger.log(Level.FINEST, "Object der Klasse BadBeast erstellt");        
	}
        
@Override
    public void nextStep(EntityContext entCon) {
        entCon.tryMove(this, this.getLocation().getRandomVector());
    }        
}