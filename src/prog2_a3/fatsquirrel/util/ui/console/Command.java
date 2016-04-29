
package prog2_a3.fatsquirrel.util.ui.console;

public class Command {
    private CommandTypeInfo commandType;
    private Object[] params;
    
    public Command(CommandTypeInfo commandType, Object[] params){
        this.commandType = commandType;
        this.params = params;
    }
    
    public Command(CommandTypeInfo commandType){
        this.commandType = commandType;
    }
    
    public CommandTypeInfo getCommandType(){
        return commandType;
    }
    
    public Object[] getParams(){
        return this.params;
    }
}
