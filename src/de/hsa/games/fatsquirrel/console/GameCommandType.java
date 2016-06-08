package de.hsa.games.fatsquirrel.console;

import java.util.logging.Level;

import de.hsa.games.fatsquirrel.logger.GameLogger;
import de.hsa.games.fatsquirrel.util.ui.console.*;

public enum GameCommandType implements CommandTypeInfo{
    HELP("help"," * list all commands"),
    EXIT("exit"," * exit the game"),
    ALL("all"," * list all Entities"),
    LEFT("left"," * master takes one step left"),
    UP("up"," * master takes one step up"),
    DOWN("down"," * master moves down"),
    RIGHT("right"," * master moves right"),
    MASTER_ENERGY("energy"," * shows master energy"),
    SPAWN_MINI("mini","<energy> <direction> * spawns mini-squirrel", int.class, String.class);

    private String commandName;
    private String helpText;
    private Class<?>[] paramTypes;

    GameCommandType(String name, String helpText, Class... params){
        this.commandName = name;
        this.helpText = helpText;
        this.paramTypes = params;

    }

    @Override
    public String getName(){
        return commandName;
    }

    @Override
    public String getHelpText(){
        return helpText;
    }

    @Override
    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

}