package de.hsa.games.fatsquirrel;

import java.util.logging.Level;

import de.hsa.games.fatsquirrel.logger.GameLogger;

// TODO: Auto-generated Javadoc
/**
 * The Class MoveCommand.
 */
public class MoveCommand {
	
	/** The Constant logger. */
	private static final GameLogger logger = new GameLogger();
    
    /** The input. */
    String input;
    
/**
 * Instantiates a new move command.
 */
public MoveCommand(){
	logger.log(Level.FINEST, "Objekt der Klasse MoveCommand wurde erstellt");
}
    
    /**
     * Instantiates a new move command.
     *
     * @param input the input
     */
    public MoveCommand(String input){
        this.input = input;
    }
    
    /**
     * Convert command.
     *
     * @return the xy
     */
    public XY convertCommand(){
        int[] vector = new int[]{0,0};
    
         switch(this.input){
            case "a":vector[0]=-1;vector[1]=0;break;
            case "s":vector[0]=0; vector[1]=1;break;
            case "d":vector[0]=1; vector[1]=0;break;
            case "w":vector[0]=0; vector[1]=-1;break;
            default: System.out.println("Keine gültige Richtung");break;
         }
         return new XY(vector);
    }
}
