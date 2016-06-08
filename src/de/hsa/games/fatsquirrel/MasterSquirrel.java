package de.hsa.games.fatsquirrel;

import java.util.logging.Level;

import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.core.MiniSquirrelBot;
import de.hsa.games.fatsquirrel.logger.GameLogger;

public abstract class MasterSquirrel extends PlayerEntity{
	private static final GameLogger logger = new GameLogger();
	public static final int energy = 1000; 
	public MasterSquirrel(int id, int x, int y) {
		super(id, energy, x, y);
		logger.log(Level.FINEST, "Objekt der Klasse MasterSquirrel wurde erstellt");
	}
	
	// Erstellt ein Minisquirrel
        public MiniSquirrel createDescendant(int latestId, int energy, int x, int y){
            MiniSquirrelBot Norbert = new MiniSquirrelBot(latestId+1, energy, getLocation().getX()+x, getLocation().getY()+y, this.getId());
            this.updateEnergy(-energy);
            return Norbert;
	}
	
	//Pr�fe ob �bergebenes Entity Object ein Nachfahre ist
	public boolean checkDescendant(MiniSquirrel mini){
            return mini.getPatronId()==this.getId();
                    }
 
    public void nextStep(EntityContext entCon,XY direction){
        entCon.tryMove(this, direction);
    }
}

