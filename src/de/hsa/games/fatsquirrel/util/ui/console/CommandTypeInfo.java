package de.hsa.games.fatsquirrel.util.ui.console;

public interface CommandTypeInfo {
    
    String getName();
    String getHelpText();
    Class<?>[] getParamTypes();
}
