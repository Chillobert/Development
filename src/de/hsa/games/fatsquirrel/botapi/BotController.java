
package de.hsa.games.fatsquirrel.botapi;

/**
 * The Interface BotController.
 */
public interface BotController {
	
	/**
	 * nextStep overrides the nextStep-methode of Entity for the current used bot.
	 *
	 * @param view an implementation of the ControllerContext interface
	 */
	void nextStep(ControllerContext view);
}
