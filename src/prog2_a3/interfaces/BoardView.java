package prog2_a3.interfaces;

import prog2_a3.fatsquirrel.core.*;
        
public interface BoardView {

    String getEntityType(int x, int y);
    
    XY getSize();
}
