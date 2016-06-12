package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.core.BoardView;
import de.hsa.games.fatsquirrel.util.ui.console.*;

// TODO: Auto-generated Javadoc
/**
 * The Interface UI.
 */
public interface UI {
    
	/**
	 * Gets the command.
	 *
	 * @return the command
	 */
	Command getCommand();
        
        /**
         * Render.
         *
         * @param view the view
         */
        void render (BoardView view);
	
        /**
         * Message.
         *
         * @param message the message
         */
        void message (String message);
}
