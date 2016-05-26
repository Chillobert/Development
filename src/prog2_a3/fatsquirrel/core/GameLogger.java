package prog2_a3.fatsquirrel.core;

import java.util.logging.*;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

//TODO: Irgendwie eine Handler-Config Datei erstellen damit das Outputfile logfile.txt ein lesbares Format hat.


public class GameLogger {
	private static FileHandler fileTxt;
	private int filesize = 1000000; //filesize
	private Logger logger;

//Handler handler = new FileHandler("log.txt");

	
public GameLogger(Level newLevel){
	logger = Logger.getLogger("logger"); //Logger erstellen
	logger.setLevel(newLevel); //Logger level setzen
	try {
		fileTxt = new FileHandler("logfile.txt", filesize, 1);
	} catch (SecurityException e1) {
		e1.printStackTrace();
	} catch (IOException e1) {
		e1.printStackTrace();
	}	
	
	logger.addHandler(fileTxt);

}

public void log(Level level, String message){
	this.logger.log(level, message);
};	
}
