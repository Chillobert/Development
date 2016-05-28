package prog2_a3.fatsquirrel.botapi;

import prog2_a3.fatsquirrel.core.XY;

public interface MasterBotControllerContext extends ControllerContext{
    
    	void spawnMiniBot(XY direction, int energy);
}
