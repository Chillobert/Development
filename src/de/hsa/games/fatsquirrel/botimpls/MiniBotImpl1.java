
package de.hsa.games.fatsquirrel.botimpls;

import java.util.Random;

import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;

// TODO: Auto-generated Javadoc
/**
 * The Class MiniBotImpl1.
 */
public class MiniBotImpl1 implements BotController{

    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.botapi.BotController#nextStep(de.hsa.games.fatsquirrel.botapi.ControllerContext)
     */
    @Override
    public void nextStep(ControllerContext view) {
        view.move(randVector());
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
