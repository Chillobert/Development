package prog2_a3.fatsquirrel.proxy;
import prog2_a3.fatsquirrel.botapi.ControllerContext;
import java.lang.reflect.Proxy;


public class ControllerContextProxy {

	   ControllerContext ctrlContProx;
	    InvocationHandlerImpl invocHandler;

	    public ControllerContextProxy(ControllerContext ContrlCont) {

		invocHandler = new InvocationHandlerImpl(ContrlCont);
                // Neuen dynamischen Proxy erstellen mit den Parametern Class Loader, zu implementierendes Interface und invocationHandler
		ctrlContProx = (ControllerContext) Proxy.newProxyInstance(ControllerContext.class.getClassLoader(),new Class[] { ControllerContext.class }, invocHandler); 
	    }
	    
	  // nur den Proxy returnen, den Rest erledigt invoke() v. InvoHandler
	    public ControllerContext invoke() { 
		return ctrlContProx;
	    }

	}
