package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.core.BoardView;
import de.hsa.games.fatsquirrel.util.ui.console.*;

public interface UI {
    
	Command getCommand();
        
        void render (BoardView view);
	
        void message (String message);
}
