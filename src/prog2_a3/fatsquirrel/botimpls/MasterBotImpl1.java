
package prog2_a3.fatsquirrel.botimpls;

import java.util.Random;
import prog2_a3.fatsquirrel.botapi.BotController;
import prog2_a3.fatsquirrel.botapi.ControllerContext;
import prog2_a3.fatsquirrel.core.XY;
import prog2_a3.fatsquirrel.proxy.*;

public class MasterBotImpl1 implements BotController{
    int i = 0;
    
    @Override
    public void nextStep(ControllerContext view) {
        ControllerContextProxy contControllerProxy = new ControllerContextProxy(view);
        ControllerContext contController = contControllerProxy.getProxy();
        contController.move(randVector());
            //view.move(randVector());
    }
    
    private XY randVector(){
        Random r = new Random();
        return new XY(new int[]{r.nextInt(3)-1,r.nextInt(3)-1});
    }
    
    
}
