package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.EntityType;
import de.hsa.games.fatsquirrel.XY;

/**
 * The Interface ControllerContext includes all methods, that can be called from bots to perform their strategy.
 * The Bot gets a part of the board as view in the shape of a rectangle.
 */
public interface ControllerContext {
	
	/**
	 * Gets the lower left corner of the bots sight as XY-object.
	 * @see XY
	 * @return the view lower left
	 */
	XY getViewLowerLeft();

	/**
	 * Gets the upper right corner of the bots sigth as XY-object.
	 * @see XY
	 * @return the view upper right
	 */
	XY getViewUpperRight();

	/**
	 * Gets the entity at specific position.
	 * @see XY
	 * @param xy the location you want to get the entity from
	 * @return the entity at the specified location
	 */
	EntityType getEntityAt(XY xy);

	/**
	 * moves squirrel to direction, using the tryMove method from EntityContext
         * @see EntityContext
	 * @see XY
	 * @param direction the direction
	 */
	void move(XY direction);

	/**
	 * Spawn mini bot that helps master bot to collect energy.
         * The logic of this Mini can be implemented in BotController.
         * @see BotController
	 * @see XY
	 * @param direction the direction
	 * @param energy the energy
	 */
	void spawnMiniBot(XY direction, int energy);

	/**
	 * returns the energy of the Bot-Entity.
	 * @see Entity
	 * @return the energy
	 */
	int getEnergy();
        
        /**
         * Minisquirrel implodes, what damages entitys around it and spends all recevied energy to master.
         *
         * @param radius the radius of explosion
         */
        void implode(int radius);
        
        /**
         * Gets the direction to parent as XY vector.
         * @see XY
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