package prog2_a3.interfaces;

import prog2_a3.fatsquirrel.console.*;

public interface UI {

    GameImpl game = new GameImpl();
    
	String getCommand();
        void render (BoardView view);
	
}
