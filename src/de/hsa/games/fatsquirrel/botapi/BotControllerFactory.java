package de.hsa.games.fatsquirrel.botapi;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating BotController objects.
 */
public interface BotControllerFactory {
	
	/**
	 * Creates a new BotController object.
	 *
	 * @param masterImpls the master impls
	 * @return the bot controller[]
	 */
	BotController[] createMasterBotController(String[] masterImpls);
	
	/**
	 * Creates a new BotController object.
	 *
	 * @return the bot controller
	 */
	BotController createMiniBotController();
}
