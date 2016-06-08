package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.BadBeast;
import de.hsa.games.fatsquirrel.Entity;
import de.hsa.games.fatsquirrel.GoodBeast;
import de.hsa.games.fatsquirrel.MasterSquirrel;
import de.hsa.games.fatsquirrel.MiniSquirrel;
import de.hsa.games.fatsquirrel.PlayerEntity;
import de.hsa.games.fatsquirrel.XY;

public interface EntityContext {

    XY getSize();
    
    void tryMove(MiniSquirrel miniSquirrel, XY moveDirection);
    void tryMove(GoodBeast goodBeast, XY moveDirection);
    void tryMove(BadBeast badBeast, XY moveDirection);
    void tryMove(MasterSquirrel masterBot, XY moveDirection);
    
    PlayerEntity nearestPlayerEntity(XY pos);
    int getSquirrelEnergy();
    
    void spawnChild(MasterSquirrel parent, XY direction, int energy) throws NotEnoughEnergyException;
    
    void kill(Entity entity);
    void killAndReplace(Entity entity);
    String getEntityType(XY loc);
}