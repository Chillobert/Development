package de.hsa.games.fatsquirrel.logger;

import java.util.logging.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

// TODO: Auto-generated Javadoc
//TODO: Irgendwie eine Handler-Config Datei erstellen damit das Outputfile logfile.txt ein lesbares Format hat.
//LOGGER LEVELS UND DEREN VERWENDUNG:
//SEVERE = Fehler
//WARNING = Warnungen
//INFO = Informationen, Start/Ende    
//CONFIG = Konfigurationen usw
//FINE = Informationen zum MAsterSquirrel
//FINER = WEitere Informationen zu Entitys
//FINEST = Mooore details

/**
 * The Class GameLogger.
 */
public class GameLogger {
	 
 	/** The Constant logManager. */
 	private static final LogManager logManager = LogManager.getLogManager();
	 
 	/** The Constant logger. */
 	private static final Logger logger = Logger.getLogger("");
	 static{
		 try{
			 logManager.readConfiguration(new FileInputStream("./logging.properties"));
		 } catch (IOException exception) {
			 logger.log(Level.SEVERE, "Error in loading configuration",exception);
		 }


	 }

/**
 * Instantiates a new game logger.
 */
public GameLogger(){
	logger.log(Level.FINEST, "Objekt der Klasse GameLogger wurde erstellt");
}



/**
 * Log.
 *
 * @param level the level
 * @param name the name
 */
public void log(Level level, String name){
	logger.log(level, name);
}

/**
 * Proxy log.
 *
 * @param level the level
 * @param o the o
 */
public void proxyLog(Level level, Object o){
	logger.log(level, (String) o);
}
}