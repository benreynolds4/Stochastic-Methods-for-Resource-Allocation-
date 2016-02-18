import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudentEntry {
	private String name;
	private boolean hasPreAssignedProject;
	private String preAssignedProject;
	private List<String> projects = new ArrayList<String>();
	private int statedProjects;
	private Random random = new Random();
	
	public StudentEntry() {
		
	}
	
	public void setName(String sname) {
		name = sname;
	}
	
	public String getStudentName() {
		return name;
	}
	
	public List<String> getOrderedPreferences() {
		return projects;
	}
	
	public void preassignProject(String pname) {
		hasPreAssignedProject = true;
		statedProjects++;
		preAssignedProject = pname;
	}
	
	public boolean hasPreAssignedProject() {
		return hasPreAssignedProject;
	}
	
	public int getNumberOfStatedPreferences()	{
		return statedProjects;
	}
	
	public void addProject(String pname) {
		projects.add(pname);
	}
	
	public void addOriginalProject(String pname) {
		projects.add(pname);
		statedProjects++;
	}
	
	public String getRandomPreference() {
		String randomProject = projects.get(random.nextInt(projects.size()));
		return randomProject;
	}
	
	public boolean hasPreference(String preference) {
		boolean bool = false;
		for (String key : projects) {
			   if(key.equalsIgnoreCase(preference) ) {
				   bool = true;
			   }
			}
		return bool;
	}
}
