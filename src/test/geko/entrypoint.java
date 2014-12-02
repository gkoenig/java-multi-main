package test.geko;

import test.geko.util.Dispatcher;

public class entrypoint {
	
	public static void main(String[] args) throws Exception {
		
		int exitCode = -1;
		Dispatcher disp = new Dispatcher();
		try {
			disp.addClass("validate", validate.class, "validates ... stuff");
			disp.addClass("mergetool", merge.class, "merges ... stuff");
			disp.addClass("compare", compare.class, "compares ... stuff");
		
			disp.dispatch(args);
			exitCode=0;
		}
		catch(Throwable e){
			e.printStackTrace();
		}
		
		System.exit(exitCode);
	}

}