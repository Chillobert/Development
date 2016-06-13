package de.hsa.games.fatsquirrel;

import java.util.logging.Level;

import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.logger.GameLogger;

/**
 * The Class Entity.
 * This Class is the Superclass for all Entitys which are getting created for the Game.
 */
public abstract class Entity{
	
	/** The id. */
	private int id;
	
	/** The energy. */
	private int energy;
	
	/** current location of the Entity */
	private XY loc;
        
        /** number of rounds, a entity is unable to move or operate a command */
        private int penalty;
	
	/** number of collisions a badBeast already had */
	public int collCount=0;
	
	/** The Constant logger. */
	private static final GameLogger logger = new GameLogger();

        /**
         * Instantiates a new entity.
         *
         * @param id the id
         * @param energy the energy
         * @param x the x
         * @param y the y
         */
        public Entity(int id, int energy, int x, int y){

	this.id = id;
	this.energy = energy;
	this.loc = new XY(new int[]{x,y});
	logger.log(Level.FINEST, "Object der Klasse Entity erstellt");
        };

        /**
         * Gets the id.
         *
         * @return the id
         */
        public int getId(){
	return id;
        }

        /**
         * Gets the energy.
         *
         * @return the energy
         */
        public int getEnergy(){
	return energy;
        }
        
        /**
         * Gets the location.
         *
         * @return the location
         */
        public XY getLocation(){
            return loc;
        }
	
        /**
         * Update energy.
         *
         * @param charge the charge
         */
        public void updateEnergy(int charge){
	energy += charge;
        }

        /**
         * This method operates the next command of the Entity.
         *
         * @param entCon implementation of EntityContext interface
         * @param input xy-vector for direction
         */
        public abstract void nextStep(EntityContext entCon, XY input);
        
        //Referenzcheck; true falls beide von gleicher Klasse abstammen

        /**
         * Check instance.
         *
         * @param entity the entity
         * @return true, if successful
         */
        public boolean checkInstance(Entity entity){
            return this.getClass().isInstance(entity);
        }
        
        /**
         * Equals.
         *
         * @param ent the ent
         * @return true, if successful
         */
        public boolean equals(Entity ent){
            return((this.getId()==ent.getId())&&(this.getName().equals(ent.getName())));
        }
        
        /**
         * Rand move.
         */
        public void randMove(){
            loc = loc.moveRandom();
        }
        
        /**
         * Move.
         *
         * @param vector the vector
         */
        public void move(XY vector){
            loc = loc.move(new int[]{vector.getX(),vector.getY()});
        }
        
        /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString(){
            return ("id:"+id+", energy:"+energy+", X:"+loc.getX()+", Y:"+loc.getY());
        }
        
        /**
         * Gets the name.
         *
         * @return the name
         */
        public String getName(){
            String name = this.getClass().getName();
            int i = name.lastIndexOf(".");
            name = name.substring(i+1);
            return name;
        }
        
        /**
         * Gets the timeout.
         *
         * @return the timeout
         */
        public int getTimeout(){
            return penalty;
        }
        
        /**
         * Sets the timeout.
         *
         * @param timeout the new timeout
         */
        public void setTimeout(int timeout){
            this.penalty = timeout;
        }
}