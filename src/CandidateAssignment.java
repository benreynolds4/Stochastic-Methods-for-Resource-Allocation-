public class CandidateAssignment {
	private StudentEntry student;
	private String assignment = "";
	private String previousAssignment;
	
	public CandidateAssignment(StudentEntry entry) {
		student = entry;
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
	
	public void randomizeAssignment() {
		previousAssignment = assignment;
		assignment = student.getRandomPreference();
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