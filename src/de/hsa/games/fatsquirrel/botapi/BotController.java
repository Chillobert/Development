
package de.hsa.games.fatsquirrel.botapi;

/**
 * The Interface BotController.
 */
public interface BotController {
	
	/**
	 * nextStep overrides the nextStep-method of Entity for the current used bot.
         * all methods from ControllerContext can be used to implement the loot-strategy.
	 * @see ControllerContext
	 * @param view an implementation of the ControllerContext interface
	 */
	void nextStep(ControllerContext view);
}
