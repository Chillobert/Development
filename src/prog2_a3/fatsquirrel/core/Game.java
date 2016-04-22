package prog2_a3.fatsquirrel.core;

import java.util.Random;
import java.util.Scanner;
import prog2_a3.*;

public class Game {
    public State state;
    private FlattenedBoard flattenedBoard;
    private XY input;
    public Game (){
        state = new State();
        input = new XY(new int[]{0,0});
    };

    public void run(){
	while(true){
            render();
            processInput();
            update();
	}
    }
		
//Darstellung des Spielzustands auf dem Ausgabemedium

    protected void render(){
        flattenedBoard = state.board.flatten();
    	System.out.println(flattenedBoard.entSet.toString());
    };
	
//Verarbeitung von Benutzereingaben

    protected void processInput(){
        Scanner input = new Scanner(System.in);
        System.out.println("Wählen Sie eine Bewegungsrichtung für ihr Eichhörnchen: ");
        input.hasNext();
        int[] vector = new int[]{0,0};
        switch(input.next()){
            case "a":vector[0]=-1;break;
            case "s":vector[1]=1;break;
            case "d":vector[0]=1;break;
            case "w":vector[1]=-1;break;
            case "q":vector[0]=-1;vector[1]=-1;break;
            case "e":vector[0]=1;vector[1]=-1;break;
            case "c":vector[0]=1;vector[1]=1;break;
            case "y":vector[0]=-1;vector[1]=1;break;
            default: System.out.println("Keine gültige Richtung");break;        
        }
        this.input = new XY(vector);
    }
	
//Ver�nderung des Spielzustandes -> Vorbereitung n�chster Render Vorgang

    protected void update() {
        //flattenedBoard = state.board.flatten();
        Random r = new Random();
        Entity[] entArray = state.board.entSet.getEntityArray();
        for(int i=0;entArray[i]!=null;i++){
            if("BadBeast".equals(entArray[i].getName()))
                flattenedBoard.tryMove((BadBeast)entArray[i],new XY(new int[]{r.nextInt(3)-1,r.nextInt(3)-1}));
            if("GoodBeast".equals(entArray[i].getName()))
                flattenedBoard.tryMove((GoodBeast)entArray[i],new XY(new int[]{r.nextInt(3)-1,r.nextInt(3)-1}));
            if("MiniSquirrel".equals(entArray[i].getName()))
                flattenedBoard.tryMove((MiniSquirrel)entArray[i],new XY(new int[]{r.nextInt(3)-1,r.nextInt(3)-1}));
            if(state.board.entSet.isInstance(entArray[i], MasterSquirrel.class))
                flattenedBoard.tryMove((MasterSquirrel)entArray[i],this.input);
        }
        flattenedBoard.entSet.nextStepAll(flattenedBoard.getVectors());
    }
}