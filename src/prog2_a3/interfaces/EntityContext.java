package prog2_a3.interfaces;

import prog2_a3.fatsquirrel.core.*;


public interface EntityContext {

    XY getSize();
    
    void tryMove(MiniSquirrel miniSquirrel, XY moveDirection);
    void tryMove(GoodBeast goodBeast, XY moveDirection);
    void tryMove(BadBeast badBeast, XY moveDirection);
    void tryMove(MasterSquirrel masterBot, XY moveDirection);
    
    PlayerEntity nearestPlayerEntity(XY pos);
    int getSquirrelEnergy();
    
    void spawnChild(MasterSquirrel parent, XY direction, int energy);
    
    void kill(Entity entity);
    void killAndReplace(Entity entity);
    String getEntityType(XY loc);
}
