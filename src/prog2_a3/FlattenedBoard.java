
package prog2_a3;

import java.util.Arrays;
import prog2_a3.fatsquirrel.core.*;
import prog2_a3.interfaces.*;
import java.util.Hashtable;


public class FlattenedBoard implements BoardView, EntityContext {
    
 
    private final Entity[][] flattenedBoard;
    XY size;
    EntitySet entSet;
    Hashtable<Integer, XY> vectorList;
    
    public FlattenedBoard(Entity[][] flattenedBoard, XY size){
        this.vectorList = new Hashtable<Integer, XY>();
        this.flattenedBoard = flattenedBoard;
        this.size = size;
        this.entSet = new EntitySet();
    }
    
    public Entity[][] getBoard(){
        return flattenedBoard;
    }
    
    @Override
    public String toString(){
        return Arrays.deepToString(flattenedBoard);
    }
    
    public Entity getEntity(int x, int y){
        return this.flattenedBoard[x][y];
    }
    
    @Override
    public String getEntityType(int x, int y){
        return flattenedBoard[x][y].getName();
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
        entSet.add(entity.getName(),(int)(Math.random()*size.getX()),(int)(Math.random()*size.getY()));
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
            if ("PlayerEntity".equals(entArray[i].getClass().getSuperclass().getSuperclass().getName())){
                //Summe der Beträge, der X und Y Differenzen @_@
                currentDistance = (Math.abs(position.getX() - entArray[i].getLocation().getX()) + Math.abs(position.getY() - entArray[i].getLocation().getY()));
                if (currentDistance < minDistance){
                    minDistance = currentDistance;
                    entityReturn = (PlayerEntity) entArray[i];
                }
            }
        }
        return entityReturn;
    }
    
    @Override
    public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection){
        Entity nextField = getEntity(miniSquirrel.getLocation().getX() + moveDirection.getX(), miniSquirrel.getLocation().getY() + moveDirection.getY());
        //Das Feld betrachten, in das das Squirrel Laufen möchte und falls dort keine Wall steht, kann es sich bewegen.
        if(nextField !=null )
            if(!("Wall".equals(nextField.getName()))){
                vectorList.put(nextField.getId(), moveDirection);
            }
    }
    @Override
    public void tryMove(GoodBeast goodBeast, XY moveDirection){
        Entity nextField = getEntity(goodBeast.getLocation().getX() + moveDirection.getX(), goodBeast.getLocation().getY() + moveDirection.getY());
        //Das Feld betrachten, in das das Beast Laufen möchte und falls dort keine Wall steht, kann es sich bewegen.
        if(nextField !=null )
            if(!("Wall".equals(nextField.getName()))){
                vectorList.put(nextField.getId(), moveDirection);
            }
    }
    @Override
    public void tryMove(BadBeast badBeast, XY moveDirection){
        Entity nextField = getEntity(badBeast.getLocation().getX() + moveDirection.getX(), badBeast.getLocation().getY() + moveDirection.getY());
        //Das Feld betrachten, in das das badBeast Laufen möchte und falls dort keine Wall steht, kann es sich bewegen.
        if(nextField !=null )
            if(!("Wall".equals(nextField.getName()))){
                vectorList.put(nextField.getId(), moveDirection);
            }
    }
    @Override
    public void tryMove(MasterSquirrel masterBot, XY moveDirection){
        Entity nextField = getEntity(masterBot.getLocation().getX() + moveDirection.getX(), masterBot.getLocation().getY() + moveDirection.getY());
        //Das Feld betrachten, in das das Squirrel Laufen möchte und falls dort keine Wall steht, kann es sich bewegen.
        if(nextField !=null )
            if(!("Wall".equals(nextField.getName()))){
                vectorList.put(nextField.getId(), moveDirection);
                //vectors[vectorCount++] = moveDirection;
            }
    }
    
    @Override
    public Hashtable<Integer,XY> getVectors(){
        return vectorList;
    }
}
