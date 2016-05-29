package prog2_a3.fatsquirrel.proxy;
import prog2_a3.fatsquirrel.botapi.ControllerContext;
import java.lang.reflect.Proxy;


public class ContextControllerProxy {

	   ControllerContext ctrlContProx;
	    InvocationHandlerImpl handler;

	    public ContextControllerProxy(ControllerContext ContrlCont) {

		handler = new InvocationHandlerImpl(ContrlCont);
		ctrlContProx = (ControllerContext) Proxy.newProxyInstance(
                        ControllerContext.class.getClassLoader(),
			new Class[] { ControllerContext.class }, handler); // Proxy
									   // erstellen
	    }
	    
	  // nur den Proxy returnen, den Rest erledigt invoke() v. InvoHandler
	    public ControllerContext invoke() { 
		return ctrlContProx;
	    }

	}
