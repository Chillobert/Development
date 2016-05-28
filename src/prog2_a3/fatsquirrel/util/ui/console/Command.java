
package prog2_a3.fatsquirrel.util.ui.console;

import java.util.logging.Level;

import prog2_a3.fatsquirrel.core.GameLogger;

public class Command {
    private CommandTypeInfo commandType;
    private Object[] params;
    private static final GameLogger logger = new GameLogger();
    public Command(CommandTypeInfo commandType, Object... params){
        this.commandType = commandType;
        this.params = params;
    }
    
    public Command(CommandTypeInfo commandType){
        this.commandType = commandType;
        logger.log(Level.FINEST, "Objekt der Klasse Command wurde erstellt");
    }
    
    public CommandTypeInfo getCommandType(){
        return commandType;
    }
    
    public Object[] getParams(){
        if(params[0] != null)
            return this.params;
        else
            return null;
    }
}
