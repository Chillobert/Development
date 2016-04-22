package prog2_a3.fatsquirrel.core;

import java.util.Arrays;

public class Board {
	
private final int length;
private final int width;
private final XY size;
private final int amountGoodBeasts;
private final int amountBadBeasts;
private final int amountGoodPlants;
private final int amountBadPlants;
private int amountWalls;
public EntitySet entSet;

    public Board(){
	BoardConfig config = new BoardConfig();
	this.length = config.getLength();
	this.width = config.getWidth();
        this.size = config.getSize();
        this.entSet = new EntitySet(size);
	this.amountGoodBeasts = config.getAmountGoodBeasts();
	this.amountBadBeasts = config.getAmountBadBeasts();
	this.amountGoodPlants = config.getAmountGoodPlants();
	this.amountBadPlants = config.getAmountBadPlants();
	fillBoard(2,0,0,0,0);
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

    private int[] randLoc(){
        return new int[]{(int)(((Math.random()*9)+1)),(int)(((Math.random()*9)+1))};
    }
    
@Override
    public String toString(){
    	return entSet.toString();
    }

    public int getEntityCount() {
	return entSet.getLatestId();
	}
    
    public FlattenedBoard flatten(){
        Entity[] entArray = entSet.getEntityArray();
        Entity[][] flattenedBoard = new Entity[this.length+1][this.width+1];
        for(Entity[] row:flattenedBoard)
            Arrays.fill(row, null);
        for(int i = 0;entArray.length>i;i++){
            if(entArray[i]!=null)
                flattenedBoard[entArray[i].getLocation().getX()] [entArray[i].getLocation().getY()] = entArray[i];
        }
        return new FlattenedBoard(flattenedBoard,size,entSet);
    }
}
