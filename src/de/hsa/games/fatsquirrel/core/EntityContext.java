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
 * The Interface EntityContext contains the movement Methods for the Entitys of the Game.
 */
public interface EntityContext {

    /**
     * This Method gets the Size of the Board.
     *
     * @return the size
     */
    XY getSize();
    
    /**
     * This Method handles the Movement attempt of a miniSquirrel.
     *
     * @param miniSquirrel the mini squirrel
     * @param moveDirection the move direction
     */
    void tryMove(MiniSquirrel miniSquirrel, XY moveDirection);
    
    /**
     * This Method handles the Movement attempt of a goodBeast.
     *
     * @param goodBeast the good beast
     * @param moveDirection the move direction
     */
    void tryMove(GoodBeast goodBeast, XY moveDirection);
    
    /**
     * This Method handles the Movement attempt of a BadBeast.
     *
     * @param badBeast the bad beast
     * @param moveDirection the move direction
     */
    void tryMove(BadBeast badBeast, XY moveDirection);
    
    /**
     * This Method handles the Movement attempt of a MasterBot.
     *
     * @param masterBot the master bot.
     * @param moveDirection the move direction.
     */
    void tryMove(MasterSquirrel masterBot, XY moveDirection);
    
    /**
     * This Method is looking for the nearest PlayerEntity starting from a given Position.
     *
     * @param pos is the given Position where we are looking from.
     * @return the nearest PlayerEntity.
     */
    PlayerEntity nearestPlayerEntity(XY pos);
    
    /**
     * This Method gets the Energy of a Squirrel.
     *
     * @return the amount of Energy from a Squirrel.
     */
    int getSquirrelEnergy();
    
    /**
     * This Method spawns a miniSquirrel from a MasterSquirrel.
     *
     * @param parent the parent MasterSquirrel.
     * @param direction the direction in which the miniSquirrel should spawn in.
     * @param energy the energy of the miniSquirrel at spawn.
     * @throws NotEnoughEnergyException gets thrown if the master has not enough Energy to spawn the miniSquirrel.
     */
    void spawnChild(MasterSquirrel parent, XY direction, int energy) throws NotEnoughEnergyException;
    
    /**
     * This Method kills an given Entity.
     *
     * @param entity the Entity who should get killed.
     */
    void kill(Entity entity);
    
    /**
     * This Method kills an Entity and spawns a new one.
     *
     * @param entity the Entity who should get killed and respawned.
     */
    void killAndReplace(Entity entity);
    
    /**
     * This Method gets the EntityType of a given Entity.
     *
     * @param loc the location of the Entity.
     * @return the entity Type.
     */
    String getEntityType(XY loc);
}