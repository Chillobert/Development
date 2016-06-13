package de.hsa.games.fatsquirrel.botapi;

import java.util.Random;

import de.hsa.games.fatsquirrel.XY;

/**
 * The Class MiniBotImpl implements the strategy for all Minisquirrels, how to collect energy for their master.
 */
public class MiniBotImpl implements BotController{

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