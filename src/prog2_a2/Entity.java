package prog2_a2;

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

        public boolean equals(Entity entity ){
            return this.getClass().isInstance(entity);
        }
        
        public void randMove(){
            loc = new XY(loc.move(loc.randVect()));
        }
    
        @Override
        public String toString(){
            return ("id:"+id+", energy:"+energy+", X:"+loc.getX()+", Y:"+loc.getY());
        }

}
