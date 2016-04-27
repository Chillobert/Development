package prog2_a3.interfaces;

import prog2_a3.fatsquirrel.core.*;

public interface UI {

    
	MoveCommand getCommand();
        
        void render (BoardView view);
	
}
