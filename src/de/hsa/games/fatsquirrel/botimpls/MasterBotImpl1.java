
package de.hsa.games.fatsquirrel.botimpls;

import java.util.Random;

import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.proxy.*;

// TODO: Auto-generated Javadoc
/**
 * The Class MasterBotImpl1.
 */
public class MasterBotImpl1 implements BotController{
    
    /** The i. */
    int i = 0;
    
    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.botapi.BotController#nextStep(de.hsa.games.fatsquirrel.botapi.ControllerContext)
     */
    @Override
    public void nextStep(ControllerContext view) {
        ControllerContextProxy contControllerProxy = new ControllerContextProxy(view);
        ControllerContext contController = contControllerProxy.getProxy();
        contController.move(randVector());
        if(i%10 ==0)
            System.out.println("remaining Steps: "+view.getRemainingSteps());
        i++;
            //view.move(randVector());
    }
    
    /**
     * Rand vector.
     *
     * @return the xy
     */
    private XY randVector(){
        Random r = new Random();
        return new XY(new int[]{r.nextInt(3)-1,r.nextInt(3)-1});
    }
    
    
}
