package prog2_a3.fatsquirrel.core;

import java.util.logging.Level;

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
private static final GameLogger logger = new GameLogger();

    public Board( BoardConfig config){
	this.length = config.getLength();
	this.width = config.getWidth();
        this.size = config.getSize();
        this.entSet = new EntitySet();
	this.amountGoodBeasts = config.getAmountGoodBeasts();
	this.amountBadBeasts = config.getAmountBadBeasts();
	this.amountGoodPlants = config.getAmountGoodPlants();
	this.amountBadPlants = config.getAmountBadPlants();
	fillBoard(this.amountGoodBeasts,this.amountBadBeasts,this.amountGoodPlants,this.amountBadPlants,this.amountWalls);
	logger.log(Level.FINEST, "Object der Klasse Board erstellt");
    };
    
    
    public Board(BoardConfig config, GuidedMasterSquirrel masterSquirrel, MasterSquirrelBot masterBot, GoodBeast goodBeast, BadBeast badBeast, GoodPlant goodPlant, BadPlant badPlant, MiniSquirrel miniSquirrel){
	this.length = config.getLength();
	this.width = config.getWidth();
        this.size = config.getSize();
        this.entSet = new EntitySet();
	this.amountGoodBeasts = 0;
	this.amountBadBeasts = 0;
	this.amountGoodPlants = 0;
	this.amountBadPlants = 0;
	prepBoard(masterSquirrel, masterBot, goodBeast, badBeast, goodPlant, badPlant, miniSquirrel);
	logger.log(Level.FINEST, "Testobject der Klasse Board erstellt");
    };
        
    // Methode um gezielt Entiys auf vorgegebene Stellen zu platzieren um Testcases zu ermöglichen (Ersatz für fillBoard)
    private void prepBoard(GuidedMasterSquirrel masterSquirrel, MasterSquirrelBot masterBot, GoodBeast goodBeast, BadBeast badBeast, GoodPlant goodPlant, BadPlant badPlant, MiniSquirrel miniSquirrel){
    	if(masterSquirrel != null){
    		this.entSet.add("GuidedMasterSquirrel", masterSquirrel.getLocation().getX(), masterSquirrel.getLocation().getY());
    	}
    	if(masterBot != null){
    		this.entSet.add("MasterSquirrelBot", masterBot.getLocation().getX(), masterBot.getLocation().getY());
    	}
    	
    	if(goodBeast != null){
    		this.entSet.add("GoodBeast", goodBeast.getLocation().getX(), goodBeast.getLocation().getY());
    	}
    	
    	if(badBeast != null){
    		this.entSet.add("BadBeast", badBeast.getLocation().getX(), badBeast.getLocation().getY());
    	}
    	
    	if(goodPlant != null){
    		this.entSet.add("GoodPlant", goodPlant.getLocation().getX(), goodPlant.getLocation().getY());
    	}
    	
    	if(badPlant != null){
    		this.entSet.add("BadPlant", badPlant.getLocation().getX(), badPlant.getLocation().getY());
    	}
    	if(miniSquirrel != null){
    		this.entSet.add("MiniSquirrel", miniSquirrel.getLocation().getX(), miniSquirrel.getLocation().getY());
    	}

    	//Walls 
        for(int i = 1; i<this.width;i++){
            this.entSet.add("Wall", 0, i);
            this.entSet.add("Wall", this.length, i);
        }
    }
    
    //Erstellen aller Entitys an zufÃ¤lligem Ort
    private void fillBoard(int amountGoodBeasts, int amountBadBeasts, int amountGoodPlants, int amountBadPlants, int amountWalls){
        this.entSet.add("GuidedMasterSquirrel", randLoc()[0], randLoc()[1]);
        this.entSet.add("MasterSquirrelBot", randLoc()[0], randLoc()[1]);
        
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
        int[] randVector = new int[]{((int)((Math.random()*(length-1))+1)),((int)((Math.random()*(width-1))+1))};
        Entity[] entArray = entSet.getEntityArray();
        boolean isTaken = false;
            do{
                for(int i = 0; entArray[i]!=null; i++){
                    if(entArray[i].getLocation().getPos()==randVector){
                        isTaken = true;
                        randVector = new int[]{(int)(((Math.random()*(length-1))+1)),(int)(((Math.random()*(width-1))+1))};
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
    
    public FlattenedBoard flatten(){ //Board Ã¼bergeben und fÃ¼r flattendBoard nutzen
        return new FlattenedBoard(this);
    }
    
    public EntitySet getEntitySet(){
        return this.entSet;
    }
    
    public XY getSize(){
        return this.size;
    }
}
