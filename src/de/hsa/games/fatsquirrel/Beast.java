
package de.hsa.games.fatsquirrel;

import java.util.logging.Level;

import de.hsa.games.fatsquirrel.logger.GameLogger;

public abstract class Beast extends Entity {
	private static final GameLogger logger = new GameLogger();
    public Beast(int id, int energy, int x, int y){
        super(id,energy,x,y);
        logger.log(Level.FINEST, "Object der Klasse Beast erstellt");
    }
}
