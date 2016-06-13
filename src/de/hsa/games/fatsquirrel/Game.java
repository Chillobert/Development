package de.hsa.games.fatsquirrel;

import java.util.logging.Level;

import de.hsa.games.fatsquirrel.core.FlattenedBoard;
import de.hsa.games.fatsquirrel.logger.GameLogger;

// TODO: Auto-generated Javadoc
/**
 * The Class Game is an abstract Class and extends the GameImpl.
 * These Methods are there for the GameLoop.
 */
public abstract class Game {
    
    /** The state. */
    public State state;
    
    /** The flattened board. */
    public FlattenedBoard flattenedBoard;
    
    /** The input. */
    public XY input;
    
    /** The fps. */
    public final int FPS = 12;
    
    /** The Constant logger. */
    private static final GameLogger logger = new GameLogger();
    
    /**
     * Instantiates a new game.
     */
    public Game (){
        state = new State();
        input = new XY(new int[]{0,0});
        logger.log(Level.FINEST, "Objekt der Klasse Game wurde erstellt");
    };

    /**
     * This Method controls the Gameloop for the different variants.
     * 
     */
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
    
    /**
     * This Method handles the userInput.
     */
    public void process(){
    	processInput();
    }
		
//Darstellung des Spielzustands auf dem Ausgabemedium

    /**
 * This Method is rendering the Game for the output (console or gui).
 */
protected abstract void render();
	
//Verarbeitung von Benutzereingaben

    /**
 * This Method processes the Input..
 */
protected abstract void processInput();
	
//Ver�nderung des Spielzustandes -> Vorbereitung n�chster Render Vorgang

    /**
 * This Method updates the Game after something happened.
 */
protected abstract void update();
}
