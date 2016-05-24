package prog2_a3.interfaces;

import prog2_a3.fatsquirrel.util.ui.console.*;

public interface UI {
    
	Command getCommand();
        
        void render (BoardView view);
	
        void message (String message);
}
