package prog2_a3.interfaces;

import prog2_a3.fatsquirrel.console.*;
import prog2_a3.fatsquirrel.core.*;

public interface UI {

    GameImpl game = new GameImpl();
    
	XY getCommand();
        void render (BoardView view);
	
}
