package prog2_a3.fatsquirrel.core;


import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import prog2_a3.interfaces.*;



public class FlattenedBoard implements BoardView, EntityContext {


    private final Entity[][] flattenedBoard;
    private XY size;
    private EntitySet entSet;
    private Board board;
    private static final GameLogger logger = new GameLogger();
    private String params;
    
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
    
    public Entity[][] getEntBoard(){
        return flattenedBoard;
    }
    
    public Board getBoard(){
    	return board;
    }
    
    public EntitySet getEntitySet(){
        return this.entSet;
    }
    
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
    
    @Override
    public Entity getEntity(int x, int y){
        return this.flattenedBoard[x][y];
    }
    
    @Override
    public String getEntityType(int x, int y){
        if(flattenedBoard[x][y]!=null)
            return flattenedBoard[x][y].getName();
        else return " ";
    }
    @Override
    public String getEntityType(XY loc){
        return flattenedBoard[loc.getX()][loc.getY()].getName();
    }
    
    @Override
    public XY getSize(){  
        return size;
    }
    
    @Override
    public void kill(Entity entity){
        entSet.delete(entity.getId());
    }
    @Override
    public void killAndReplace(Entity entity){
        entSet.delete(entity.getId());
        XY newLoc = new XY(board.randLoc());
        //Constructor für MasterSquirrelBot hinzufügen, Impl aus bestehendem auslesen
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
    
    @Override
    public void spawnChild(MasterSquirrel parent, XY direction, int energy)throws NotEnoughEnergyException{
        if(energy>=this.getSquirrelEnergy()){
        	logger.log(Level.WARNING, "FlattenBoard.spawnChild: Nicht gen�gend Energie f�r Spawn");
            throw new NotEnoughEnergyException("your squirrels energy is too low");
            }
        else
            entSet.addMini(parent,energy,direction);
    }
    
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
    
    public MasterSquirrel getMasterSquirrel(){
        List<Entity> entArr = this.entSet.getEntityVector();
        for (int i = 0; entArr.size()>i;i++)
            if(GuidedMasterSquirrel.class.isInstance(entArr.get(i)))
                return (MasterSquirrel)entArr.get(i);
        return null;
    }
    
    public void setParams(String string){
    	this.params = string;
    	
    }
    
    public String getParams(){
    	return this.params;
    }
    
}
