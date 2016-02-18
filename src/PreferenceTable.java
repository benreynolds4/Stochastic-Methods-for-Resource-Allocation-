import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PreferenceTable {
	
	private Random random = new Random();
	
	private	Hashtable<String, StudentEntry> studentLookup =	new	Hashtable();

	public PreferenceTable() {
		
	}
	
	public PreferenceTable(String fileName) throws IOException {
		// Vector<Vector> allData = loadContentFromFile(fileName);
		// System.out.println(allData);
	}
	
	
	/* Gets vector from LoadContentsFromFile and Creates StudentEntry for each Student */
	public void setupStudents() throws IOException {
		Vector<Vector> students = loadContentFromFile("tabfile.txt");
		for (Vector person : students)		// For each vector in Vector of Vectors
		{
			StudentEntry student = new StudentEntry();			
			String currentName = "";
			boolean preAssigned = false;
			int count = 0;						// use count to be aware of what piece of data you are dealing with.
		    for(Object elem :person) {			// For each element in Student Vector
		    	if (count == 0) {
		    		student.setName(elem.toString());
		    		currentName = elem.toString();
		    	} 
		    	else if(count == 1) {
		    		if(elem.toString().equalsIgnoreCase("Yes")) {
		    			preAssigned = true;
		    		}
		    	}
		    	else {
		    		if(preAssigned) {
		    			student.preassignProject(elem.toString());
		    			student.addProject(elem.toString());
		    			preAssigned = false;
		    		} else {
		    			student.addOriginalProject(elem.toString());
		    		}
		    	}
		    	count++;
		    }
		    studentLookup.put(currentName, student);			// add student to hashtable with name as key
		}
	}
	
	public StudentEntry getRandomStudent() {
		List<String> names = new ArrayList<String>();
		for (String key : studentLookup.keySet()) {
			   names.add(key);
			}
		int index = random.nextInt(names.size());
		String name = names.get(index);

		return studentLookup.get(name);
	}
	
	public String getRandomPreference() {
		StudentEntry randomStudent = getRandomStudent();
		String randomPreference = randomStudent.getRandomPreference();
		return randomPreference;
	}
	
	/* Returns HashTable of Students */
	public Hashtable<String, StudentEntry> getAllStudentEntries() {
		return studentLookup;
	}
	
	public String fillPreferencesOfAll(int maxPrefs) {
		// Iterate over all student objects and ensure they all have ten different preferences. 
		for (String key : studentLookup.keySet()) {
			   StudentEntry student = studentLookup.get(key);
			   int numberPreferences = student.getNumberOfStatedPreferences();
			   if(numberPreferences != maxPrefs) {
				   // 
			   }
			}
		return "";
	}
	
	/* Returns a StudentENtry Object for specified student */
	public StudentEntry getEntryFor(String sname) {
		return studentLookup.get(sname);
	}
	
	/* This method loads students from TSV file into a Vector of Vectors */
	private static Vector<Vector> loadContentFromFile(String fileName) throws IOException {
		FileInputStream	stream	= new FileInputStream(fileName);							// Read File.
		BufferedReader input = new BufferedReader(new InputStreamReader(stream));
		Vector<Vector> allData = new Vector<Vector>();			// Create a Vector to store all vectors
		
		String line = "";
		int count = 0;
		while((line= input.readLine()) != null) {						// For each line, Create a vector of the cells and store it in allData vector
			if(count != 0) {
				String[] splitLine = line.split("\t");
				Vector<String> lineVector = new Vector<String>(Arrays.asList(splitLine));
				allData.add(lineVector);
			}
			count++;
		}	
		input.close();											// Close Streams
		stream.close();
		return allData;		
	}
}







