package prog2_a3.fatsquirrel.core;

public class Board {
	
private final int length;
private final int width;
private final XY size;
private final int amountGoodBeasts;
private final int amountBadBeasts;
private final int amountGoodPlants;
private final int amountBadPlants;
private int amountWalls;
private EntitySet entSet; 

    public Board( BoardConfig config){
	this.length = config.getLength();
	this.width = config.getWidth();
        this.size = config.getSize();
        this.entSet = new EntitySet(size);
	this.amountGoodBeasts = config.getAmountGoodBeasts();
	this.amountBadBeasts = config.getAmountBadBeasts();
	this.amountGoodPlants = config.getAmountGoodPlants();
	this.amountBadPlants = config.getAmountBadPlants();
	fillBoard(this.amountGoodBeasts,this.amountBadBeasts,this.amountGoodPlants,this.amountBadPlants,this.amountWalls);
    };
    
    //Erstellen aller Entitys an zufälligem Ort
    private void fillBoard(int amountGoodBeasts, int amountBadBeasts, int amountGoodPlants, int amountBadPlants, int amountWalls){
        this.entSet.add("GuidedMasterSquirrel", randLoc()[0], randLoc()[1]);
        
    	for(int i = 0; i!=amountGoodBeasts;i++)
            this.entSet.add("GoodBeast", randLoc()[0], randLoc()[1]);
        for(int i = 0; i!=amountBadBeasts;i++)
            this.entSet.add("BadBeast", randLoc()[0], randLoc()[1]);
        for(int i = 0; i!=amountGoodPlants;i++)
            this.entSet.add("GoodPlant", randLoc()[0], randLoc()[1]);
        for(int i = 0; i!=amountBadPlants;i++)
            this.entSet.add("BadPlant", randLoc()[0], randLoc()[1]);
        for(int i = 0; i!=amountWalls;i++)
            this.entSet.add("Wall", randLoc()[0], randLoc()[1]);
        for(int i = 0; i<= this.length;i++){
            this.entSet.add("Wall", i, 0);
            this.entSet.add("Wall", i, this.width);
        }
        for(int i = 1; i<this.width;i++){
            this.entSet.add("Wall", 0, i);
            this.entSet.add("Wall", this.length, i);
        }
    };

    public int[] randLoc(){
        int[] randVector = new int[]{((int)((Math.random()*9)+1)),((int)((Math.random()*9)+1))};
        Entity[] entArray = entSet.getEntityArray();
        boolean isTaken = false;
            do{
                for(int i = 0; entArray[i]!=null; i++){
                    if(entArray[i].getLocation().getPos()==randVector){
                        isTaken = true;
                        randVector = new int[]{(int)(((Math.random()*9)+1)),(int)(((Math.random()*9)+1))};
                        break;
                    }
                    else
                        isTaken = false;
                }
            }while(isTaken);
        return randVector;
    }
    
@Override
    public String toString(){
    	return entSet.toString();
    }

    public int getEntityCount() {
	return entSet.getLatestId();
	}
    
    public FlattenedBoard flatten(){ //Board übergeben und für flattendBoard nutzen
        return new FlattenedBoard(this);
    }
    
    public EntitySet getEntitySet(){
        return this.entSet;
    }
    
    public XY getSize(){
        return this.size;
    }
}
