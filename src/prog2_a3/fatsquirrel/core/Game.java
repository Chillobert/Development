package prog2_a3.fatsquirrel.core;

import java.util.Scanner;

public class Game {
prog2_a3.State state;
public Game (){
state = new prog2_a3.State();
};

    public void run(){
	while(true){
            render();
            processInput();
            update();
			}
	}

		
//Darstellung des Spielzustands auf dem Ausgabemedium
    void render(){
    	
    };
	
//Verarbeitung von Benutzereingaben
    public void processInput(){
        Scanner input = new Scanner(System.in);
        System.out.println("Wählen Sie eine Bewegungsrichtung für ihr Eichhörnchen: ");
        GuidedMasterSquirrel a;
        for(int i=0;state.board.entSet.entArray.length>i;i++){
            if(state.board.entSet.entArray[i] instanceof GuidedMasterSquirrel){
                a = (GuidedMasterSquirrel)state.board.entSet.entArray[i];
                a.in = input.next();
            }
        }
    };
	
//Ver�nderung des Spielzustandes -> Vorbereitung n�chster Render Vorgang	
    private void update() {
    EntitySet entSet = new EntitySet();
    entSet.nextStepAll();
    prog2_a3.FlattenedBoard fb = new prog2_a3.FlattenedBoard(); // Damit in jeder Runde ein aktuelles flattenedBoard erstellt wird.
    }	
	
}

/*
	public static void main(String[] args){
		
			/*
            Boolean play=true;
            EntitySet ent = new EntitySet();

            while(play){
                ent.add("BadBeast", 0, 1, 2);
                ent.add("GoodBeast" ,1,2,3);
                ent.add("GoodPlant",2,3,4);
                ent.add("BadPlant",3,4,5);
                ent.add("GuidedMasterSquirrel",4,5,6);
                ent.add("Wall", 5, 6, 7);
                System.out.println(ent.toString());
                ent.nextStepAll();
                System.out.println(ent.toString());
                ent.delete(3);
                System.out.println(ent.toString());
                play=false;
            }
            */		