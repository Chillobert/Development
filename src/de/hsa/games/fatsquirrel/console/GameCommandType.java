package de.hsa.games.fatsquirrel.console;

import java.util.logging.Level;

import de.hsa.games.fatsquirrel.logger.GameLogger;
import de.hsa.games.fatsquirrel.util.ui.console.*;

// TODO: Auto-generated Javadoc
/**
 * The Enum GameCommandType.
 */
public enum GameCommandType implements CommandTypeInfo{
    
    /** The help. */
    HELP("help"," * list all commands"),
    
    /** The exit. */
    EXIT("exit"," * exit the game"),
    
    /** The all. */
    ALL("all"," * list all Entities"),
    
    /** The left. */
    LEFT("left"," * master takes one step left"),
    
    /** The up. */
    UP("up"," * master takes one step up"),
    
    /** The down. */
    DOWN("down"," * master moves down"),
    
    /** The right. */
    RIGHT("right"," * master moves right"),
    
    /** The master energy. */
    MASTER_ENERGY("energy"," * shows master energy"),
    
    /** The spawn mini. */
    SPAWN_MINI("mini","<energy> <direction> * spawns mini-squirrel", int.class, String.class);

    /** The command name. */
    private String commandName;
    
    /** The help text. */
    private String helpText;
    
    /** The param types. */
    private Class<?>[] paramTypes;

    /**
     * Instantiates a new game command type.
     *
     * @param name the name
     * @param helpText the help text
     * @param params the params
     */
    GameCommandType(String name, String helpText, Class... params){
        this.commandName = name;
        this.helpText = helpText;
        this.paramTypes = params;

    }

    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.util.ui.console.CommandTypeInfo#getName()
     */
    @Override
    public String getName(){
        return commandName;
    }

    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.util.ui.console.CommandTypeInfo#getHelpText()
     */
    @Override
    public String getHelpText(){
        return helpText;
    }

    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.util.ui.console.CommandTypeInfo#getParamTypes()
     */
    @Override
    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

}