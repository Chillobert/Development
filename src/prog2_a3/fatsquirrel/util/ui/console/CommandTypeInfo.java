package prog2_a3.fatsquirrel.util.ui.console;

public interface CommandTypeInfo {
    
    String getName();
    String getHelpText();
    Class<?>[] getParamTypes();
}
