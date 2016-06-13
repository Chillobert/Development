package de.hsa.games.fatsquirrel.core;


import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.hsa.games.fatsquirrel.BadBeast;
import de.hsa.games.fatsquirrel.Beast;
import de.hsa.games.fatsquirrel.Entity;
import de.hsa.games.fatsquirrel.GoodBeast;
import de.hsa.games.fatsquirrel.GuidedMasterSquirrel;
import de.hsa.games.fatsquirrel.MasterSquirrel;
import de.hsa.games.fatsquirrel.MiniSquirrel;
import de.hsa.games.fatsquirrel.Plant;
import de.hsa.games.fatsquirrel.PlayerEntity;
import de.hsa.games.fatsquirrel.Wall;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.logger.GameLogger;

/**
 * The Class FlattenedBoard is a mirror of the Board Object itself and is used for the view (gui).
 */
public class FlattenedBoard implements BoardView, EntityContext {


    /** Object for containing all created Entitys. */
    private final Entity[][] flattenedBoard;
    
    /** The size of the Board. */
    private XY size;
    
    /** The entitySet given by the Board (contains all Entitys). */
    private EntitySet entSet;
    
    /** The board which gets mirrored by the flattenedBoard and is used for all gamelogical tasks. */
    private Board board;
    
    /** The Logger is logging important Informations which happen inside of the FlattenedBoard Object . */
    private static final GameLogger logger = new GameLogger();
    
    /** The params. */
   // private String params;
    
    /**
     * Instantiates a new flattenedboard for the view.
     *
     * @param board gets mirrored by the flattenedBoard and is used for all gamelogical tasks.
     */
    public FlattenedBoard(Board board){
        this.board = board;
        this.size = board.getSize();
        this.entSet = board.getEntitySet();
        List<Entity> entArray = entSet.getEntityVector();
        this.flattenedBoard = new Entity[this.size.getX()+1][this.size.getY()+1];
        for(int i = 0;entArray.size()>i;i++){
                this.flattenedBoard[entArray.get(i).getLocation().getX()] [entArray.get(i).getLocation().getY()] = entArray.get(i);
        }
        logger.log(Level.FINEST, "Objekt der Klasse FlattenedBoard wurde erstellt");
    }
    
    /**
     * This Method gets the flattenedBoard.
     *
     * @return the flattenedBoard.
     */
    public Entity[][] getEntBoard(){
        return flattenedBoard;
    }
    
    /**
     * This Method gets the Board (which got mirrored by the flattenedBoard).
     *
     * @return the board.
     */
    public Board getBoard(){
    	return board;
    }
    
    /**
     * This Method gets the entitySet.
     *
     * @return the entitySet.
     */
    public EntitySet getEntitySet(){
        return this.entSet;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString(){
    	String output = "";
    	
        for (int row = 0; row < flattenedBoard.length; row++) {
            for (int column = 0; column < flattenedBoard[row].length; column++) {
                output += flattenedBoard[row][column] + "\n";
            }
        }
            return output;
    }
    
    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.core.BoardView#getEntity(int, int)
     */
    @Override
    public Entity getEntity(int x, int y){
        return this.flattenedBoard[x][y];
    }
    
    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.core.BoardView#getEntityType(int, int)
     */
    @Override
    public String getEntityType(int x, int y){
        if(flattenedBoard[x][y]!=null)
            return flattenedBoard[x][y].getName();
        else return " ";
    }
    
    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.core.EntityContext#getEntityType(de.hsa.games.fatsquirrel.XY)
     */
    @Override
    public String getEntityType(XY loc){
        return flattenedBoard[loc.getX()][loc.getY()].getName();
    }
    
    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.core.BoardView#getSize()
     */
    @Override
    public XY getSize(){  
        return size;
    }
    
    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.core.EntityContext#kill(de.hsa.games.fatsquirrel.Entity)
     */
    @Override
    public void kill(Entity entity){
        entSet.delete(entity.getId());
    }
    
    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.core.EntityContext#killAndReplace(de.hsa.games.fatsquirrel.Entity)
     */
    @Override
    public void killAndReplace(Entity entity){
        entSet.delete(entity.getId());
        XY newLoc = new XY(board.randLoc());
        try {
            entSet.add((Entity)entity.getClass().getDeclaredConstructor(int.class, int.class, int.class).newInstance(entSet.getLatestId(),newLoc.getX(),newLoc.getY()));
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FlattenedBoard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(FlattenedBoard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(FlattenedBoard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(FlattenedBoard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(FlattenedBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.core.EntityContext#spawnChild(de.hsa.games.fatsquirrel.MasterSquirrel, de.hsa.games.fatsquirrel.XY, int)
     */
    @Override
    public void spawnChild(MasterSquirrel parent, XY direction, int energy)throws NotEnoughEnergyException{
        if(energy>=this.getSquirrelEnergy()){
        	logger.log(Level.WARNING, "FlattenBoard.spawnChild: Nicht gen�gend Energie f�r Spawn");
            throw new NotEnoughEnergyException("your squirrels energy is too low");
            }
        else
            entSet.addMini(parent,energy,direction);
    }
    
    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.core.EntityContext#nearestPlayerEntity(de.hsa.games.fatsquirrel.XY)
     */
    @Override
    public PlayerEntity nearestPlayerEntity(XY position){
        List<Entity> entArray = entSet.getEntityVector();
        int minDistance = 30;
        int currentDistance;
        PlayerEntity entityReturn=null;
        for(int i=0; i < entArray.size();i++){
            //Das Array aller Entitys durchgehen und für PlayerEntitys die Distanz zum Objekt mit aktuellem Minimum vergleichen
            if (PlayerEntity.class.isInstance(entArray.get(i))){
                //Summe der Beträge, der X und Y Differenzen @_@
                int x0 = position.getX();
                int x1 = entArray.get(i).getLocation().getX();
                int y0 = position.getY();
                int y1 = entArray.get(i).getLocation().getY();
                currentDistance =  (Math.abs(x1-x0))>(Math.abs(y1-y0))?Math.abs(x1-x0):Math.abs(y1-y0);
                if (currentDistance < minDistance){
                    minDistance = currentDistance;
                    entityReturn = (PlayerEntity) entArray.get(i);
                }
            }
        }
        return entityReturn;
    }
    
    /**
     * Nearest player distance.
     *
     * @param position the position
     * @return the int
     */
    public int nearestPlayerDistance(XY position){
        List<Entity> entArray = entSet.getEntityVector();
        int minDistance = 100;
        int currentDistance;
        for(int i=0; i < entArray.size();i++){
            //Das Array aller Entitys durchgehen und für PlayerEntitys die Distanz zum Objekt mit aktuellem Minimum vergleichen
            if (PlayerEntity.class.isInstance(entArray.get(i))){
                int x0 = position.getX();
                int x1 = entArray.get(i).getLocation().getX();
                int y0 = position.getY();
                int y1 = entArray.get(i).getLocation().getY();
                currentDistance =  (Math.abs(x1-x0))>(Math.abs(y1-y0))?Math.abs(x1-x0):Math.abs(y1-y0);
                if (currentDistance < minDistance){
                    minDistance = currentDistance;
                }
            }
        }
        return minDistance;
    }
    
    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.core.EntityContext#tryMove(de.hsa.games.fatsquirrel.MiniSquirrel, de.hsa.games.fatsquirrel.XY)
     */
    @Override
    public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection){
        if (miniSquirrel.getEnergy()<=0)
            this.kill(miniSquirrel);
        while((moveDirection.getX()==0) && (moveDirection.getY()==0))
            moveDirection = moveDirection.getRandomVector();
        Entity nextField = getEntity(miniSquirrel.getLocation().getX() + moveDirection.getX(), miniSquirrel.getLocation().getY() + moveDirection.getY());
        //Das Feld betrachten, in das das Squirrel Laufen möchte und falls dort keine Wall steht, kann es sich bewegen.
            if(nextField !=null ){
                if(!Wall.class.isInstance(nextField)){
                    miniSquirrel.move(moveDirection);
                    miniSquirrel.updateEnergy(-1);
                    mortalCombat(miniSquirrel,nextField);
                }
                else if(Wall.class.isInstance(nextField)){
                    miniSquirrel.updateEnergy(nextField.getEnergy());
                    miniSquirrel.setTimeout(3);
                    miniSquirrel.updateEnergy(-1);
                }
            }
            else{
                miniSquirrel.move(moveDirection);
                miniSquirrel.updateEnergy(-1);
            }
    }
    
    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.core.EntityContext#tryMove(de.hsa.games.fatsquirrel.GoodBeast, de.hsa.games.fatsquirrel.XY)
     */
    @Override
    public void tryMove(GoodBeast goodBeast, XY moveDirection){
        
        int nearestPlayerDistance = nearestPlayerDistance(goodBeast.getLocation());
        
        //Das Feld betrachten, in das das Beast Laufen möchte und falls dort keine Wall steht, kann es sich bewegen.
        XY actualMoveDirection = new XY(new int[]{0,0});
        if(nearestPlayerDistance>6)
            actualMoveDirection = moveDirection;
        else if(nearestPlayerDistance<=6)
            actualMoveDirection = getFleeDirection(goodBeast);
        
            Entity nextField = getEntity(goodBeast.getLocation().getX() + actualMoveDirection.getX(), goodBeast.getLocation().getY() + actualMoveDirection.getY());
            if(nextField !=null ){
                if((!Wall.class.isInstance(nextField))){
                    goodBeast.move(actualMoveDirection);
                    goodBeast.setTimeout(4);
                }
                else if(Wall.class.isInstance(nextField)){
                    //tryMove(goodBeast, new XY(new int[]{r.nextInt(3)-1,r.nextInt(3)-1}));
                    //goodBeast.getLocation().move(new int[]{-actualMoveDirection.getX(),-goodBeast.getLocation().getY()});
                }
                mortalCombat(goodBeast,nextField);
            }
            else{
                goodBeast.move(actualMoveDirection);
                goodBeast.setTimeout(4);
            }
    }
    
    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.core.EntityContext#tryMove(de.hsa.games.fatsquirrel.BadBeast, de.hsa.games.fatsquirrel.XY)
     */
    @Override
    public void tryMove(BadBeast badBeast, XY moveDirection){
        
        int nearestPlayerDistance = nearestPlayerDistance(badBeast.getLocation());
        XY actualMoveDirection = new XY(new int[]{0,0});
       
        if(nearestPlayerDistance>6)
            actualMoveDirection = moveDirection;
        else if(nearestPlayerDistance<=6)
            actualMoveDirection = getFleeDirection(badBeast).reverse();
        
        Entity nextField = getEntity(badBeast.getLocation().getX() + actualMoveDirection.getX(), badBeast.getLocation().getY() + actualMoveDirection.getY());
            if(nextField !=null ){
                if((!Wall.class.isInstance(nextField))){
                    badBeast.move(actualMoveDirection);
                    badBeast.setTimeout(4);
                }
                else if(Wall.class.isInstance(nextField)){
                }
                mortalCombat(badBeast,nextField);
            }
            else{
                badBeast.move(actualMoveDirection);
                badBeast.setTimeout(4);
            }
    }
    
    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.core.EntityContext#tryMove(de.hsa.games.fatsquirrel.MasterSquirrel, de.hsa.games.fatsquirrel.XY)
     */
    @Override
    public void tryMove(MasterSquirrel masterBot, XY moveDirection){
        // solange es noch nur einen Master gibt
//        if(masterBot.getEnergy()<=0)
//            kill(masterBot);
        Entity nextField = getEntity(masterBot.getLocation().getX() + moveDirection.getX(), masterBot.getLocation().getY() + moveDirection.getY());
        //Das Feld betrachten, in das das Squirrel Laufen möchte und falls dort keine Wall steht, kann es sich bewegen.
            if(nextField !=null ){
                if(Wall.class.isInstance(nextField)){
                    masterBot.updateEnergy(nextField.getEnergy());
                    masterBot.setTimeout(3);
                }
                else if(!Wall.class.isInstance(nextField)){
                    masterBot.move(moveDirection);
                    mortalCombat(masterBot,nextField);
                }
            }
            else
                masterBot.move(moveDirection);
            
    }
    
    /**
     * This Method gets the fleeDirection of the given Beast.
     *
     * @param the beast which flees (GoodBeast, BadBeast).
     * @return the direction in where the beast is moving away.
     */
    private XY getFleeDirection(Beast beast){
        XY nearestPlayerLoc = nearestPlayerEntity(beast.getLocation()).getLocation();
        int[] fleeVector = new int[]{0,0};

        int distX=beast.getLocation().getX()-nearestPlayerLoc.getX();
        int distY = beast.getLocation().getY()-nearestPlayerLoc.getY();
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

    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.core.EntityContext#getSquirrelEnergy()
     */
    @Override
    public int getSquirrelEnergy() {
        int energy = 0;
        List<Entity> entArray = entSet.getEntityVector();
        for(int i = 0;entArray.size() > i; i++){
            if(GuidedMasterSquirrel.class.isInstance(entArray.get(i)))
                energy = entArray.get(i).getEnergy();
        }
        return energy;
    }
    
    /**
     * This Method handles the Collision between two given Entitys.
     *
     * @param moveEnt the Entity which moved onto another.
     * @param collEnt the Entity who got overrun by another.
     */
    private void mortalCombat(Entity moveEnt,Entity collEnt){

        if(PlayerEntity.class.isInstance(moveEnt)){
    		//Hier m�ssen die verschiedenen Kollisionsf�lle implementiert werden
    		//arrayPos = bewegte Entity  //collPos = statische Entity 
    		//Goodplant Kollision:
            if(Plant.class.isInstance(collEnt)){
                moveEnt.updateEnergy(collEnt.getEnergy());
                killAndReplace(collEnt);
                logger.log(Level.FINE, "GoodPlant wurde von " + moveEnt.getName() + " gefressen");
                logger.log(Level.FINE, "Eine neue GoodPlant ist gewachsen");
            }
            //GoodBeast Kollision:
            if(GoodBeast.class.isInstance(collEnt)){
            	moveEnt.updateEnergy(collEnt.getEnergy());
                killAndReplace(collEnt);
                logger.log(Level.FINE, "GoodBeast wurde von " + moveEnt.getName() + " gefressen");
                logger.log(Level.FINE, "Ein neues GoodBeast erwacht");
            }
            //BadBeast Kollision:
            if(BadBeast.class.isInstance(collEnt)){
                moveEnt.updateEnergy(collEnt.getEnergy());
                logger.log(Level.FINE, "BadBeast kollidiert mit " + moveEnt.getName() + ";" + moveEnt.getName() + " verliert 150 Energie");
                collEnt.collCount++;
                if(collEnt.collCount>=7)
                    killAndReplace(collEnt);
                	logger.log(Level.FINE, "BadBeast bricht zusammen (Lebenszeit erloschen)");
                	logger.log(Level.FINE, "Ein neues BadBeast erwacht");
            }
            //MasterSquirrel Kollision f�r MiniSquirrel fremdes/eigenes
            if(MasterSquirrel.class.isInstance(collEnt)){
                if(MiniSquirrel.class.isInstance(moveEnt)){
                    if(((MasterSquirrel)collEnt).checkDescendant((MiniSquirrel)moveEnt)){
                        collEnt.updateEnergy(moveEnt.getEnergy());
                        logger.log(Level.FINER, "Eigenes MiniSquirrel wurde von Mastersquirrel aufgenommen (Mini kommt zu Master)");
                    }  
                kill(moveEnt);
                logger.log(Level.FINE, "Fremdes MiniSquirrel wurde von einem MasterSquirrel aufgefressen (Mini kommt zu Master)");
                }
            }
            //MiniSquirrel Kollision:
            if(MiniSquirrel.class.isInstance(collEnt)){
                if(MasterSquirrel.class.isInstance(moveEnt)){
                    if(((MasterSquirrel)moveEnt).checkDescendant((MiniSquirrel)collEnt)){
                       moveEnt.updateEnergy(collEnt.getEnergy());
                       logger.log(Level.FINER, "Eigenes MiniSquirrel wurde von Mastersquirrel aufgenommen (Master kommt zu Mini)");
                    }
                    else
                       moveEnt.updateEnergy(150);              
                    kill(collEnt);
                    logger.log(Level.FINE, "Fremdes MiniSquirrel wurde von einem MasterSquirrel aufgefressen (Mini kommt zu Master)");
                }
                if(MiniSquirrel.class.isInstance(moveEnt)){
                    kill(moveEnt);
                      logger.log(Level.FINE, "Minisquirrel l�uft auf anderes Minisquirrel und stirbt");
                }
            }
        }
        else if((PlayerEntity.class.isInstance(collEnt))){
            if (BadBeast.class.isInstance(moveEnt)){
                    ((PlayerEntity)collEnt).updateEnergy(moveEnt.getEnergy());
                    ((BadBeast)moveEnt).collCount++;
                if(moveEnt.collCount>=7)
                    killAndReplace(moveEnt);
                	logger.log(Level.FINE, "BadBeast bricht zusammen (Lebenszeit erloschen)");
                	logger.log(Level.FINE, "Ein neues BadBeast erwacht");
            }
            if (GoodBeast.class.isInstance(moveEnt)){
                ((PlayerEntity)collEnt).updateEnergy(moveEnt.getEnergy());
                killAndReplace(moveEnt);
                	logger.log(Level.FINE, "GoodBeast wird von " + moveEnt.getName() + " aufgefressen");
                	logger.log(Level.FINE, "Ein neues GoodBeast erwacht");
            }
        }
    }
    
    /**
     * This Method gets the MasterSquirrel.
     *
     * @return the Mastersquirrel if there is one.
     */
    public MasterSquirrel getMasterSquirrel(){
        List<Entity> entArr = this.entSet.getEntityVector();
        for (int i = 0; entArr.size()>i;i++)
            if(GuidedMasterSquirrel.class.isInstance(entArr.get(i)))
                return (MasterSquirrel)entArr.get(i);
        return null;
    }
    

 //   public void setParams(String string){
 //   	this.params = string;
    	
  //  }
    

  //  public String getParams(){
  //  	return this.params;
 //   }
    
}
