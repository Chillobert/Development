package de.hsa.games.fatsquirrel.proxy;
import java.lang.reflect.Proxy;

import de.hsa.games.fatsquirrel.botapi.ControllerContext;


public class ControllerContextProxy {

	   ControllerContext ctrlContProx;
	    InvocationHandlerImpl invocHandler;

	    public ControllerContextProxy(ControllerContext ContrlCont) {

		invocHandler = new InvocationHandlerImpl(ContrlCont);
                // Neuen dynamischen Proxy erstellen mit den Parametern Class Loader, zu implementierendes Interface und invocationHandler
		ctrlContProx = (ControllerContext) Proxy.newProxyInstance(ControllerContext.class.getClassLoader(),new Class[] { ControllerContext.class }, invocHandler); 
	    }
	    
	  // nur den Proxy returnen, den Rest erledigt getProxy() v. InvoHandler
	    public ControllerContext getProxy() { 
		return ctrlContProx;
	    }

	}
