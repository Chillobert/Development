package prog2_a4;

public interface CommandTypeInfo {
    
            
    CommandTypeInfo[] commandTypeInfo = new CommandTypeInfo[]{MyFavoriteCommandType.HELP,MyFavoriteCommandType.ADDF,MyFavoriteCommandType.ADDI,MyFavoriteCommandType.ECHO,MyFavoriteCommandType.EXIT};
    CommandScanner commandScanner = new CommandScanner(commandTypeInfo);
    
    String getName();
    String getHelpText();
    Class<?>[] getParamTypes();
    CommandTypeInfo[] getCommandTypeInfo();
}
