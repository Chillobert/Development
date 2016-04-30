package prog2_a3.fatsquirrel.core;

import prog2_a3.interfaces.*;



public class FlattenedBoard implements BoardView, EntityContext {

 
    private final Entity[][] flattenedBoard;
    XY size;
    private EntitySet entSet;
    Board board;
    
    public FlattenedBoard(Board board){
        this.board = board;
        this.size = board.getSize();
        this.entSet = board.getEntitySet();
        Entity[] entArray = entSet.getEntityArray();
        this.flattenedBoard = new Entity[this.size.getX()+1][this.size.getY()+1];
        for(int i = 0;entArray.length>i;i++){
            if(entArray[i]!=null)
                this.flattenedBoard[entArray[i].getLocation().getX()] [entArray[i].getLocation().getY()] = entArray[i];
        }
    }
    
    public Entity[][] getBoard(){
        return flattenedBoard;
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
        entSet.add(entity.getName(),newLoc.getX(),newLoc.getY());
    }
    
    @Override
    public void spawnChild(MasterSquirrel parent, XY direction, int energy)throws NotEnoughEnergyException{
        if(energy>=this.getSquirrelEnergy())
            throw new NotEnoughEnergyException("your squirrels energy is too low");
        else
            entSet.addMini(parent,energy,direction);
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
            if(nextField !=null ){
                if(!entSet.isInstance(nextField, Wall.class)){
                    miniSquirrel.move(moveDirection);
                    miniSquirrel.updateEnergy(-1);
                }
                else if(entSet.isInstance(nextField, Wall.class)){
                    miniSquirrel.updateEnergy(nextField.getEnergy());
                    miniSquirrel.setTimeout(3);
                    miniSquirrel.updateEnergy(-1);
                }
                mortalCombat(miniSquirrel,nextField);
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
                if((!entSet.isInstance(nextField, Wall.class))){
                    goodBeast.move(actualMoveDirection);
                    goodBeast.setTimeout(4);
                }
                else if(entSet.isInstance(nextField, Wall.class)){
                    //tryMove(goodBeast, new XY(new int[]{r.nextInt(3)-1,r.nextInt(3)-1}));
                    goodBeast.getLocation().move(new int[]{-actualMoveDirection.getX(),-goodBeast.getLocation().getY()});
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
                if((!entSet.isInstance(nextField, Wall.class))){
                    badBeast.move(actualMoveDirection);
                    badBeast.setTimeout(4);
                }
                else if(entSet.isInstance(nextField, Wall.class)){
                    badBeast.move(new XY(new int[]{-actualMoveDirection.getX(),-actualMoveDirection.getY()}));
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
        Entity nextField = getEntity(masterBot.getLocation().getX() + moveDirection.getX(), masterBot.getLocation().getY() + moveDirection.getY());
        //Das Feld betrachten, in das das Squirrel Laufen möchte und falls dort keine Wall steht, kann es sich bewegen.
            if(nextField !=null ){
                if(entSet.isInstance(nextField, Wall.class)){
                    masterBot.updateEnergy(nextField.getEnergy());
                    masterBot.setTimeout(3);
                }
                else if(!entSet.isInstance(nextField, Wall.class))
                    masterBot.move(moveDirection);
                mortalCombat(masterBot,nextField);
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
        Entity[] entArray = entSet.getEntityArray();
        for(int i = 0;entArray.length > i; i++){
            if(entSet.isInstance(entArray[i], MasterSquirrel.class))
                energy = entArray[i].getEnergy();
        }
        return energy;
    }
    
    private void mortalCombat(Entity moveEnt,Entity collEnt){
        if((entSet.isInstance(collEnt, PlayerEntity.class))){
            if (entSet.isInstance(moveEnt, BadBeast.class)){
                    ((PlayerEntity)collEnt).updateEnergy(moveEnt.getEnergy());
                    ((BadBeast)moveEnt).collCount++;
                if(moveEnt.collCount>=7)
                    killAndReplace(moveEnt);
            }
            if (entSet.isInstance(moveEnt, GoodBeast.class)){
                ((PlayerEntity)collEnt).updateEnergy(moveEnt.getEnergy());
                killAndReplace(moveEnt);
            }
        }
        else if(entSet.isInstance(moveEnt, PlayerEntity.class)){
    		//Hier m�ssen die verschiedenen Kollisionsf�lle implementiert werden
    		//arrayPos = bewegte Entity  //collPos = statische Entity 
    		//Goodplant Kollision:
            if(entSet.isInstance(collEnt, Plant.class)){
                moveEnt.updateEnergy(collEnt.getEnergy());
                killAndReplace(collEnt);
            }
            //GoodBeast Kollision:
            if(entSet.isInstance(collEnt, GoodBeast.class)){
            	moveEnt.updateEnergy(collEnt.getEnergy());
                killAndReplace(collEnt);
            }
            //BadBeast Kollision:
            if(entSet.isInstance(collEnt, BadBeast.class)){
                moveEnt.updateEnergy(collEnt.getEnergy());
                collEnt.collCount++;
                if(collEnt.collCount>=7)
                    killAndReplace(collEnt);
            }
            //MasterSquirrel Kollision
            if(entSet.isInstance(collEnt, MasterSquirrel.class)){
                if(entSet.isInstance(moveEnt,MiniSquirrel.class)){
                    if(((MasterSquirrel)collEnt).checkDescendant((MiniSquirrel)moveEnt)){
                        collEnt.updateEnergy(moveEnt.getEnergy());
                    }
                kill(moveEnt);
                }
            }
            //MiniSquirrel Kollision:
            if(entSet.isInstance(collEnt, MiniSquirrel.class)){
                if(entSet.isInstance(moveEnt,MasterSquirrel.class)){
                    if(((MasterSquirrel)moveEnt).checkDescendant((MiniSquirrel)collEnt)){
                       moveEnt.updateEnergy(collEnt.getEnergy());
                    }
                    else
                       moveEnt.updateEnergy(150);
                kill(collEnt);
                }
                if(entSet.isInstance(moveEnt,MiniSquirrel.class)){
                    kill(moveEnt);
                }
            }
        }
    }
    
    public MasterSquirrel getMasterSquirrel(){
        Entity[] entArr = this.entSet.getEntityArray();
        for (int i = 0; entArr[i]!=null;i++)
            if(entSet.isInstance(entArr[i], MasterSquirrel.class))
                return (MasterSquirrel)entArr[i];
        return null;
    }
}
