package prog2_a3.fatsquirrel.core;

import prog2_a3.fatsquirrel.botapi.BotControllerMasterImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import prog2_a3.interfaces.*;
import prog2_a3.fatsquirrel.botapi.*;

public class MasterSquirrelBot extends MasterSquirrel{

    public MasterSquirrelBot(int id, int x, int y) {
        super(id, x, y);
        BotControllerFactory BotFact = new BotControllerFactoryImpl();
        BotController BotCon = BotFact.createMasterBotController();
    }

    @Override
    public void nextStep(EntityContext entCon) {
        ControllerContextImpl conConImp = new ControllerContextImpl(entCon);
        BotControllerMasterImpl botCon = new BotControllerMasterImpl();
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
            XY upperRight = currentLocation;
            for(int i = 1;i<=15;i++){
                if(currentX-i>=0)
                    upperRight = new XY(new int[]{upperRight.getX()-1,upperRight.getY()});
                if(currentY+i <= fieldSize.getY())
                    upperRight = new XY(new int[]{upperRight.getX(),upperRight.getY()-1});
            }
            return upperRight;
        }
        
        @Override
        public XY getViewUpperRight() {
            XY currentLocation = MasterSquirrelBot.this.getLocation();
            int currentX = currentLocation.getX();
            int currentY = currentLocation.getY();
            XY fieldSize = entCon.getSize();
            XY upperRight = currentLocation;
            for(int i = 1;i<=15;i++){
                if(currentX+i<=fieldSize.getX())
                    upperRight = new XY(new int[]{upperRight.getX()+1,upperRight.getY()});
                if(currentY-i >= 0)
                    upperRight = new XY(new int[]{upperRight.getX(),upperRight.getY()-1});
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

    }
}