package prog2_a3.fatsquirrel.botapi;

public class BotControllerFactoryImpl implements BotControllerFactory {

    //MasterBotController erstellen
    @Override
    public BotController createMasterBotController() {
        return new BotControllerMasterImpl();
    }

    //MiniBotController erstellen
    @Override
    public BotController createMiniBotController() {
        return new BotControllerMiniImpl();
    }
}