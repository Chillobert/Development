package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.BadBeast;
import de.hsa.games.fatsquirrel.Entity;
import de.hsa.games.fatsquirrel.GoodBeast;
import de.hsa.games.fatsquirrel.MasterSquirrel;
import de.hsa.games.fatsquirrel.MiniSquirrel;
import de.hsa.games.fatsquirrel.PlayerEntity;
import de.hsa.games.fatsquirrel.XY;

// TODO: Auto-generated Javadoc
/**
 * The Interface EntityContext.
 */
public interface EntityContext {

    /**
     * Gets the size.
     *
     * @return the size
     */
    XY getSize();
    
    /**
     * Try move.
     *
     * @param miniSquirrel the mini squirrel
     * @param moveDirection the move direction
     */
    void tryMove(MiniSquirrel miniSquirrel, XY moveDirection);
    
    /**
     * Try move.
     *
     * @param goodBeast the good beast
     * @param moveDirection the move direction
     */
    void tryMove(GoodBeast goodBeast, XY moveDirection);
    
    /**
     * Try move.
     *
     * @param badBeast the bad beast
     * @param moveDirection the move direction
     */
    void tryMove(BadBeast badBeast, XY moveDirection);
    
    /**
     * Try move.
     *
     * @param masterBot the master bot
     * @param moveDirection the move direction
     */
    void tryMove(MasterSquirrel masterBot, XY moveDirection);
    
    /**
     * Nearest player entity.
     *
     * @param pos the pos
     * @return the player entity
     */
    PlayerEntity nearestPlayerEntity(XY pos);
    
    /**
     * Gets the squirrel energy.
     *
     * @return the squirrel energy
     */
    int getSquirrelEnergy();
    
    /**
     * Spawn child.
     *
     * @param parent the parent
     * @param direction the direction
     * @param energy the energy
     * @throws NotEnoughEnergyException the not enough energy exception
     */
    void spawnChild(MasterSquirrel parent, XY direction, int energy) throws NotEnoughEnergyException;
    
    /**
     * Kill.
     *
     * @param entity the entity
     */
    void kill(Entity entity);
    
    /**
     * Kill and replace.
     *
     * @param entity the entity
     */
    void killAndReplace(Entity entity);
    
    /**
     * Gets the entity type.
     *
     * @param loc the loc
     * @return the entity type
     */
    String getEntityType(XY loc);
}