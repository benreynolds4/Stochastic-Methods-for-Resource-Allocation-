import java.util.ArrayList;
import java.util.Random;

public class Projects {
	private Random random = new Random();
	private ArrayList<String> projects = new ArrayList<String>();
	private ArrayList<String> preassignedProjects = new ArrayList<String>();
	
	public Projects(ArrayList<String> projectList, ArrayList<String> preassignedProjectList) {
		projects = projectList;
		System.out.println(projects.size());
		preassignedProjects = preassignedProjectList;
		System.out.println(preassignedProjects.size());
	}
	
	public void removeProject(String project) {
		projects.remove(project);
	}
	
	public void addProjects(String project){
		projects.add(project);
	}
	
	public ArrayList<String> getProjects(){
		return projects;
	}
	
	public ArrayList<String> getPreassignedProjects() {
		return preassignedProjects;
	}
	
	public void removePreassignedProject(String project) {
		preassignedProjects.remove(project);
	}
	
	public String getRandomProject(){
		int index = random.nextInt(projects.size()) ;
		return projects.get(index);
	}
	
}
