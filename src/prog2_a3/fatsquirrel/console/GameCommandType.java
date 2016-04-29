package prog2_a3.fatsquirrel.console;

import prog2_a3.fatsquirrel.util.ui.console.*;

public enum GameCommandType implements CommandTypeInfo{
    HELP("help"," * list all commands"),
    EXIT("exit"," * exit the game"),
    ALL("all"," * i don't understand this command"),
    LEFT("a"," * master takes one step left"),
    UP("w"," * master takes one step up"),
    DOWN("s"," * master moves down"),
    RIGHT("d"," * master moves right"),
    MASTER_ENERGY("energy"," * shows master energy"),
    SPAWN_MINI("mini","<energy> * spawns mini-squirrel", int.class);
    
    private String name;
    private String helpText;
    private Class param1;
    
    GameCommandType(String name, String helpText){
        this.name = name;
        this.helpText = helpText;
    }
    GameCommandType(String name, String helpText, Class type1){
        this(name,helpText);
        this.param1 = type1;
    }
    
    @Override
    public String getName(){
        return name;
    }
    
    @Override
    public String getHelpText(){
        return helpText;
    }

    @Override
    public Class<?>[] getParamTypes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
