package prog2_a3.fatsquirrel.proxy;

import prog2_a3.fatsquirrel.botapi.ControllerContext;
import prog2_a3.fatsquirrel.core.GameLogger;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;

public class InvocationHandlerImpl implements InvocationHandler  {
    private ControllerContext context; // Proxy f�r ControllerContext
    //private static Logger log = Logger.getLogger(InvocationHandlerImpl.class.getName());
    private static final GameLogger logger = new GameLogger();

    public InvocationHandlerImpl(ControllerContext context)
        {
            this.context = context; 
        }  // Konstruktor

    // Das einzige was man hier aendern darf sind die sysouts/loggings der Rest ist allgemein g�ltig
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable  {  // Methode um allgemein Methoden auszuf�hren
        //Die aufgerufene Methode loggen
        logger.log(Level.INFO, "* calling method " + method + " with params ");

        //und alle zugehörigen Argumente
        for (int i = 0; i < args.length; i++)
            logger.log(Level.INFO, " " + args[i]);

        Object returnValue = null; // das zu returnende Objekt, welches den Wert der aufgerufenen Methode bekommt. Bei Void bleibt es null
        try  {
            returnValue = method.invoke(context, args); // die �bergebende Methode aufrufen und dessen R�ckgabe result zuweisen
            // Die Fehler die auftretten k�nnen abfangen wie z.B. das die Methode void ist oder private
        } catch(IllegalAccessException ex)  {
        } catch(InvocationTargetException ex)  {
            logger.log(Level.SEVERE, "Fehler: InvocationHandlerImpl.invoke(); TargetException");
            throw ex.getTargetException();
        	}
        // returnValue zum nachvollziehen loggen
        if(returnValue != null)
            logger.log(Level.INFO, (String) returnValue);

        return returnValue;
    }
}

