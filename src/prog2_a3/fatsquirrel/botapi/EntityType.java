package prog2_a3.fatsquirrel.botapi;

import prog2_a3.fatsquirrel.core.XY;

public enum EntityType {

    BADBEAST(XY.class),
    GOODBEAST(XY.class),
    BADPLANT(XY.class),
    GOODPLANT(XY.class),
    MasterSquirrel(XY.class),
    MiniSquirrel(XY.class),
    Wall(XY.class);

    Class position;

    EntityType(Class pos){
        this.position = pos;
    }
    
    public Class getPosition(){
        return position;
    }
}