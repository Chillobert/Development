package prog2_a3.fatsquirrel.core;

import java.util.logging.Level;

import prog2_a3.interfaces.EntityContext;


public class GuidedMasterSquirrel extends MasterSquirrel {
	private static final GameLogger logger = new GameLogger();
    public String in;
    public GuidedMasterSquirrel(int id, int x, int y){
        super(id,x,y);
        logger.log(Level.FINEST, "Objekt der Klasse GuidedMasterSquirrel wurde erstellt");
    }
    
    @Override
        public void nextStep(EntityContext entCon) {
    } 
        
}
