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
	
	public void randomChange() {
		projects.addProjects(assignment);
		String rand = "";
		int count = 00;
		while(true) {
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
			} else if (count == 20) {
				projects.removeProject(assignment);
				break;
			}
		}
	}
	
	public void randomizeAssignment() throws NullPointerException {
		int count = 0;
		previousAssignment = assignment;
		//projects.addProjects(assignment);
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
		if(ranking == -1) {
			ranking = 15;
		}
		int energy = (ranking + 1) * (ranking+1);
		return energy;
	}
	
	public int getAssignmentRank() {
		int rank= student.getRanking(assignment);
		if (rank == -1) {
			return 15;
		}
		return rank;
	}
}