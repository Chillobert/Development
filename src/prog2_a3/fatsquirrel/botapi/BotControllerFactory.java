package prog2_a3.fatsquirrel.botapi;

public interface BotControllerFactory {
	
	BotController[] createMasterBotController(String[] masterImpls);
	BotController createMiniBotController();
}
