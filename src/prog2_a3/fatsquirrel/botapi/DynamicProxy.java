package prog2_a3.fatsquirrel.botapi;

import java.lang.reflect.Method;

public class DynamicProxy implements java.lang.reflect.InvocationHandler{
	Object obj;
	
	public DynamicProxy(Object obj){
		this.obj = obj;
	}
			
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}
	
	}


