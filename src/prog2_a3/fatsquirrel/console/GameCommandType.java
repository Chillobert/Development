package prog2_a3.fatsquirrel.console;

import prog2_a3.fatsquirrel.util.ui.console.*;

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
    private Class<?>[] paramTypes = new Class[]{null};
    
    GameCommandType(String name, String helpText){
        this.commandName = name;
        this.helpText = helpText;
    }
    GameCommandType(String name, String helpText, Class type1, Class type2){
        this(name,helpText);
        this.paramTypes = new Class[]{type1,type2};
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
