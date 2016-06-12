package de.hsa.games.fatsquirrel.core;

import java.util.logging.Level;
import java.util.logging.Logger;

import de.hsa.games.fatsquirrel.EntityType;
import de.hsa.games.fatsquirrel.MasterSquirrel;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.botapi.*;
import de.hsa.games.fatsquirrel.logger.GameLogger;


// TODO: Auto-generated Javadoc
/**
 * The Class MasterSquirrelBot.
 */
public class MasterSquirrelBot extends MasterSquirrel{
	
	/** The Constant logger. */
	private static final GameLogger logger = new GameLogger();
        
        /** The bot con. */
        BotController botCon;
        
        /** The current steps in round. */
        protected int currentStepsInRound = 0;
	
    /**
     * Instantiates a new master squirrel bot.
     *
     * @param id the id
     * @param x the x
     * @param y the y
     * @param botCon the bot con
     */
    public MasterSquirrelBot(int id, int x, int y, BotController botCon) {
        super(id, x, y);
        BotControllerFactory BotFact = new BotControllerFactoryImpl();
        logger.log(Level.FINEST, "Objekt der Klasse MasterSquirrelBot wurde erstellt");
        this.botCon = botCon;
    }
    
    /**
     * Gets the impl name.
     *
     * @return the impl name
     */
    public String getImplName(){
        String name = botCon.getClass().getCanonicalName();
        int index = name.lastIndexOf(".");
        name = name.substring(index+1);
        return name;
    }

    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.MasterSquirrel#nextStep(de.hsa.games.fatsquirrel.core.EntityContext, de.hsa.games.fatsquirrel.XY)
     */
    @Override
    public void nextStep(EntityContext entCon, XY input) {
        ControllerContextImpl conConImp = new ControllerContextImpl(entCon);
        botCon.nextStep(conConImp);
                if(conConImp.getRemainingSteps()<=0){
            this.currentStepsInRound = 0;
        }
        this.currentStepsInRound++;
    }

    /**
     * The Class ControllerContextImpl.
     */
    class ControllerContextImpl implements ControllerContext{

    /** The ent con. */
    EntityContext entCon;
    
    /**
     * Instantiates a new controller context impl.
     *
     * @param entCon the ent con
     */
    public ControllerContextImpl(EntityContext entCon){
        this.entCon = entCon;
    }
        
    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.botapi.ControllerContext#getViewLowerLeft()
     */
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

    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.botapi.ControllerContext#getViewUpperRight()
     */
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
        
    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.botapi.ControllerContext#getEntityAt(de.hsa.games.fatsquirrel.XY)
     */
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

    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.botapi.ControllerContext#move(de.hsa.games.fatsquirrel.XY)
     */
    @Override
    public void move(XY direction) {
            
        entCon.tryMove(MasterSquirrelBot.this,direction);
    }

    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.botapi.ControllerContext#spawnMiniBot(de.hsa.games.fatsquirrel.XY, int)
     */
    @Override
    public void spawnMiniBot(XY direction, int energy) {
        
        try {
            entCon.spawnChild(MasterSquirrelBot.this, direction, energy);
        } catch (NotEnoughEnergyException ex) {
            Logger.getLogger(MasterSquirrelBot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.botapi.ControllerContext#getEnergy()
     */
    @Override
    public int getEnergy() {
        return MasterSquirrelBot.this.getEnergy();
    }

    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.botapi.ControllerContext#implode(int)
     */
    @Override
    public void implode(int radius) {
        logger.log(Level.WARNING,"Es wurde versucht implode() in MasterSquirrelBot aufzurufen");
    }

    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.botapi.ControllerContext#getDirectionToParent()
     */
    @Override
    public XY getDirectionToParent() {
        logger.log(Level.WARNING,"Es wurde versucht getDirectionToParent() in MasterSquirrelBot aufzurufen");
        return null;
    }

    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.botapi.ControllerContext#getRemainingSteps()
     */
    @Override
    public int getRemainingSteps() {
        return ((FlattenedBoard)entCon).getBoard().getConfig().getStepsPerRounds() - MasterSquirrelBot.this.currentStepsInRound;
        }

    }
}