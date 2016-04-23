package prog2_a3.fatsquirrel.core;

import prog2_a3.interfaces.*;
import java.util.Hashtable;



public class FlattenedBoard implements BoardView, EntityContext {

 
    private final Entity[][] flattenedBoard;
    XY size;
    public EntitySet entSet;
    Hashtable<Integer, XY> vectorList;
    
    public FlattenedBoard(Entity[][] flattenedBoard, XY size, EntitySet entSet){
        this.vectorList = new Hashtable<>();
        this.flattenedBoard = flattenedBoard;
        this.size = size;
        this.entSet = entSet;
    }
    
    public Entity[][] getBoard(){
        return flattenedBoard;
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
        entSet.add(entity.getName(),(int)(Math.random()*(size.getX()-1)),(int)(Math.random()*(size.getY()-1)));
    }
    
    @Override
    public void spawnChild(MasterSquirrel parent, XY direction, int energy){
        parent.createDescendant(entSet.getLatestId(), energy, parent.getLocation().getX() + direction.getX(), parent.getLocation().getY() + direction.getY());
    }
    
    @Override
    public PlayerEntity nearestPlayerEntity(XY position){
        Entity[] entArray = entSet.getEntityArray();
        int minDistance = 30;
        int currentDistance;
        PlayerEntity entityReturn=null;
        for(int i=0; i < entArray.length;i++){
            //Das Array aller Entitys durchgehen und für PlayerEntitys die Distanz zum Objekt mit aktuellem Minimum vergleichen
            if (entSet.isInstance(entArray[i], PlayerEntity.class)){
                //Summe der Beträge, der X und Y Differenzen @_@
                int x0 = position.getX();
                int x1 = entArray[i].getLocation().getX();
                int y0 = position.getY();
                int y1 = entArray[i].getLocation().getY();
                currentDistance =  (Math.abs(x1-x0))>(Math.abs(y1-y0))?Math.abs(x1-x0):Math.abs(y1-y0);
                if (currentDistance < minDistance){
                    minDistance = currentDistance;
                    entityReturn = (PlayerEntity) entArray[i];
                }
            }
        }
        return entityReturn;
    }
    
    public int nearestPlayerDistance(XY position){
        Entity[] entArray = entSet.getEntityArray();
        int minDistance = 30;
        int currentDistance;
        for(int i=0; i < entArray.length;i++){
            //Das Array aller Entitys durchgehen und für PlayerEntitys die Distanz zum Objekt mit aktuellem Minimum vergleichen
            if (entSet.isInstance(entArray[i], PlayerEntity.class)){
                int x0 = position.getX();
                int x1 = entArray[i].getLocation().getX();
                int y0 = position.getY();
                int y1 = entArray[i].getLocation().getY();
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
        Entity nextField = getEntity(miniSquirrel.getLocation().getX() + moveDirection.getX(), miniSquirrel.getLocation().getY() + moveDirection.getY());
        //Das Feld betrachten, in das das Squirrel Laufen möchte und falls dort keine Wall steht, kann es sich bewegen.
        if(miniSquirrel.getTimeout()==0){
            if(nextField !=null ){
                if(!entSet.isInstance(nextField, Wall.class)){
                    vectorList.put(nextField.getId(), moveDirection);
                    miniSquirrel.updateEnergy(-1);
                }
                else if(entSet.isInstance(nextField, Wall.class)){
                    miniSquirrel.updateEnergy(nextField.getEnergy());
                    miniSquirrel.setTimeout(3);
                    miniSquirrel.updateEnergy(-1);
                }
            }
        }
        else if(miniSquirrel.getTimeout()>0)
            miniSquirrel.setTimeout(miniSquirrel.getTimeout()-1);
    }
    @Override
    public void tryMove(GoodBeast goodBeast, XY moveDirection){
        
        int nearestPlayerDistance = nearestPlayerDistance(goodBeast.getLocation());
        XY nearestPlayerLoc = nearestPlayerEntity(goodBeast.getLocation()).getLocation();
        //Das Feld betrachten, in das das Beast Laufen möchte und falls dort keine Wall steht, kann es sich bewegen.
        XY actualMoveDirection = new XY(new int[]{0,0});
        int[] fleeVector = new int[]{0,0};
        int distX=goodBeast.getLocation().getX()-nearestPlayerLoc.getX();
        int distY = goodBeast.getLocation().getY()-nearestPlayerLoc.getY();
        if(distX==0 && distY==0)
            fleeVector = new int[]{0,0};
        else if(distX==0 && distY!=0)
            fleeVector = new int[]{0,(distY/Math.abs(distY))};
        else if(distX!=0 && distY==0)
            fleeVector = new int[]{(distX/Math.abs(distX)),0};
        else if(distX!=0 && distY!=0)
            fleeVector = new int[]{(distX/Math.abs(distX)) , (distY/Math.abs(distY))};
        
        if(nearestPlayerDistance>6)
            actualMoveDirection = moveDirection;
        else if(nearestPlayerDistance<=6)
            actualMoveDirection = new XY(fleeVector);
        
        if(goodBeast.getTimeout()==0){
                    Entity nextField = getEntity(goodBeast.getLocation().getX() + actualMoveDirection.getX(), goodBeast.getLocation().getY() + actualMoveDirection.getY());
            if(nextField !=null ){
                if((!entSet.isInstance(nextField, Wall.class))){
                    vectorList.put(goodBeast.getId(), actualMoveDirection);
                    goodBeast.setTimeout(4);
                }
                else if(entSet.isInstance(nextField, Wall.class)){
                    //tryMove(goodBeast, new XY(new int[]{r.nextInt(3)-1,r.nextInt(3)-1}));
                    vectorList.put(goodBeast.getId(), new XY(new int[]{-actualMoveDirection.getX(),-actualMoveDirection.getY()}));
                }
            }
            else{
                vectorList.put(goodBeast.getId(), actualMoveDirection);
                goodBeast.setTimeout(4);
            }
        }
            else
                goodBeast.setTimeout(goodBeast.getTimeout()-1);
    }
    @Override
    public void tryMove(BadBeast badBeast, XY moveDirection){
        
        int nearestPlayerDistance = nearestPlayerDistance(badBeast.getLocation());
        XY nearestPlayerLoc = nearestPlayerEntity(badBeast.getLocation()).getLocation();
        //Das Feld betrachten, in das das Beast Laufen möchte und falls dort keine Wall steht, kann es sich bewegen.
        XY actualMoveDirection = new XY(new int[]{0,0});
        int[] attackVector = new int[]{0,0};
        int distX=nearestPlayerLoc.getX()-badBeast.getLocation().getX();
        int distY = nearestPlayerLoc.getY()-badBeast.getLocation().getY();
        if(distX==0 && distY==0)
            attackVector = new int[]{0,0};
        else if(distX==0 && distY!=0)
            attackVector = new int[]{0,(distY/Math.abs(distY))};
        else if(distX!=0 && distY==0)
            attackVector = new int[]{(distX/Math.abs(distX)),0};
        else if(distX!=0 && distY!=0)
            attackVector = new int[]{(distX/Math.abs(distX)) , (distY/Math.abs(distY))};
        
        if(nearestPlayerDistance>6)
            actualMoveDirection = moveDirection;
        else if(nearestPlayerDistance<=6)
            actualMoveDirection = new XY(attackVector);
        
        Entity nextField = getEntity(badBeast.getLocation().getX() + actualMoveDirection.getX(), badBeast.getLocation().getY() + actualMoveDirection.getY());
        //Das Feld betrachten, in das das badBeast Laufen möchte und falls dort keine Wall steht, kann es sich bewegen.
        if(badBeast.getTimeout()==0){
            if(nextField !=null ){
                if((!entSet.isInstance(nextField, Wall.class))){
                    vectorList.put(badBeast.getId(), actualMoveDirection);
                    badBeast.setTimeout(4);
                }
                else if(entSet.isInstance(nextField, Wall.class)){
                    vectorList.put(badBeast.getId(), new XY(new int[]{-actualMoveDirection.getX(),-actualMoveDirection.getY()}));
                }
            }
            else{
                vectorList.put(badBeast.getId(), actualMoveDirection);
                badBeast.setTimeout(4);
            }
        }
            else
                badBeast.setTimeout(badBeast.getTimeout()-1);
    }
    @Override
    public void tryMove(MasterSquirrel masterBot, XY moveDirection){
        Entity nextField = getEntity(masterBot.getLocation().getX() + moveDirection.getX(), masterBot.getLocation().getY() + moveDirection.getY());
        //Das Feld betrachten, in das das Squirrel Laufen möchte und falls dort keine Wall steht, kann es sich bewegen.
        if(masterBot.getTimeout()==0){
            if(nextField !=null ){
                if(entSet.isInstance(nextField, Wall.class)){
                    masterBot.updateEnergy(nextField.getEnergy());
                    masterBot.setTimeout(3);
                }
                else if(!entSet.isInstance(nextField, Wall.class))
                    vectorList.put(masterBot.getId(),moveDirection);
                /*else if(entSet.isInstance(nextField, Plant.class)){
                    masterBot.updateEnergy(nextField.getEnergy());
                    killAndReplace(nextField);
                    vectorList.put(masterBot.getId(), moveDirection);
                }
                else if("GoodBeast".equals(nextField.getName())){
                    masterBot.updateEnergy(nextField.getEnergy());
                    killAndReplace(nextField);
                    vectorList.put(masterBot.getId(), moveDirection);
                    }
                else if("BadBeast".equals(nextField.getName())){
                    masterBot.updateEnergy(nextField.getEnergy());
                    if(nextField.collCount<7)
                        nextField.collCount++;
                    else
                        killAndReplace(nextField);
                    vectorList.put(masterBot.getId(), moveDirection);
                }
                else if("MiniSquirrel".equals(nextField.getName())){
                    if(masterBot.checkDescendant((MiniSquirrel)nextField)){
                        masterBot.updateEnergy(nextField.getEnergy());
                    }
                    else
                        masterBot.updateEnergy(150);
                    kill(nextField);
                    vectorList.put(masterBot.getId(), moveDirection);
                }*/
            }
            else
                vectorList.put(masterBot.getId(), moveDirection);
        }
        else if(masterBot.getTimeout()>0)
            masterBot.setTimeout(masterBot.getTimeout()-1);
    }
    
    @Override
    public Hashtable<Integer,XY> getVectors(){
        return vectorList;
    }
}
