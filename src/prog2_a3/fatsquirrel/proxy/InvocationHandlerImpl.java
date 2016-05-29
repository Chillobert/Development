package prog2_a3.fatsquirrel.proxy;

import prog2_a3.fatsquirrel.botapi.ControllerContext;
import prog2_a3.fatsquirrel.core.GameLogger;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InvocationHandlerImpl implements InvocationHandler  {
    private ControllerContext context; // Proxy für ControllerContext
    //private static Logger log = Logger.getLogger(InvocationHandlerImpl.class.getName());
    private static final GameLogger logger = new GameLogger();

    public InvocationHandlerImpl(ControllerContext context)  { this.context = context; }  // Konstruktor

    // Das einzige was man hier aendern darf sind die sysouts/loggings der Rest ist allgemein gültig
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable  {  // Methode um allgemein Methoden auszuführen

        logger.log(Level.INFO, "* calling method " + method + " with params ");

        // übergebene Argumente ausgeben
        for (int i = 0; i < args.length; i++)
            logger.log(Level.INFO, " " + args[i]);


        Object result = null; // das zu returnende Objekt, welches den Wert der zu aufrufenden Methode bekommt
        try  {
            result = method.invoke(context, args); // die übergebende Methode aufrufen und dessen Rückgabe result zuweisen
            // Die Fehler die auftretten können abfangen wie z.B. das die Methode void ist oder private
        } catch(IllegalAccessException ex)  {
        } catch(InvocationTargetException ex)  {
            logger.log(Level.SEVERE, "Fehler: InvocationHandlerImpl.invoke(); getTargetException");
            throw ex.getTargetException();
        }
        // Ergebnis d. Methoden ausgeben, falls alles gepasst hat kommt kein null raus
        logger.log(Level.INFO, (String) result);

        return result;
    }
}

