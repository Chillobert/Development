package prog2_a3.fatsquirrel.core;

import java.util.logging.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//TODO: Irgendwie eine Handler-Config Datei erstellen damit das Outputfile logfile.txt ein lesbares Format hat.
//LOGGER LEVELS UND DEREN VERWENDUNG:
//SEVERE = Fehler
//WARNING = Warnungen
//INFO = Informationen, Start/Ende    
//CONFIG = Konfigurationen usw
//FINE = Informationen zum MAsterSquirrel
//FINER = WEitere Informationen zu Entitys
//FINEST = Mooore details

public class GameLogger {
	 private static final LogManager logManager = LogManager.getLogManager();
	 private static final Logger logger = Logger.getLogger("");
	 static{
		 try{
			 logManager.readConfiguration(new FileInputStream("./logging.properties"));
		 } catch (IOException exception) {
			 logger.log(Level.SEVERE, "Error in loading configuration",exception);
		 }


	 }

public GameLogger(){
	logger.log(Level.FINEST, "Objekt der Klasse GameLogger wurde erstellt");
}



public void log(Level level, String name){
	logger.log(level, name);
}

public void proxyLog(Level level, Object o){
	logger.log(level, (String) o);
}
}