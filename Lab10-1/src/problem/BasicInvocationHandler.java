package problem;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

public class BasicInvocationHandler implements InvocationHandler {

	HashMap<String, Object> map = new HashMap<String, Object>();
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
				
		if(method.getName().startsWith("get")){
			return map.get("name");
		} else if (method.getName().startsWith("set")) {
			if (map.containsKey("name")){
				map.remove("name");
			}
		}
		
		
		return null;
	}

}
