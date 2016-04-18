package prog2_a3;

import java.util.Arrays;
import java.util.Random;

public class Board {
	
private int length;
private int width;
private int amountGoodBeasts;
private int amountBadBeasts;
private int amountGoodPlants;
private int amountBadPlants;
private int amountWalls;
private String[][] board;


    public Board(){
	BoardConfig config = new BoardConfig();
	this.length = config.getLength();
	this.width = config.getWidth();
	this.amountGoodBeasts = config.getAmountGoodBeasts();
	this.amountBadBeasts = config.getAmountBadBeasts();
	this.amountGoodPlants = config.getAmountGoodPlants();
	this.amountBadPlants = config.getAmountBadPlants();
	this.board = new String[config.getLength()][config.getWidth()];
	fillBoard(2,3,2,2,2);
	};
    
    //Erstellen aller Entitys an zufälligem Ort
    private void fillBoard(int amountGoodBeasts, int amountBadBeasts, int amountGoodPlants, int amountBadPlants, int amountWalls){
	EntitySet entSet = new EntitySet ();
        for(int i = 0; i!=amountGoodBeasts;i++)
            entSet.add("GoodBeast", randLoc()[0], randLoc()[1]);
        for(int i = 0; i!=amountBadBeasts;i++)
            entSet.add("BadBeast", randLoc()[0], randLoc()[1]);
        for(int i = 0; i!=amountGoodPlants;i++)
            entSet.add("GoodPlant", randLoc()[0], randLoc()[1]);
        for(int i = 0; i!=amountBadPlants;i++)
            entSet.add("BadPlant", randLoc()[0], randLoc()[1]);
        for(int i = 0; i!=amountBadPlants;i++)
            entSet.add("Wall", randLoc()[0], randLoc()[1]);
        for(int i = 0; i<= this.length;i++){
            entSet.add("Wall", i, 0);
            entSet.add("Wall", i, this.width);
        }
        for(int i = 1; i<this.width;i++){
            entSet.add("Wall", 0, i);
            entSet.add("Wall", this.length, i);
        }
    };

    private int[] randLoc(){
        Random r = new Random();
        return new int[]{r.nextInt(this.length),r.nextInt(this.width)};
    }
    
@Override
    public String toString(){
    		//Möchte er bestimmt selbst geschrieben haben
    	return (Arrays.deepToString(board) + "\n");
    }

    public int getEntityCount() {
		int amount = 0;
		return amount;
		
	}
	
}
