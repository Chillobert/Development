package prog2_a3.fatsquirrel.core;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BoardConfig {
	Properties prop = new Properties();
	InputStream input = null;
	private XY size;
	private int amountGoodBeasts;
	private int amountBadBeasts;
	private int amountGoodPlants;
	private int amountBadPlants;
	private int amountWalls;
	private String masterBotImplsLocation;
	private String[] masterBotImpls;
	private int stepsPerRound;
	
	public BoardConfig(){

		this.getProps();
		
	}

	public void getProps(){
		try {
			//Stream erstellen
			input = new FileInputStream("config.properties");
			// Auslesen
			prop.load(input);
			// Verarbeiten
			this.setSize(Integer.parseInt(prop.getProperty("size_length")), Integer.parseInt(prop.getProperty("size_width")));
			this.setAmountGoodBeasts(Integer.parseInt(prop.getProperty("amountGoodBeasts")));
			this.setAmountBadBeasts(Integer.parseInt(prop.getProperty("amountBadBeasts")));
			this.setAmountGoodPlants(Integer.parseInt(prop.getProperty("amountGoodPlants")));
			this.setAmountBadPlants(Integer.parseInt(prop.getProperty("amountBadPlants")));
			this.setAmountWalls();
			this.setStepsPerRound(Integer.parseInt(prop.getProperty("stepsPerRound")));
			this.setMasterBotImplsLocation(prop.getProperty("masterBotImplsLocation"));
			this.setMasterBotImpls(prop.getProperty("masterBotImpls"));
			

		} catch (IOException ex) {
			BoardConfig_old alternative = new BoardConfig_old();
			//hier setter verwenden
			this.setSize(Integer.parseInt(prop.getProperty("size_length")), Integer.parseInt(prop.getProperty("size_width")));
			this.setAmountGoodBeasts(Integer.parseInt(prop.getProperty("amountGoodBeasts")));
			this.setAmountBadBeasts(Integer.parseInt(prop.getProperty("amountBadBeasts")));
			this.setAmountGoodPlants(Integer.parseInt(prop.getProperty("amountGoodPlants")));
			this.setAmountBadPlants(Integer.parseInt(prop.getProperty("amountBadPlants")));
			this.setAmountWalls();
			this.setStepsPerRound(Integer.parseInt(prop.getProperty("stepsPerRound")));
			this.setMasterBotImplsLocation(prop.getProperty("masterBotImplsLocation"));
			this.setMasterBotImpls(prop.getProperty("masterBotImpls"));
			System.out.println("FUUUUUUU");
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
		
  }
	
	public void printProperties(){
		System.out.println("size:" + this.size.toString());
		System.out.println("goodbeasts:" + this.amountGoodBeasts);
		System.out.println("badbeasts:" + this.amountBadBeasts);
		System.out.println("goodplants" + this.amountGoodPlants);
		System.out.println("badplants" + this.amountBadPlants);
		System.out.println("walls:" + this.amountWalls);
		System.out.println("stepsPerRound" + this.stepsPerRound);
		System.out.println("masterBotLocation" + this.masterBotImplsLocation);
		System.out.println("masterBots:" + this.masterBotImpls.toString());
		
	}
	
	private void setSize(int length, int width){
		this.size = new XY(new int[]{length,width});
	}
	
	private void setAmountGoodBeasts(int amount){
		this.amountGoodBeasts = amount;
	}
	
	private void setAmountBadBeasts(int amount){
		this.amountBadBeasts = amount;
	}	
	
	private void setAmountGoodPlants(int amount){
		this.amountGoodPlants = amount;
	}
	
	private void setAmountBadPlants(int amount){
		this.amountBadPlants = amount;
	}	
	
	private void setAmountWalls(){
		this.amountWalls = size.getX()*2 + size.getY()*2;
	}		
	
	private void setStepsPerRound(int amount){
		this.stepsPerRound = amount;
	}	
	
	private void setMasterBotImplsLocation(String path){
		this.masterBotImplsLocation = path;
	}
	
	private void setMasterBotImpls(String masterBotImpls){
		String[] splitter = masterBotImpls.split(",");
        this.masterBotImpls = new String[splitter.length];
		for (int i = 0; i < splitter.length; i++) {
			this.masterBotImpls[i] = splitter[i];
			
		}
		
	}
public int getLength(){
     return size.getX();
}
public int getWidth(){
      return size.getY();
}
    public XY getSize(){
        return size;
    }
public int getAmountGoodBeasts(){
        return amountGoodBeasts;
}
public int getAmountBadBeasts(){
        return amountBadBeasts;
}
public int getAmountGoodPlants(){
        return amountGoodPlants;
}
public int getAmountBadPlants(){
        return amountBadPlants;
}
public int getAmountWalls(){
        return size.getX()*2 + size.getY()*2;
}
    public String[] getMasterBotImpls(){
        String[] output = new String[masterBotImpls.length];

        for(int i = 0; i < masterBotImpls.length;i++){
            output[i] = this.masterBotImplsLocation+masterBotImpls[i];
        }
        return output;
    }
    public int getStepsPerRounds(){
        return stepsPerRound;
    }
	
	
}
