package prog2_a3.fatsquirrel.core;

import prog2_a3.Launcher;

public abstract class Game {
    public State state;
    public FlattenedBoard flattenedBoard;
    public XY input;
    public final int FPS = 10;
    public Game (){
        state = new State();
        input = new XY(new int[]{0,0});
    };

    public void run(){  		
    	 if(Launcher.getMode() == 1 ){
    		while(true){
    			render();
    			processInput();
    			update();
    		}
    	
    	}
    	
    	else if(Launcher.getMode() == 2 || Launcher.getMode() == 3){
    		render();
    		if(Launcher.getMode() == 3)
    		processInput();
    		update();
    	}
    	
    }
    
    public void process(){
    	processInput();
    }
		
//Darstellung des Spielzustands auf dem Ausgabemedium

    protected abstract void render();
	
//Verarbeitung von Benutzereingaben

    protected abstract void processInput();
	
//Ver�nderung des Spielzustandes -> Vorbereitung n�chster Render Vorgang

    protected abstract void update();
}
