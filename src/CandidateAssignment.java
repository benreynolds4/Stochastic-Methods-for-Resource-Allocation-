public class CandidateAssignment {
	private StudentEntry student;
	private String assignment = "";
	private String previousAssignment;
	private Projects projects;
	
	public CandidateAssignment(StudentEntry entry, Projects projectIn) {
		student = entry;
		projects = projectIn;
		randomizeAssignment();
	}
	
	public void undoChange() {
		assignment = previousAssignment;
	}
	
	public StudentEntry getStudentEntry() {
		return student;
	}
	
	public String getAssignedProject() {
		return assignment;
	}
	
	public void randomizeAssignment() throws NullPointerException {
		int count = 0;
		previousAssignment = assignment;
		String rand = "";
		while(true){
			count++;
			rand = student.getRandomPreference();
			if(projects.getProjects().contains(rand)){
				assignment = rand;
				projects.removeProject(rand);
				break;
			} else if(projects.getPreassignedProjects().contains(rand)) {
				assignment = rand;
				projects.removePreassignedProject(rand);
				break;
			} else if (count == 100){
				assignment = projects.getRandomProject();
				projects.removeProject(assignment);
				break;
			}
			else if(projects.getProjects().size() == 0){
				break;
			}
		}
	}
	
	public int getEnergy() {
		int ranking = student.getRanking(assignment);
		int energy = (ranking + 1) * (ranking+1);
		return energy;
	}
	
	public int getAssignmentRank() {
		return student.getRanking(assignment);
	}
}