package de.hsa.games.fatsquirrel.util.ui.console;

// TODO: Auto-generated Javadoc
/**
 * The Interface CommandTypeInfo.
 */
public interface CommandTypeInfo {
    
    /**
     * Gets the name.
     *
     * @return the name
     */
    String getName();
    
    /**
     * Gets the help text.
     *
     * @return the help text
     */
    String getHelpText();
    
    /**
     * Gets the param types.
     *
     * @return the param types
     */
    Class<?>[] getParamTypes();
}
