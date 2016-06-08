
package de.hsa.games.fatsquirrel.botimpls;

import java.util.Random;

import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;

public class MiniBotImpl1 implements BotController{

    @Override
    public void nextStep(ControllerContext view) {
        view.move(randVector());
    }
    
    private XY randVector(){
        Random r = new Random();
        return new XY(new int[]{r.nextInt(3)-1,r.nextInt(3)-1});
    }
    
}
