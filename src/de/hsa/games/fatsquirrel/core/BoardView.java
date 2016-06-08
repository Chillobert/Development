package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.Entity;
import de.hsa.games.fatsquirrel.XY;

// TODO: Auto-generated Javadoc
/**
 * The Interface BoardView.
 */
public interface BoardView {

    /**
     * Gets the entity type.
     *
     * @param x the x
     * @param y the y
     * @return the entity type
     */
    String getEntityType(int x, int y);
    
    /**
     * Gets the entity.
     *
     * @param x the x
     * @param y the y
     * @return the entity
     */
    Entity getEntity(int x, int y);
    
    /**
     * Gets the size.
     *
     * @return the size
     */
    XY getSize();
}
