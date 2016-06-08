package de.hsa.games.fatsquirrel.core.unit;


import de.hsa.games.fatsquirrel.BadBeast;
import de.hsa.games.fatsquirrel.Entity;
import de.hsa.games.fatsquirrel.GoodBeast;
import de.hsa.games.fatsquirrel.MasterSquirrel;
import de.hsa.games.fatsquirrel.MiniSquirrel;
import de.hsa.games.fatsquirrel.PlayerEntity;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.core.NotEnoughEnergyException;

public class EntityMethodProxy implements EntityContext{
	private XY direction = new XY(new int[]{0,-1});
	private String params;
	
	public EntityMethodProxy(){
	};
	public String generateNextStep(Entity entity){
		switch (entity.getName()) {
		case "GoodBeast":
			this.tryMove((GoodBeast)entity, direction);
			return params;
		case "BadBeast":
			this.tryMove((BadBeast)entity, direction);
			return params;
		case "GuidedMasterSquirrel":
			this.tryMove((MasterSquirrel)entity, direction);
			return params;
		case "MiniSquirrel":
			this.tryMove((MiniSquirrel)entity, direction);
			return params;
			
		default:
			return null;
		}
		
	}


	@Override
	public XY getSize() {
		return null;
	}

	@Override
	public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection) {
		params = "MiniSquirrel";
		
	}

	@Override
	public void tryMove(GoodBeast goodBeast, XY moveDirection) {
		params = "GoodBeast";
		
	}

	@Override
	public void tryMove(BadBeast badBeast, XY moveDirection) {
		params = "BadBeast";
		
	}

	@Override
	public void tryMove(MasterSquirrel masterBot, XY moveDirection) {
		params = "MasterSquirrel";
	}

	@Override
	public PlayerEntity nearestPlayerEntity(XY pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSquirrelEnergy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void spawnChild(MasterSquirrel parent, XY direction, int energy) throws NotEnoughEnergyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kill(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void killAndReplace(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getEntityType(XY loc) {
		// TODO Auto-generated method stub
		return null;
	};
	
	
}
