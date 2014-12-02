/**
 * 
 */
package test.geko;

import java.util.List;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;


public class validate {

	private static CommandLine parseArgs(String[] args) throws ParseException {
		CommandLineParser parser = new BasicParser();
		return parser.parse(new Options(), args);
	}

	private static void printUsage() {
		HelpFormatter helpFormatter = new HelpFormatter();
		helpFormatter.setWidth(80);
		String usageStr = "java -jar name-of-jar.jar validate <param-1> <param-2>";
		
		helpFormatter.printHelp(usageStr, new Options());
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("ENTER validate::main()\n");
		
		CommandLine cmd = null;
		try {
			// parse the command line arguments
			cmd = parseArgs(args);
		} catch (ParseException e) {
			System.out.println("Error when parsing command-line arguments");
			printUsage();
			System.exit(-1);
		}

		List<?> argList = cmd.getArgList();
		if (argList.size() != 2) {
			printUsage();
			System.exit(-1);
		}
				
		System.out.println("EXIT validate::main()\n");
		System.exit(0);
	}

}
