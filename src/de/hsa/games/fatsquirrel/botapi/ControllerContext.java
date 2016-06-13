package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.EntityType;
import de.hsa.games.fatsquirrel.XY;

/**
 * The Interface ControllerContext includes all methods, that can be called from bots to perform their strategy.
 */
public interface ControllerContext {
	
	/**
	 * Gets the lower left corner of the bots view.
	 *
	 * @return the view lower left
	 */
	XY getViewLowerLeft();

	/**
	 * Gets the upper right corner of the bots view.
	 *
	 * @return the view upper right
	 */
	XY getViewUpperRight();

	/**
	 * Gets the entity at specific position.
	 *
	 * @param xy the xy
	 * @return the entity at
	 */
	EntityType getEntityAt(XY xy);

	/**
	 * moves squirrel to direction.
	 *
	 * @param direction the direction
	 */
	void move(XY direction);

	/**
	 * Spawn mini bot that helps master bot to collect energy.
	 *
	 * @param direction the direction
	 * @param energy the energy
	 */
	void spawnMiniBot(XY direction, int energy);

	/**
	 * returns the energy of the bot.
	 *
	 * @return the energy
	 */
	int getEnergy();
        
        /**
         * Minisquirrel implodes, what damages entitys around it and spends all recevied energy to master.
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
         * Gets the remaining steps in this round.
         *
         * @return the remaining steps
         */
        int getRemainingSteps();
}