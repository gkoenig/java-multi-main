package test.geko.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

public class Dispatcher {

	/**
	* A description of a program based on its class and a 
	* human-readable description.
	*/
	Map<String, ToolDescription> tools;
	  
	public Dispatcher() {
		tools = new TreeMap<String, ToolDescription>();
	}

	static private class ToolDescription {

		private Method main;
	    private String description;
	    
	    static final Class<?>[] paramTypes = new Class<?>[] {String[].class};
		
	    /**
	     * Create a description of possible tools to run
	     * @param mainClass the class with the main for the tool
	     * @param description a string to display to the user in help messages
	     * @throws SecurityException if we can't use reflection
	     * @throws NoSuchMethodException if the class doesn't have a main method
	     */
		public ToolDescription(Class<?> mainClass,String description) 
				throws SecurityException, NoSuchMethodException{
			this.main = mainClass.getMethod("main", paramTypes);
		    this.description = description;
		}

		/**
	     * Invoke the requested tool with the given arguments
	     * @param args the arguments for the tool to run
	     * @throws Throwable The exception thrown by the invoked method
	     */
	    public void invoke(String[] args)
	      throws Throwable {
	      try {
	        main.invoke(null, new Object[]{args});
	      } catch (InvocationTargetException except) {
	        throw except.getCause();
	      }
	    }
		
	    public String getDescription() {
	      return description;
	    }
		
	}

	private static void printUsage(Map<String, ToolDescription> tools) {
	    System.out.println("Valid tools are:");
	    for(Map.Entry<String, ToolDescription> item : tools.entrySet()) {
	      System.out.println("  " + item.getKey() + ": " +
	                         item.getValue().getDescription());         
	    } 
	}
	
	/**
	   * Adds a class to the repository
	   * @param name The name of the string you want the class instance to be called with
	   * @param mainClass The class that you want to add to the repository
	   * @param description The description of the class
	   * @throws NoSuchMethodException 
	   * @throws SecurityException 
	   */
	  public void addClass (String name, Class mainClass, String description) throws Throwable {
		  tools.put(name , new ToolDescription(mainClass, description));
	  }
	   
	/**
	   * This is the entry point for running the possible tools.
	   * It looks at the first command line argument and tries to find a tool with that name.
	   * If it is found, it calls the main method in that class with the rest 
	   * of the command line arguments.
	   * @param args The argument from the user. args[0] is the tool to run.
	   * @throws NoSuchMethodException 
	   * @throws SecurityException 
	   * @throws IllegalAccessException 
	   * @throws IllegalArgumentException 
	   * @throws Throwable Anything thrown by the example program's main
	   */
	  public void dispatch(String[] args) throws Throwable 
	  {
	    
	    if (args.length == 0) {
	      System.out.println("A tool must be given as the first argument.");
	      printUsage(tools);
	      System.exit(-1);
	    }
		
	    
	    ToolDescription td = tools.get(args[0]);
	    if (td == null) {
	      System.out.println("Unknown tool '" + args[0] + "' provided.");
	      printUsage(tools);
	      System.exit(-1);
	    }
		
	    // Remove the leading argument and call the corresponding main function
	    String[] new_args = new String[args.length - 1];
	    for(int i=1; i < args.length; ++i) {
	      new_args[i-1] = args[i];
	    }
	    td.invoke(new_args);
	  }
}
