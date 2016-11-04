import java.util.Scanner;

/**
 * @author Denise Doyle 
 * Command Line interface for autocomplete
 * contains menu system driven by user input
 */


public class CommandLine {

	private static BruteAutocomplete brute;
	private static String prefix;
	

	public static void main(String[] args) throws Exception {
		String filePath = "lib/wiktionary.txt";
		brute = new BruteAutocomplete(filePath);
		menu();
	}

	public static void menu() {
		Scanner in = new Scanner(System.in);
		System.out.println("-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-");
		System.out.println("Welcome to the command line for Autocomplete");
		System.out.println("--------------------------------------------");
		System.out.println("Please enter a prefix or term: ");
		String prefix = in.nextLine();
		setPrefix(prefix);
		System.out.println("\nNow please choose from the following options:");
		System.out.println("-----------------------------------------------");
		System.out.println("1) Best match for '" + prefix + "'");
		System.out.println("2) Weight of '" + prefix + "'");
		System.out.println("3) Number of matches for '" + prefix + "'");
		System.out.println("0) Exit");
		System.out.println("-----------------------------------------------");

		int choice = in.nextInt();

		switch (choice) {
		case 1:
			try {
				bestMatch();
			} catch (Exception e) {
				System.out.println("Error returning best match\n" +e);
				menu();
			}
			break;
			
		case 2:
			try {
				weightOf();
			} catch (Exception e) {
				System.out.println("Error returning weight\n" +e);
				menu();
			}

			break;
			
		case 3:
			try{
				numberOfMatches();
			}catch(Exception e){
				System.out.println("Error returning number of matches\n" +e );
				menu();
			}

			break;
			
		case 0:
			System.out.println("Exiting... Goodbye!");
			System.exit(0);
			
		default:
			System.out.println("Please enter a valid choice");
			menu();
			break;
		}
	}
	
	private static void setPrefix(String prefix2) {
		prefix = prefix2;
		
	}

	public static void bestMatch() {
		System.out.println("The best match for '" + prefix + "' is: " + brute.bestMatch(prefix) + "\n");
		menu();
	}

	public static void weightOf() {
		System.out.println("The weight of '" + prefix + "' is: " + brute.weightOf(prefix) + "\n");
		menu();
	}

	public static void numberOfMatches() {
		Scanner in = new Scanner(System.in);
		System.out.println("How many matches would you like to display?");
		int k = in.nextInt();
		System.out.println(" '" + prefix + "': " + brute.matches(prefix, k) + "\n");
		menu();
	}

}