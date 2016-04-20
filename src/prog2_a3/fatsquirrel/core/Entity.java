package prog2_a3.fatsquirrel.core;


public abstract class Entity{
	
	private int id;
	private int energy;
	private XY loc;
        private int penalty;
	
        //Constructor
        public Entity(){
        };

        public Entity(int id, int energy, int x, int y){

	this.id = id;
	this.energy = energy;
	this.loc = new XY(new int[]{x,y});
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
	energy = energy + charge;
        }

        public abstract void nextStep(XY vector);
        public abstract void nextStep();
        
        //Referenzcheck; true falls beide von gleicher Klasse abstammen

        public boolean checkInstance(Entity entity){
            return this.getClass().isInstance(entity);
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
            
            return this.getClass().getName();
        }
        
        public int getTimeout(){
            return penalty;
        }
        
        public void setTimeout(int timeout){
            this.penalty = timeout;
        }
}
