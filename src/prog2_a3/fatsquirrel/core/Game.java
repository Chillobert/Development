package prog2_a3.fatsquirrel.core;

import java.util.Scanner;
import prog2_a3.*;
import prog2_a3.interfaces.*;

public class Game implements UI{

    State state;
    
    public Game (){
        state = new State();
    };

@Override
    public void run(){
	while(true){
            render();
            processInput();
            update();
	}
    }

		
//Darstellung des Spielzustands auf dem Ausgabemedium
@Override
    public void render(){
    	
    };
	
//Verarbeitung von Benutzereingaben
@Override
    public void processInput(){
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
    };
	
//Ver�nderung des Spielzustandes -> Vorbereitung n�chster Render Vorgang
@Override
    public void update() {
        EntitySet entSet = new EntitySet();
        entSet.nextStepAll();
    }	
}