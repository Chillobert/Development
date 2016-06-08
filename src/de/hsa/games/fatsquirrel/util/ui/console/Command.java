
package de.hsa.games.fatsquirrel.util.ui.console;

import java.util.logging.Level;

import de.hsa.games.fatsquirrel.logger.GameLogger;

// TODO: Auto-generated Javadoc
/**
 * The Class Command.
 */
public class Command {
    
    /** The command type. */
    private CommandTypeInfo commandType;
    
    /** The params. */
    private Object[] params;
    
    /** The Constant logger. */
    private static final GameLogger logger = new GameLogger();
    
    /**
     * Instantiates a new command.
     *
     * @param commandType the command type
     * @param params the params
     */
    public Command(CommandTypeInfo commandType, Object... params){
        this.commandType = commandType;
        this.params = params;
    }
    
    /**
     * Instantiates a new command.
     *
     * @param commandType the command type
     */
    public Command(CommandTypeInfo commandType){
        this.commandType = commandType;
        logger.log(Level.FINEST, "Objekt der Klasse Command wurde erstellt");
    }
    
    /**
     * Gets the command type.
     *
     * @return the command type
     */
    public CommandTypeInfo getCommandType(){
        return commandType;
    }
    
    /**
     * Gets the params.
     *
     * @return the params
     */
    public Object[] getParams(){
        if(params[0] != null)
            return this.params;
        else
            return null;
    }
}
