
package prog2_a3.fatsquirrel.core;

import java.util.logging.Level;

import prog2_a3.interfaces.EntityContext;

public abstract class Beast extends Entity {
	private static final GameLogger logger = new GameLogger();
    public Beast(int id, int energy, int x, int y){
        super(id,energy,x,y);
        logger.log(Level.FINEST, "Object der Klasse Beast erstellt");
    }
    
}
