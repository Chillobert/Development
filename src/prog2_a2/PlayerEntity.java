package prog2_a2;

public class PlayerEntity extends Entity{
    private int id;
    private int energy;
    
    public PlayerEntity(int id, int energy, int x, int y){
        super(id,energy,x,y);
        this.id = id;
        this.energy = energy;
    };
    @Override
    public void nextStep() {

    }
    

}
