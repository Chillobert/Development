package prog2_a3.fatsquirrel.core;


public abstract class Entity{
	
	private int id;
	private int energy;
	protected XY loc;
	
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
	
        public void updateEnergy(int charge){
	energy = energy + charge;
        }

        public abstract void nextStep();
        
        //Referenzcheck; true falls beide von gleicher Klasse abstammen

        public boolean checkInstance(Entity entity ){
            return this.getClass().isInstance(entity);
        }
        
        public void randMove(){
            loc = loc.moveRandom();
        }
    
        @Override
        public String toString(){
            return ("id:"+id+", energy:"+energy+", X:"+loc.getX()+", Y:"+loc.getY());
        }

}
