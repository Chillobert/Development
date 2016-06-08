package de.hsa.games.fatsquirrel.proxy;
import java.lang.reflect.Proxy;

import de.hsa.games.fatsquirrel.botapi.ControllerContext;


// TODO: Auto-generated Javadoc
/**
 * The Class ControllerContextProxy.
 */
public class ControllerContextProxy {

	   /** The ctrl cont prox. */
   	ControllerContext ctrlContProx;
	    
    	/** The invoc handler. */
    	InvocationHandlerImpl invocHandler;

	    /**
    	 * Instantiates a new controller context proxy.
    	 *
    	 * @param ContrlCont the contrl cont
    	 */
    	public ControllerContextProxy(ControllerContext ContrlCont) {

		invocHandler = new InvocationHandlerImpl(ContrlCont);
                // Neuen dynamischen Proxy erstellen mit den Parametern Class Loader, zu implementierendes Interface und invocationHandler
		ctrlContProx = (ControllerContext) Proxy.newProxyInstance(ControllerContext.class.getClassLoader(),new Class[] { ControllerContext.class }, invocHandler); 
	    }
	    
	  /**
  	 * Gets the proxy.
  	 *
  	 * @return the proxy
  	 */
  	// nur den Proxy returnen, den Rest erledigt getProxy() v. InvoHandler
	    public ControllerContext getProxy() { 
		return ctrlContProx;
	    }

	}
