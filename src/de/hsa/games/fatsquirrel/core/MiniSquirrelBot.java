package de.hsa.games.fatsquirrel.core;

import java.util.List;
import java.util.logging.Level;

import de.hsa.games.fatsquirrel.Entity;
import de.hsa.games.fatsquirrel.EntityType;
import de.hsa.games.fatsquirrel.MasterSquirrel;
import de.hsa.games.fatsquirrel.MiniSquirrel;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.botapi.*;
import de.hsa.games.fatsquirrel.botimpls.MiniBotImpl1;
import de.hsa.games.fatsquirrel.logger.GameLogger;


public class MiniSquirrelBot extends MiniSquirrel{
    
    protected int currentStepsInRound = 0;
    private static final GameLogger logger = new GameLogger();
    //private int energy;
    public MiniSquirrelBot(int id, int energy, int x, int y, int patronId) {
        super(id, energy, x, y, patronId);
        logger.log(Level.FINEST, "Objekt der Klasse MiniSquirrelBot wurde erstellt");
        //this.energy = energy;
    }


    @Override
    public void nextStep(EntityContext entCon,XY input){
        ControllerContextImpl conConImp = new ControllerContextImpl(entCon);
        MiniBotImpl1 botCon = new MiniBotImpl1();
        botCon.nextStep(conConImp);
        this.currentStepsInRound++;
        if(conConImp.getRemainingSteps()<=0){
            this.currentStepsInRound = 0;
        }
    }

    class ControllerContextImpl implements ControllerContext{

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
        public void spawnMiniBot(XY direction, int energy) {
            // fehler logging
        }

        @Override
        public int getEnergy() {
            return MiniSquirrelBot.this.getEnergy();
        }

        @Override
        public void implode(int impactRadius) {
            int energy = MiniSquirrelBot.this.getEnergy();
            MasterSquirrel patron = this.getMaster();
            XY position = MiniSquirrelBot.this.getLocation();
            int currentX = position.getX();
            int currentY = position.getY();
            int testX = 0;
            int testY = 0;
            List<Entity> entArray = ((FlattenedBoard)entCon).getEntitySet().getEntityVector();
            int distance = 0;
            int energyLoss = 0;
            
            
            if(impactRadius >= 2 && impactRadius <= 10){
                for(int i = 0; i < entArray.size(); i++){
                    //ganzes Feld oder Liste der Entitys durchgehen und den Abstand zum Squirrel berechnen
                    testX = entArray.get(i).getLocation().getX();
                    testY = entArray.get(i).getLocation().getY();
                    // Berechnen des Abstands zu unserem Mini
                    distance = (Math.abs(testX-currentX))>(Math.abs(testY-currentY))?Math.abs(testX-currentX):Math.abs(testY-currentY);
                    //Bei passendem Abstand und gültigem Ziel (nicht patron, nicht selbst) Formel anwenden
                    if(distance < impactRadius && !entArray.get(i).equals(patron) && !entArray.get(i).equals(MiniSquirrelBot.this)){
                        energyLoss = (-200) * (energy / (int)((Math.pow(impactRadius, 2)*(Math.PI))+0.5)* (1 - distance / impactRadius));
                        //falls das Ziel über genügend Energie verfügt
                        if(entArray.get(i).getEnergy() > energyLoss){
                            entArray.get(i).updateEnergy(-energyLoss);
                            patron.updateEnergy(energyLoss);
                        }
                        //falls das Ziel nicht über genügend Energie verfügt und nicht der eigene Master ist
                        if(entArray.get(i).getEnergy() <= energyLoss){
                            entArray.get(i).updateEnergy(-entArray.get(i).getEnergy());
                            patron.updateEnergy(entArray.get(i).getEnergy());
                            if(entArray.get(i).getName().contains("Squirrel"))
                                ((FlattenedBoard)entCon).kill(entArray.get(i));
                            else
                                ((FlattenedBoard)entCon).killAndReplace(entArray.get(i));
                        }
                    }
                }
            }
            else
                logger.log(Level.WARNING, "implode in MiniSquirrelBot mit falschem Radius aufgerufen");
        }

        @Override
        public XY getDirectionToParent() {
                        
            XY direction = null;
            MasterSquirrel master = this.getMaster();
            if(master != null)
                direction = getDirectionTo(master.getLocation(),MiniSquirrelBot.this.getLocation());
            return direction;
        }
        
        private XY getDirectionTo(XY subject, XY follower){
            
        int[] fleeVector = new int[]{0,0};

        int distX=follower.getX()-subject.getX();
        int distY = follower.getY()-subject.getY();
        if(distX==0 && distY==0) // in die XY verlagern
            fleeVector = new int[]{0,0};
        else if(distX==0 && distY!=0)
            fleeVector = new int[]{0,(distY/Math.abs(distY))};
        else if(distX!=0 && distY==0)
            fleeVector = new int[]{(distX/Math.abs(distX)),0};
        else if(distX!=0 && distY!=0)
            fleeVector = new int[]{(distX/Math.abs(distX)) , (distY/Math.abs(distY))};
        
        return new XY(fleeVector);
    }

        private MasterSquirrel getMaster(){
            //Geht das komplette Board durch und checkt für jedes MasterSquirrel, ob das Mini von ihm ist
            Entity[][] board = ((FlattenedBoard)entCon).getEntBoard();

            for(int i=0; i< board.length;i++){
                for(int j=0; j<board[i].length;i++){
                    if("MasterSquirrelBot".equals(board[i][j].getName())||"GuidedMasterSquirrel".equals(board[i][j].getName())){
                        if(((MasterSquirrel)board[i][j]).checkDescendant(MiniSquirrelBot.this))
                            return (MasterSquirrel)board[i][j];
                        }
                }
            }
            return null;
        }

        @Override
        public int getRemainingSteps() {
            return ((FlattenedBoard)entCon).getBoard().getConfig().getStepsPerRounds() - MiniSquirrelBot.this.currentStepsInRound;
        }
    }
}