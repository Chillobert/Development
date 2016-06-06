package prog2_a3.fatsquirrel.botapi;

import java.util.logging.Level;
import java.util.logging.Logger;
import prog2_a3.fatsquirrel.botimpls.*;

public class BotControllerFactoryImpl implements BotControllerFactory {

    //MasterBotController erstellen
    
    @Override
    public BotController[] createMasterBotController(String[] masterImpls) {
        BotController[] botControllerArray = new BotController[masterImpls.length];
        for(int i = 0; i < masterImpls.length; i++){
            try {
                botControllerArray[i] = (BotController)Class.forName(masterImpls[i]).newInstance();
            } catch (ClassNotFoundException ex) {
                System.out.println("Invoke Class not Found in BotControllerFactory");
            } catch(InstantiationException ex){
                System.out.println("exception, InstantiationException");
            } catch(IllegalAccessException ex){
                
            }
        }
        //getter aus BoardConfig benutzen um die Namen zu bekommen, per reflection die richtige Datei suchen und als Array alle Bots ausgeben?
        //dann in Board.fillBoard der Reihe nach add mit den ArrayObjekten ausführen. Vielleicht besser Mit Vector oder LinkedList
        return botControllerArray;
    }

    //MiniBotController erstellen
    @Override
    public BotController createMiniBotController() {
        return new MiniBotImpl1();
    }
}