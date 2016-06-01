package prog2_a3.fatsquirrel.botapi;

import prog2_a3.fatsquirrel.botimpls.MasterBotImpl1;
import prog2_a3.fatsquirrel.botimpls.MiniBotImpl1;

public class BotControllerFactoryImpl implements BotControllerFactory {

    //MasterBotController erstellen
    @Override
    public BotController createMasterBotController() {
        //getter aus BoardConfig benutzen um die Namen zu bekommen, per reflection die richtige Datei suchen und als Array alle Bots ausgeben?
        //dann in Board.fillBoard der Reihe nach add mit den ArrayObjekten ausführen. Vielleicht besser Mit Vector oder LinkedList
        return new MasterBotImpl1();
    }

    //MiniBotController erstellen
    @Override
    public BotController createMiniBotController() {
        return new MiniBotImpl1();
    }
}