package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.EntityType;
import de.hsa.games.fatsquirrel.XY;

public interface ControllerContext {
	XY getViewLowerLeft();

	XY getViewUpperRight();

	EntityType getEntityAt(XY xy);

	void move(XY direction);

	void spawnMiniBot(XY direction, int energy);

	int getEnergy();
        
        void implode(int radius);
        
        XY getDirectionToParent();
        
        int getRemainingSteps();
}