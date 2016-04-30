package prog2_a3.fatsquirrel.util.ui.console;

public enum  MyFavoriteCommandType implements CommandTypeInfo {
    HELP("help", "  * list all commands"),
    EXIT("exit", "  * exit program"),
    ADDI("addi", "<param1>  <param2>   * simple integer add ",Integer.class, Integer.class ),
    ADDF("addf", "<param1>  <param2>   * simple float add ",Float.class, Float.class ),
    ECHO("echo", "<param1>  <param2>   * echos param1 string param2 times ",String.class, Integer.class );
    
    private String commandName;
    private String helpText;
    private Class<?>[] paramTypes = new Class[]{null};
    
    private MyFavoriteCommandType(String name,String helpText){
        this.commandName = name;
        this.helpText = helpText;
    }
    
    private MyFavoriteCommandType(String name, String helpText, Class Type1,Class Type2){
        this(name,helpText);
        this.paramTypes = new Class[]{Type1,Type2}; 
    }

    @Override
    public String getName() {
        return commandName;
    }

    @Override
    public String getHelpText() {
        return helpText;
    }

    @Override
    public Class<?>[] getParamTypes() {
        return paramTypes;
    }
}
