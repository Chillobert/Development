
package prog2_a3.fatsquirrel.botimpls;

import java.util.Random;
import prog2_a3.fatsquirrel.botapi.BotController;
import prog2_a3.fatsquirrel.botapi.ControllerContext;
import prog2_a3.fatsquirrel.core.XY;

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
