package de.hsa.games.fatsquirrel;

// TODO: Auto-generated Javadoc
/**
 * The Enum EntityType.
 */
public enum EntityType {

    /** The badbeast. */
    BADBEAST(XY.class),
    
    /** The goodbeast. */
    GOODBEAST(XY.class),
    
    /** The badplant. */
    BADPLANT(XY.class),
    
    /** The goodplant. */
    GOODPLANT(XY.class),
    
    /** The Master squirrel. */
    MasterSquirrel(XY.class),
    
    /** The Mini squirrel. */
    MiniSquirrel(XY.class),
    
    /** The Wall. */
    Wall(XY.class);

    /** The position. */
    Class position;

    /**
     * Instantiates a new entity type.
     *
     * @param pos the pos
     */
    EntityType(Class pos){
        this.position = pos;
    }
    
    /**
     * Gets the position.
     *
     * @return the position
     */
    public Class getPosition(){
        return position;
    }
}