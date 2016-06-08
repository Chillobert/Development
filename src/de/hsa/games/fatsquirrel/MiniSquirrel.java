package de.hsa.games.fatsquirrel;

import java.util.logging.Level;

import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.logger.GameLogger;

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
    public void nextStep(EntityContext entCon, XY input) {
        entCon.tryMove(this, this.getLocation().getRandomVector());
    } 
}
