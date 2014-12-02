/**
 * 
 */
package test.geko;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class entrypoint {

	/**
	 * 
	 */
	public entrypoint() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param argsO
	 */
	public static void main(String[] args) throws Exception{
		System.out.println("ENTER entrypoint::main()\n");
		
		final Map<String, Class<?>> ENTRY_POINTS = new HashMap<String, Class<?>>();
		ENTRY_POINTS.put("validate", validate.class);
		ENTRY_POINTS.put("merge", merge.class);
		ENTRY_POINTS.put("compare", compare.class);
		    
		if(args.length < 1){
            throw new IllegalArgumentException("at least one argument required");
        }
        final Class<?> entryPoint = ENTRY_POINTS.get(args[0]);
        if(entryPoint==null){
        	throw new IllegalArgumentException("unknown class provided: " + args[0]);
        }
        final String[] argsCopy = args.length > 1 ? Arrays.copyOfRange(args, 1, args.length) : new String[0];
        entryPoint.getMethod("main", String[].class).invoke(null,(Object) argsCopy);
		System.out.println("EXIT entrypoint::main()\n");
		
	}

}
