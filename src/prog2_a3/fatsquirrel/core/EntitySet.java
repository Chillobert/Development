package prog2_a3.fatsquirrel.core;

import java.util.Vector;
import java.util.logging.Level;

import javax.swing.JOptionPane;
import prog2_a3.interfaces.*;

public class EntitySet {

    private int numbOf=0;
    private int latestId=1;
    private Vector<Entity> entArray = new Vector();
    private int idColl=0;
    private static final GameLogger logger = new GameLogger();
    public EntitySet(){   
    logger.log(Level.FINEST, "Object der Klasse EntitySet erstellt");
    }
    
    public void add(String entityTyp, int x, int y){

        switch(entityTyp){
            case"BadPlant": entArray.add(new BadPlant(numbOf++,x,y));break;
            case"GoodBeast":entArray.add(new GoodBeast(numbOf++,x,y));break;
            case"GoodPlant":entArray.add(new GoodPlant(numbOf++,x,y));break;
            case"BadBeast":entArray.add(new BadBeast(numbOf++,x,y)); break;
            case"GuidedMasterSquirrel":entArray.add(new GuidedMasterSquirrel(numbOf++,x,y));break;
            case"Wall":entArray.add(new Wall(numbOf++,x,y));break;
            case"MasterSquirrelBot":entArray.add(new MasterSquirrelBot(numbOf++,x,y));break;
            default:break;           
        }
        latestId++;
    }
    
    public void addMini(MasterSquirrel master,int energy, XY direction){
         entArray.add(master.createDescendant(numbOf++,energy , direction.getX(), direction.getY()));
    }
    
    public void delete(int id){
        
        for(int i = 0; entArray.size()>i;i++){
            if (entArray.get(i).getId() == id)
                entArray.removeElementAt(i);
        }
    }
    
    public int getLatestId(){
        return numbOf;
    }
    
    public Vector getEntityArray(){
        return entArray;
    }
    @Override
    public String toString(){
        String output="";
        for(int i=0;entArray.size()<i;i++){
            output += entArray.get(i).getName() + "," + entArray.get(i).toString() + '\n';
        }
        return(output);
    }

    public void nextStepAll(EntityContext entContext, XY input) throws InterruptedException{
        boolean masterExists = false;
        for(int i=0;entArray.size()>i;i++){
            //if(entArray.get(i)!=null){
                if(isInstance(entArray.get(i),MasterSquirrel.class))
                    masterExists = true;
                
                if(entArray.get(i).getTimeout()<=0){
                if(isInstance(entArray.get(i),GuidedMasterSquirrel.class)){
                    if( input != null) 
                        ((GuidedMasterSquirrel)entArray.get(i)).nextStep(entContext,input);
                    }
                    else
                        entArray.get(i).nextStep(entContext);
                }
                else if(entArray.get(i).getTimeout()>0)
                    entArray.get(i).setTimeout(entArray.get(i).getTimeout()-1);
            //}
        }
        if(!masterExists){     	
            JOptionPane.showMessageDialog(null,"you lost! Press OK to exit");
            logger.log(Level.INFO, "Spiel beendet");
            System.exit(0);
        }
    }
    
    public boolean isInstance(Object o, Class c){
    return c.isInstance(o);
    }
    
}
