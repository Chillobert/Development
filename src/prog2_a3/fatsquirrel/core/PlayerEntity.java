package prog2_a3.fatsquirrel.core;


public abstract class PlayerEntity extends Entity{
    private int id;
    private int energy;
    
    public PlayerEntity(int id, int energy, int x, int y){
        super(id,energy,x,y);
        this.id = id;
        this.energy = energy;
    };
    

}
