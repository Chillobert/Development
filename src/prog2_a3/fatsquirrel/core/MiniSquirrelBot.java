package prog2_a3.fatsquirrel.core;

import java.util.HashMap;
import prog2_a3.fatsquirrel.botapi.BotControllerMiniImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import prog2_a3.fatsquirrel.botapi.*;
import prog2_a3.interfaces.*;


public class MiniSquirrelBot extends MiniSquirrel{
	private static final GameLogger logger = new GameLogger();
    //private int energy;
    public MiniSquirrelBot(int id, int energy, int x, int y, int patronId) {
        super(id, energy, x, y, patronId);
        logger.log(Level.FINEST, "Objekt der Klasse MiniSquirrelBot wurde erstellt");
        //this.energy = energy;
    }


    @Override
    public void nextStep(EntityContext entCon){
        ControllerContextImpl conConImp = new ControllerContextImpl(entCon);
        BotControllerMiniImpl botCon = new BotControllerMiniImpl();
        botCon.nextStep(conConImp);
    }

    class ControllerContextImpl implements MiniBotControllerContext{

        EntityContext entCon;

        public ControllerContextImpl(EntityContext entCon){
            this.entCon = entCon;
        }

        @Override
        public XY getViewLowerLeft() {
            XY currentLocation = MiniSquirrelBot.this.getLocation();
            int currentX = currentLocation.getX();
            int currentY = currentLocation.getY();
            XY fieldSize = entCon.getSize();
            XY lowerLeft = currentLocation;
            for(int i = 1;i<=10;i++){
                if(currentX-i>=0)
                    lowerLeft = lowerLeft.moveLeft();
                if(currentY+i <= fieldSize.getY())
                    lowerLeft = lowerLeft.moveDown();
            }
            return lowerLeft;
        }
        
        @Override
        public XY getViewUpperRight() {
            XY currentLocation = MiniSquirrelBot.this.getLocation();
            int currentX = currentLocation.getX();
            int currentY = currentLocation.getY();
            XY fieldSize = entCon.getSize();
            XY upperRight = currentLocation;
            for(int i = 1;i<=11;i++){
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
                entCon.tryMove(MiniSquirrelBot.this,direction);
        }

        @Override
        public int getEnergy() {
            return MiniSquirrelBot.this.getEnergy();
        }

        @Override
        public void implode(int radius) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public XY getDirectionToParent() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
}
