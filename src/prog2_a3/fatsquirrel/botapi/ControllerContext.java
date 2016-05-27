package prog2_a3.fatsquirrel.botapi;

import prog2_a3.fatsquirrel.core.XY;

public interface ControllerContext {
	XY getViewLowerLeft();

	XY getViewUpperRight();

	EntityType getEntityAt(XY xy);

	void move(XY direction);

	void spawnMiniBot(XY direction, int energy);

	int getEnergy();
}