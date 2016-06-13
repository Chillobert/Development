package de.hsa.games.fatsquirrel.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.logging.Level;

import de.hsa.games.fatsquirrel.UI;
import de.hsa.games.fatsquirrel.core.BoardView;
import de.hsa.games.fatsquirrel.logger.GameLogger;
import de.hsa.games.fatsquirrel.util.ui.console.*;
// TODO: Auto-generated Javadoc

/**
 * The Class ConsoleUI.
 */
public class ConsoleUI implements UI {

    /** The output stream. */
    private PrintStream outputStream;
    
    /** The input reader. */
    private BufferedReader inputReader;
    
    /** The command. */
    private Command command;
    
    /** The command types. */
    private GameCommandType[] commandTypes;
    
    /** The counter. */
    int counter = 0;
    
    /** The command puffer. */
    private Command commandPuffer;
    
    /** The Constant logger. */
    private static final GameLogger logger = new GameLogger();
    
    /**
     * Instantiates a new console ui.
     */
    public ConsoleUI() {
        this.outputStream = System.out;
        this.inputReader = new BufferedReader(new InputStreamReader(System.in));
        this.commandTypes = GameCommandType.values();
        logger.log(Level.FINEST, "Object der Klasse ConsoleUI erstellt");
    }
    
    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.UI#getCommand()
     */
    @Override
    public Command getCommand() {
        try {
            CommandScanner commandScanner = new CommandScanner(commandTypes,inputReader);
            command = commandScanner.next();
        } catch (IOException ioEx) {
        	logger.log(Level.WARNING, "Warnung: ConsoleUI.getCommand(); Ungueltige Eingabe des Spielers");
            outputStream.println("Das war keine gültige Eingabe. probier es mal mit help");
        }
        return command;
    }
    

    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.UI#render(de.hsa.games.fatsquirrel.core.BoardView)
     */
    @Override
    public void render(BoardView view) {
    	counter = counter + 1;
    	System.out.println("DurchlaufNr:" + counter); //testing
        for(int i = 0; i<=view.getSize().getY();i++){
            for(int j = 0;j<=view.getSize().getX();j++){
                switch(view.getEntityType(j,i)){
                    case " ":System.out.print("--"); break;
                    case "Wall":System.out.print("WA"); break;
                    case "BadBeast":System.out.print("BB");break;
                    case "BadPlant":System.out.print("BP");break;
                    case "GoodBeast":System.out.print("GB");break;
                    case "GoodPlant":System.out.print("GP");break;
                    case "GuidedMasterSquirrel":System.out.print("GS");break;
                    case "MiniSquirrel":case "MiniSquirrelBot":System.out.print("MS");break;
                    case "MasterSquirrelBot":System.out.print("SB");break;
                    default:System.out.print("FF");break;
                }
            }
            System.out.println();
        }   
    }

    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.UI#message(java.lang.String)
     */
    @Override
    public void message(String message) {
        System.out.println(message);
    }
    
}
