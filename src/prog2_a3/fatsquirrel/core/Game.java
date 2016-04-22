package prog2_a3.fatsquirrel.core;

import java.util.Random;
import java.util.Scanner;
import prog2_a3.*;

public class Game {
    public State state;
    private FlattenedBoard flattenedBoard;
    public Game (){
        state = new State();
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
    	
    };
	
//Verarbeitung von Benutzereingaben

    protected void processInput(){
        Scanner input = new Scanner(System.in);
        System.out.println("Wählen Sie eine Bewegungsrichtung für ihr Eichhörnchen: ");
        GuidedMasterSquirrel a;
        Entity[] entArray = state.board.entSet.getEntityArray();
        for(int i=0; entArray.length>i;i++){
            if(entArray[i] instanceof GuidedMasterSquirrel){
                a = (GuidedMasterSquirrel)entArray[i];
                a.in = input.next();
            }
        }
    }
	
//Ver�nderung des Spielzustandes -> Vorbereitung n�chster Render Vorgang

    protected void update() {
        flattenedBoard = state.board.flatten();
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
                flattenedBoard.tryMove((MasterSquirrel)entArray[i],new XY(new int[]{r.nextInt(3)-1,r.nextInt(3)-1}));//Hier Eingabe als XY übergeben, statt random
        }
        state.board.entSet.nextStepAll(flattenedBoard.getVectors());
    }	
}