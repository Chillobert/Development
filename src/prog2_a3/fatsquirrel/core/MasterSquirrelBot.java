package prog2_a3.fatsquirrel.core;

import java.util.logging.Level;
import java.util.logging.Logger;
import prog2_a3.interfaces.*;
import prog2_a3.fatsquirrel.botapi.*;

public class MasterSquirrelBot extends MasterSquirrel{
	private static final GameLogger logger = new GameLogger();
        BotController botCon;
	
    public MasterSquirrelBot(int id, int x, int y, BotController botCon) {
        super(id, x, y);
        BotControllerFactory BotFact = new BotControllerFactoryImpl();
        logger.log(Level.FINEST, "Objekt der Klasse MasterSquirrelBot wurde erstellt");
        this.botCon = botCon;
    }
    
    public String getImplName(){
        String name = botCon.getClass().getCanonicalName();
        int index = name.lastIndexOf(".");
        name = name.substring(index+1);
        return name;
    }

    @Override
    public void nextStep(EntityContext entCon) {
        ControllerContextImpl conConImp = new ControllerContextImpl(entCon);
        botCon.nextStep(conConImp);
    }

    class ControllerContextImpl implements ControllerContext{

    EntityContext entCon;
    
    public ControllerContextImpl(EntityContext entCon){
        this.entCon = entCon;
    }
        
    @Override
    public XY getViewLowerLeft() {
        XY currentLocation = MasterSquirrelBot.this.getLocation();
        int currentX = currentLocation.getX();
        int currentY = currentLocation.getY();
        XY fieldSize = entCon.getSize();
        XY lowerLeft = currentLocation;
        for(int i = 1;i<=15;i++){
            if(currentX-i>=0)
                lowerLeft = lowerLeft.moveLeft();
            if(currentY+i <= fieldSize.getY())
                lowerLeft = lowerLeft.moveDown();
        }
        return lowerLeft;
    }

    @Override
    public XY getViewUpperRight() {
        XY currentLocation = MasterSquirrelBot.this.getLocation();
        int currentX = currentLocation.getX();
        int currentY = currentLocation.getY();
        XY fieldSize = entCon.getSize();
        XY upperRight = currentLocation;
        for(int i = 1;i<=16;i++){
            if(currentX+i<=fieldSize.getX())
                upperRight = upperRight.moveRight();
            if(currentY-i >= 0)
                upperRight = upperRight.moveUp();
        }
        return upperRight;
    }
        
    @Override
    public EntityType getEntityAt(XY xy) {
        if(entCon.getEntityType(xy)!=null){
            // Name der Entity auf dem gewünschten Feld abfragen
            String entTypeString = entCon.getEntityType(xy).toUpperCase();
            // Entity Type mit dem Namen zurückgeben
            return EntityType.valueOf(entTypeString);
            }
            return null;
    }

    @Override
    public void move(XY direction) {
            
        entCon.tryMove(MasterSquirrelBot.this,direction);
    }

    @Override
    public void spawnMiniBot(XY direction, int energy) {
        
        try {
            entCon.spawnChild(MasterSquirrelBot.this, direction, energy);
        } catch (NotEnoughEnergyException ex) {
            Logger.getLogger(MasterSquirrelBot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getEnergy() {
        return MasterSquirrelBot.this.getEnergy();
    }

    @Override
    public void implode(int radius) {
        logger.log(Level.WARNING,"Es wurde versucht implode() in MasterSquirrelBot aufzurufen");
    }

    @Override
    public XY getDirectionToParent() {
        logger.log(Level.WARNING,"Es wurde versucht getDirectionToParent() in MasterSquirrelBot aufzurufen");
        return null;
    }

    @Override
    public int getRemainingSteps() {
        return ((FlattenedBoard)entCon).getEntitySet().getRemainingSteps();
        }

    }
}