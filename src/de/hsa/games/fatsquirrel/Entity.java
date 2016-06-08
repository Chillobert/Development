package de.hsa.games.fatsquirrel;

import java.util.logging.Level;

import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.logger.GameLogger;



public abstract class Entity{
	
	private int id;
	private int energy;
	private XY loc;
        private int penalty;
	public int collCount=0;
	private static final GameLogger logger = new GameLogger();

        public Entity(int id, int energy, int x, int y){

	this.id = id;
	this.energy = energy;
	this.loc = new XY(new int[]{x,y});
	logger.log(Level.FINEST, "Object der Klasse Entity erstellt");
        };

        public int getId(){
	return id;
        }

        public int getEnergy(){
	return energy;
        }
        
        public XY getLocation(){
            return loc;
        }
	
        public void updateEnergy(int charge){
	energy += charge;
        }

        public abstract void nextStep(EntityContext entCon, XY input);
        
        //Referenzcheck; true falls beide von gleicher Klasse abstammen

        public boolean checkInstance(Entity entity){
            return this.getClass().isInstance(entity);
        }
        
        public boolean equals(Entity ent){
            return((this.getId()==ent.getId())&&(this.getName().equals(ent.getName())));
        }
        
        public void randMove(){
            loc = loc.moveRandom();
        }
        
        public void move(XY vector){
            loc = loc.move(new int[]{vector.getX(),vector.getY()});
        }
        @Override
        public String toString(){
            return ("id:"+id+", energy:"+energy+", X:"+loc.getX()+", Y:"+loc.getY());
        }
        
        public String getName(){
            String name = this.getClass().getName();
            int i = name.lastIndexOf(".");
            name = name.substring(i+1);
            return name;
        }
        
        public int getTimeout(){
            return penalty;
        }
        
        public void setTimeout(int timeout){
            this.penalty = timeout;
        }
}