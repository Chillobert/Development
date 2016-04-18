package prog2_a3.fatsquirrel.core;

import java.util.Arrays;
import java.util.Random;

import prog2_a3.BoardConfig;

public class Board {
	
private int length;
private int width;
private int amountGoodBeasts;
private int amountBadBeasts;
private int amountGoodPlants;
private int amountBadPlants;
private int amountWalls;
private String[][] board;
public EntitySet entSet = new EntitySet();

    public Board(){
	BoardConfig config = new BoardConfig();
	this.length = config.getLength();
	this.width = config.getWidth();
	this.amountGoodBeasts = config.getAmountGoodBeasts();
	this.amountBadBeasts = config.getAmountBadBeasts();
	this.amountGoodPlants = config.getAmountGoodPlants();
	this.amountBadPlants = config.getAmountBadPlants();
	this.board = new String[config.getLength()][config.getWidth()];
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
        Random r = new Random();
        return new int[]{r.nextInt(this.length),r.nextInt(this.width)};
    }
    
@Override
    public String toString(){
    		//Möchte er bestimmt selbst geschrieben habe
    	return entSet.toString();
    }

    public int getEntityCount() {
		int amount = 0;
		return amount;
		
	}
    
    public String[][] flatten(){
        String[][] flattenBoard = new String[this.length][this.width];
        for(String[] row:flattenBoard)
            Arrays.fill(row, "...");
        for(int i = 0;entSet.entArray.length!=' ';i++){
            flattenBoard[entSet.entArray[i].loc.getX()][entSet.entArray[i].loc.getY()]=entSet.entArray[i].getName(entSet.entArray[i]);
        }
        return flattenBoard;
    }
}