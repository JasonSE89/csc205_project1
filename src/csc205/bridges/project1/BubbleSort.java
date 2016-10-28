package csc205.bridges.project1;

import java.util.*;
import javax.swing.JOptionPane;
import bridges.connect.Bridges;

public class BubbleSort {

	/** enumerated type listing all the valid data structure choices */
	private enum DataStructureChoice { JAVA_ARRAY_LIST, JAVA_LINKED_LIST, CSC205_LINKED_LIST };
	
	static Bridges<String, Student> bridges;

	public static void main(String[] args) {

		System.out.println ( "Hello world from BRIDGES bubblesort\n");

		/** create the "bridge" to the visualization software */
		bridges = new Bridges<String, Student>(10, "1105532094412", "jepst1");

		/** the user specifies which data structure to use in the sorting algorithm */
		DataStructureChoice dsChoice = getDataStructureChoice();
		
		/** create the List that will be sorted 
		 *  Note the use of the List interface here */
		List<Student> theListOfData = createEmptyList(dsChoice);
		
		/** the user specifies whether to use set/get or add/remove methods in the sorting */
		boolean useSetGet = setGet_v_AddRemove();	
		
		/** the user specifies whether to use a small test data set, or a large one */
		boolean useSmallTestData = chooseTestDataSize();	
		
		if ( useSmallTestData ) {
			theListOfData.add (  new Student ( "edith", "EDC" ) );
			theListOfData.add (  new Student ( "dan", "DNC" ) );
			theListOfData.add (  new Student ( "chad", "CHM" ) );
			theListOfData.add (  new Student ( "bob", "BIO" ) );
			theListOfData.add (  new Student ( "alice", "ART" ) );
			
			/** we can only visualize the List if we are using BRIDGES */
			if ( theListOfData instanceof CSC205_Project_1_Linked_List<?> )
				bridges.visualize(); 
		} else 
			initializeLargeRandomList ( theListOfData );	

		long startTime = System.currentTimeMillis();

		/** Perform a Bubble Sort.
		 *  we repeatedly sweep through the list, until no items have been
		 *  swapped, at which time the list is sorted 	                   	 */
		boolean restart = true;
		while ( restart ) 
		{
			restart = false;

			/** sweep through the List from left-to-right, comparing adjacent items */
			for ( int j=0; j < theListOfData.size() - 1; j++ ) {

				/** swap items in positions (j) and (j+1) if out-of-order     */
				if (  theListOfData.get(j).compareTo( theListOfData.get(j+1) ) > 0  ) {

					if ( useSetGet ) {
						/** use Set and Get methods */
						Student temp = theListOfData.get(j);
						theListOfData.set ( j, theListOfData.get(j+1) );
						theListOfData.set ( j+1, temp ); 
					} else {  
						/** use Add and Remove methods, much more time-consuming */
						Student temp = theListOfData.remove(j);   
						theListOfData.add( j+1, temp );	
					}

					visualizeResultOfSwap(theListOfData, useSmallTestData);

					restart = true;  // if swap occurred, must repeat the loop
				}
			}
		}

		long endTime = System.currentTimeMillis();

		System.out.println( "\n\nThe total time to sort n = " + theListOfData.size() 
				+ " items, using the " + dsChoice +
				" data structure was "
				+ (endTime - startTime) + " milliseconds");   
		
		System.out.println ("\n\nThe sorting algorithm was successful:\t\t" + isSorted(theListOfData) + "\n");

		/** we can only visualize the List if we are using BRIDGES */
		if ( useSmallTestData &&
		     theListOfData instanceof CSC205_Project_1_Linked_List<?> )
			bridges.visualize(); 
		
		System.out.println ("\nGood-bye world");
	}


	/**
	 * Visualize the result of the swap
	 *  
	 * @param theListOfData the List being sorted
	 * @param useSmallTestData true if the user is sorting the small test data set
	 */
	private static void visualizeResultOfSwap(List<Student> theListOfData,
			boolean useSmallTestData) {
		
		if ( useSmallTestData ) {

			/** we can only visualize the List if we are using BRIDGES */
			if ( theListOfData instanceof CSC205_Project_1_Linked_List<?> ) {

				/** color the "finger" location in the List green */
				((CSC205_Project_1_Linked_List<Student>) theListOfData)
				.getFinger().getVisualizer().setColor( "GREEN" );

				bridges.visualize(); 

				/** restore the color of the "finger" location in the List to blue */
				(( CSC205_Project_1_Linked_List<Student>) theListOfData)
				.getFinger().getVisualizer().setColor( "BLUE" );
			}
		}
	}

	
	/** create the List to contain the data that will be sorted */
	private static List<Student> createEmptyList(DataStructureChoice dsChoice) {

		List<Student> foo = null;          /** declared using an Interface declaration */
		
		switch ( dsChoice ) {
		case JAVA_ARRAY_LIST:
			foo = new ArrayList<Student>();
			break;
		case JAVA_LINKED_LIST:
			foo = new LinkedList<Student>();
			break;
		case CSC205_LINKED_LIST:
			foo = new CSC205_Project_1_Linked_List<Student>();

			CSC205_Project_1_Linked_List<Student> fooAlias = 
					(CSC205_Project_1_Linked_List<Student>) foo;

			bridges.setDataStructure( fooAlias.getDummyHeader() ); 

			/** highlight the dummy header node in red */
			fooAlias.getDummyHeader().getVisualizer().setColor( "RED" );
			break;
		default:
			System.out.println ( "Illegal choice of data structure");
			System.exit(1);   // abort the program
		}
		return foo;
	}


	/** Initialize the List to contain data in reverse sorted order */
	private static void initializeLargeRandomList(List<Student> foo) 
	{
		/** query the user for the number of items to sort */
		String amountOfData = JOptionPane.showInputDialog("Enter the number of data items");
		int n = Integer.parseInt ( amountOfData );

		/** create a list of Strings that is very un-sorted */
		for ( int i=n-1; i >= 0; i-- ) 
			foo.add( new Student("alice " + (n+i), "MTH") );     
	}


	/** @return true if the data to be sorted is the small, hard-wired data set */
	private static boolean chooseTestDataSize() {
		String[] methodChoices = { "Use Small Data Set", "Use Large Random Data Set" };

		int choice;
		choice = JOptionPane.showOptionDialog(
				null, // No parent
				"Bubble Sort", // Prompt message
				"List data structures", // Window title
				JOptionPane.YES_NO_CANCEL_OPTION, // Option type
				JOptionPane.QUESTION_MESSAGE, // Message type
				null, // Icon
				methodChoices, // List of commands
				methodChoices[methodChoices.length - 1]); // Default choice
		switch (choice) {
		case 0:
			return true;
		default:
			return false;
		}
	}

	/**
	 * The user will specify what type of List oeprations are used
	 * to sort the List.
	 * 
	 * @return true if the user selected the Set and Get methods 
	 */
	private static boolean setGet_v_AddRemove() {

		String[] methodChoices = { "Use Set and Get", "Use Add and Remove" };

		int choice;
		choice = JOptionPane.showOptionDialog(
				null, // No parent
				"Bubble Sort", // Prompt message
				"List data structures", // Window title
				JOptionPane.YES_NO_CANCEL_OPTION, // Option type
				JOptionPane.QUESTION_MESSAGE, // Message type
				null, // Icon
				methodChoices, // List of commands
				methodChoices[methodChoices.length - 1]); // Default choice
		switch (choice) {
		case 0:
			return true;
		default:
			return false;
		}
	}

	/**
	 * @return the type of List data structure to use when sorting the list
	 */
	private static DataStructureChoice getDataStructureChoice() {

		int choice;
		String[] dataStructureChoices = 
			{ "Use a Java ArrayList", "Use a Java LinkedList", "Use a CSC 205 LinkedList" };

		choice = JOptionPane.showOptionDialog(
				null, // No parent
				"Bubble Sort", // Prompt message
				"List data structures", // Window title
				JOptionPane.YES_NO_CANCEL_OPTION, // Option type
				JOptionPane.QUESTION_MESSAGE, // Message type
				null, // Icon
				dataStructureChoices, // List of commands
				dataStructureChoices[dataStructureChoices.length - 1]); // Default choice
		switch (choice) {
		case 0:
			return DataStructureChoice.JAVA_ARRAY_LIST;
		case 1:
			return DataStructureChoice.JAVA_LINKED_LIST;
		case 2:
			return DataStructureChoice.CSC205_LINKED_LIST;
		default:
			System.out.println ( "Illegal choice of data structure");
			System.exit(1);   // abort the program
		}
		return null;
	}

	/**
	 * @param theListofData
	 * @return true if-and-only-if the given List is in sorted order
	 */
	private static boolean isSorted ( List<Student>   theListofData ) {

		/** check every pair of adjacent items to see that they are in sorted order */
		for ( int i = 0; i < theListofData.size() - 1; i++ ) {
			Student leftItem = theListofData.get(i);
			Student rightItem = theListofData.get(i+1);

			if ( leftItem.compareTo( rightItem ) > 0 )
				return false;   // these two items are out-of-order
		}
		return true;
	}
}





