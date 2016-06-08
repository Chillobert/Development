package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.EntityType;
import de.hsa.games.fatsquirrel.XY;

// TODO: Auto-generated Javadoc
/**
 * The Interface ControllerContext.
 */
public interface ControllerContext {
	
	/**
	 * Gets the view lower left.
	 *
	 * @return the view lower left
	 */
	XY getViewLowerLeft();

	/**
	 * Gets the view upper right.
	 *
	 * @return the view upper right
	 */
	XY getViewUpperRight();

	/**
	 * Gets the entity at.
	 *
	 * @param xy the xy
	 * @return the entity at
	 */
	EntityType getEntityAt(XY xy);

	/**
	 * Move.
	 *
	 * @param direction the direction
	 */
	void move(XY direction);

	/**
	 * Spawn mini bot.
	 *
	 * @param direction the direction
	 * @param energy the energy
	 */
	void spawnMiniBot(XY direction, int energy);

	/**
	 * Gets the energy.
	 *
	 * @return the energy
	 */
	int getEnergy();
        
        /**
         * Implode.
         *
         * @param radius the radius
         */
        void implode(int radius);
        
        /**
         * Gets the direction to parent.
         *
         * @return the direction to parent
         */
        XY getDirectionToParent();
        
        /**
         * Gets the remaining steps.
         *
         * @return the remaining steps
         */
        int getRemainingSteps();
}