package prog2_a3.fatsquirrel.core;

import java.util.logging.Level;

import prog2_a3.interfaces.EntityContext;

public class MiniSquirrel extends PlayerEntity{
	private int patronId;
	private static final GameLogger logger = new GameLogger();
    public MiniSquirrel(int id, int energy, int x, int y, int patronId) {
        super(id, energy, x, y);
        this.patronId = patronId;
        logger.log(Level.FINEST, "Objekt der Klasse MiniSquirrel wurde erstellt");
    }
        
    public int getPatronId(){
        return patronId;
    }

    @Override
    public void nextStep(EntityContext entCon) {
        entCon.tryMove(this, this.getLocation().getRandomVector());
    } 
}
