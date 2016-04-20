package prog2_a3.interfaces;

import prog2_a3.fatsquirrel.core.BadBeast;
import prog2_a3.fatsquirrel.core.Entity;
import prog2_a3.fatsquirrel.core.GoodBeast;
import prog2_a3.fatsquirrel.core.MasterSquirrel;
import prog2_a3.fatsquirrel.core.MiniSquirrel;
import prog2_a3.fatsquirrel.core.PlayerEntity;
import prog2_a3.fatsquirrel.core.XY;

public interface EntityContext {

	
	public XY getSize();
	
	public XY getEntityType(XY xy);
	
	public PlayerEntity nearestPlayerEntity(XY xy);
	
	void tryMove(MiniSquirrel miniSquirrel, XY moveDirection);
	
	void tryMove(GoodBeast goodBeast, XY moveDirection);
	
	void tryMove(BadBeast badBeast, XY moveDirection);
	
	void tryMove(MasterSquirrel masterBot, XY moveDirection);
	
	void spawnChildBot(); //Parameter nicht sicher
	
	void kill(Entity entity);
	
	void killAndReplace(Entity entity);
	

}
