
package prog2_a3.fatsquirrel.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.*;


public class LoggingConfig {
    Handler handler;

    public LoggingConfig() throws IOException {
        this.handler = new FileHandler("log.txt");
        
    }
}
