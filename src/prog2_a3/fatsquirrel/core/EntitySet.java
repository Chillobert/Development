package prog2_a3.fatsquirrel.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import prog2_a3.interfaces.*;

public class EntitySet {

    private int numbOf=0;
    private Vector<Entity> entArray = new Vector();
    private int idColl=0;
    private static final GameLogger logger = new GameLogger();
    private int stepsThisRound;
    private int remainingSteps;
    Map<String,LinkedList<Integer>> highscore;
    
    public EntitySet(){  
        this.highscore = new HashMap();
        //für jeden MasterSquirrel eine LinkedList in der HashMap initialisieren
        for(int i = 0; entArray.size()>i;i++){
            if(isInstance(entArray.get(i),GuidedMasterSquirrel.class))
                highscore.put(entArray.get(i).getName(), new LinkedList<Integer>());
            if(isInstance(entArray.get(i),MasterSquirrelBot.class))
                highscore.put(((MasterSquirrelBot)entArray.get(i)).getImplName(), new LinkedList<Integer>());
        }
        logger.log(Level.FINEST, "Object der Klasse EntitySet erstellt");
    }
    
    public void add(Entity entity){
        entArray.add(entity);
//        switch(entityTyp){
//            case"BadPlant": entArray.add(new BadPlant(numbOf++,x,y));break;
//            case"GoodBeast":entArray.add(new GoodBeast(numbOf++,x,y));break;
//            case"GoodPlant":entArray.add(new GoodPlant(numbOf++,x,y));break;
//            case"BadBeast":entArray.add(new BadBeast(numbOf++,x,y)); break;
//            case"GuidedMasterSquirrel":entArray.add(new GuidedMasterSquirrel(numbOf++,x,y));break;
//            case"Wall":entArray.add(new Wall(numbOf++,x,y));break;
//            case"MasterSquirrelBot":entArray.add(new MasterSquirrelBot(numbOf++,x,y));break;
//            case"MiniSquirrel":entArray.add(new MiniSquirrel(numbOf++, 200, x, y, 1)); //Zum testen
//            default:break;
//            
//        }
        numbOf++;
    }
    
    public void add(String entityTyp, int x, int y){
                switch(entityTyp){
            case"BadPlant": entArray.add(new BadPlant(numbOf++,x,y));break;
            case"GoodBeast":entArray.add(new GoodBeast(numbOf++,x,y));break;
            case"GoodPlant":entArray.add(new GoodPlant(numbOf++,x,y));break;
            case"BadBeast":entArray.add(new BadBeast(numbOf++,x,y)); break;
            case"GuidedMasterSquirrel":entArray.add(new GuidedMasterSquirrel(numbOf++,x,y));break;
            case"Wall":entArray.add(new Wall(numbOf++,x,y));break;
            case"MiniSquirrel":entArray.add(new MiniSquirrel(numbOf++, 200, x, y, 0)); //Zum testen
            default:break;
            
        }
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
    
    public Vector getEntityVector(){
        return entArray;
    }
    public Entity[] getEntityArray(){
        Entity[] entArr = new Entity[entArray.size()];
        for(int i =0; i< entArray.size();i++){
            entArr[i] = entArray.get(i);
        }
        return entArr;
    }
    @Override
    public String toString(){
        String output="";
        for(int i=0;entArray.size()<i;i++){
            output += entArray.get(i).getName() + "," + entArray.get(i).toString() + '\n';
        }
        return(output);
    }

    public void nextStepAll(EntityContext entContext, XY input, int stepsPerRound) throws InterruptedException{
        if(stepsPerRound > this.stepsThisRound){
            for(int i=0;entArray.size()>i;i++){
                if(entArray.get(i).getTimeout()<=0){
                    if(isInstance(entArray.get(i),GuidedMasterSquirrel.class)){
                        if( input != null){
                            ((GuidedMasterSquirrel)entArray.get(i)).nextStep(entContext,input);
                        }
                    }
                    else
                        entArray.get(i).nextStep(entContext,null);
                }
                else if(entArray.get(i).getTimeout()>0)
                    entArray.get(i).setTimeout(entArray.get(i).getTimeout()-1);
            }
        }
        //Pause, wenn die gewünschte Anzahl an Schritten in dieser Runde abgelaufen sind
        else{
            
//            Vector<Entity> entArr = getEntityVector();
//            LinkedList<Integer> ls = new LinkedList();
//            LinkedList<Integer> lsBot = new LinkedList();
//            for(int i = 0; i<entArr.size();i++){
//                if(isInstance(entArr.get(i),GuidedMasterSquirrel.class)){
//                    if(highscore.containsKey(entArr.get(i).getName()))
//                        ls = highscore.get(((MasterSquirrel)entArr.get(i)).getName());
//                    else
//                        ls = new LinkedList<Integer>();
//                    
//                    ls.add(entArr.get(i).getEnergy());
//                    highscore.put(((MasterSquirrel)entArr.get(i)).getName(), ls);
//                    ((MasterSquirrel)entArr.get(i)).updateEnergy(1000-(entArr.get(i)).getEnergy());
//                }
//                if(isInstance(entArr.get(i),MasterSquirrelBot.class)){
//                    if(highscore.containsKey(((MasterSquirrelBot)entArr.get(i)).getImplName()))
//                        lsBot = highscore.get(((MasterSquirrelBot)entArr.get(i)).getImplName());
//                    else
//                        lsBot = new LinkedList<Integer>();
//                    lsBot.add(entArr.get(i).getEnergy());
//                    highscore.put(((MasterSquirrelBot)entArr.get(i)).getImplName(),lsBot);
//                    ((MasterSquirrel)entArr.get(i)).updateEnergy(1000-(entArr.get(i)).getEnergy());
//                }
//                else if(isInstance(entArr.get(i), MiniSquirrel.class)){
//                    delete((entArr.get(i)).getId());
//                }
//            }
//            Object[] names = highscore.keySet().toArray();
//            for(int i = 0; i<names.length;i++){
//                    ls = highscore.get(names[i]);
//                Object[] lsArr = ls.toArray();
//                
//                System.out.print(names[i]+": ");
//                Arrays.sort(lsArr);
//                System.out.println(Arrays.toString(lsArr));
//                logger.log(Level.INFO, names[i].toString()+": "+ Arrays.toString(lsArr));
//            }
//            stepsThisRound = 0;
        }
//        this.stepsThisRound++;
//        this.remainingSteps = stepsPerRound - stepsThisRound;
    }
    
    public boolean isInstance(Object o, Class c){
    return c.isInstance(o);
    }
    
    public int getRemainingSteps(){
        return remainingSteps;
    }
    
}
