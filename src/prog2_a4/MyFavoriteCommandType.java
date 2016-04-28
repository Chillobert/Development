package prog2_a4;

public enum  MyFavoriteCommandType implements CommandTypeInfo {
    HELP("help", "  * list all commands"),
    EXIT("exit", "  * exit program"),
    ADDI("addi", "<param1>  <param2>   * simple integer add ",int.class, int.class ),
    ADDF("addf", "<param1>  <param2>   * simple float add ",float.class, float.class ),
    ECHO("echo", "<param1>  <param2>   * echos param1 string param2 times ",String.class, int.class );
    
    private String commandName;
    private String helpText;
    private Class<?>[] ParamTypes = new Class[]{null};
    
    private MyFavoriteCommandType(String name,String helpText){
        this.commandName = name;
        this.helpText = helpText;
        
    }
    
    private MyFavoriteCommandType(String name, String helpText, Class Type1,Class Type2){
        this(name,helpText);
        this.ParamTypes = new Class[]{Type1,Type2}; 
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
        return ParamTypes;
    }
    
}
