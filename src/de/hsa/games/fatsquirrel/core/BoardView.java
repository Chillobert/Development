package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.Entity;
import de.hsa.games.fatsquirrel.XY;

public interface BoardView {

    String getEntityType(int x, int y);
    
    Entity getEntity(int x, int y);
    
    XY getSize();
}
