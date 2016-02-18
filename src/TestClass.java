import java.io.IOException;

public class TestClass {	
		public static void main(String[] args) throws IOException{
			PreferenceTable table = new PreferenceTable("tabfile.txt");
			table.setupStudents();
			System.out.println(table.getAllStudentEntries());
			
			// Test Loki Laufeyson
			System.out.println("\n");
			StudentEntry student = table.getEntryFor("Loki Laufeyson");
			System.out.println(student.getStudentName());
			System.out.println(student.getNumberOfStatedPreferences());
			System.out.println(student.getOrderedPreferences());
			System.out.println(student.hasPreAssignedProject());
			
			
			//Test Uriah Heap
			System.out.println("\n");
			student = table.getEntryFor("Uriah Heap");
			System.out.println(student.getStudentName());
			System.out.println(student.getNumberOfStatedPreferences());
			System.out.println(student.getOrderedPreferences());
			System.out.println(student.hasPreAssignedProject());
			
			// Test Bridget Jones
			System.out.println("\n");
			student = table.getEntryFor("Bridget Jones");
			System.out.println(student.getStudentName());
			System.out.println(student.getNumberOfStatedPreferences());
			System.out.println(student.getOrderedPreferences());
			System.out.println(student.hasPreAssignedProject());
			
			// Random Student Test
			System.out.println("\nRandom Student:");
			System.out.println(table.getRandomStudent().getStudentName());
			
			// Test Has Preference
			student = table.getEntryFor("Bridget Jones");
			System.out.println("\nBridget Jones Has Preference: Recommending Movies Using Curated IMDb Lists?");
			System.out.println(student.hasPreference("Recommending Movies Using Curated IMDb Lists"));
			
			// Test Add Preferences:
			
			table.fillPreferencesOfAll(10);
			
			System.out.println(table.getAllStudentEntries());
			
			
			
		}
	}

