import java.util.*;

public class CandidateSolution {
	private PreferenceTable table; 
	private Hashtable<StudentEntry, CandidateAssignment> assignments = new Hashtable<StudentEntry, CandidateAssignment>();
	static final int standardPenalty = 1000;
	Projects projects;
	
	
	public CandidateSolution(PreferenceTable prefs) {
		table = prefs;
		projects = new Projects(table.getProjects(), table.getPreassignedProjects());
		createCandidateAssignments();
	}
	
	public ArrayList<String> getProjects(){
		return projects.getProjects();
	}
	
	private void createCandidateAssignments() {
		Hashtable<String, StudentEntry> studentEntries = table.getAllStudentEntries();
	    Enumeration<StudentEntry> e = studentEntries.elements();
	    StudentEntry entry; 
	    while(e.hasMoreElements()) {
	    	entry = e.nextElement();
	    	CandidateAssignment assignment = new CandidateAssignment(entry, projects);
	    	assignments.put(entry, assignment);
	    } 
	}
	
	public CandidateAssignment getAssignmentFor(StudentEntry student) {
		return assignments.get(student);
	}
	
	public CandidateAssignment getRandomAssignment() {
		return getAssignmentFor(table.getRandomStudent());
	}
	
	public void printSolution() {
		Enumeration<StudentEntry> enumKey = assignments.keys();
		System.out.println(assignments.size());
		while(enumKey.hasMoreElements()) {
		    StudentEntry key = enumKey.nextElement();
		    CandidateAssignment val = assignments.get(key);
		    System.out.println("Student: " + key.getStudentName());
		    System.out.println("Assignemt: " + val.getAssignedProject() + "\n");
		}
	}
	
	public int getFitness() {
		return getEnergy() * -1;
	}
	
	public int getEnergy() {
		Enumeration<CandidateAssignment> e = assignments.elements();
		CandidateAssignment assignment;
		int totalEnergy = 0;
	    while(e.hasMoreElements()) {
	    	assignment = e.nextElement();
	    	totalEnergy += assignment.getEnergy();
	    } 
	   
	    int totalPenalties = getPenalties();
	    totalEnergy += totalPenalties;
		return totalEnergy;
	}
	
	public void removeCollisons(){
		Collection<CandidateAssignment> candidateAssign = new ArrayList<CandidateAssignment>();
		ArrayList<String> check = new ArrayList<String>() ; 
		candidateAssign = assignments.values(); 
		int i = 0 ; 
		for (CandidateAssignment x : candidateAssign){
			
			if(check.contains(x.getAssignedProject())){
				System.out.println("1. " + x.getAssignedProject());
				x.randomizeAssignment() ; 
				System.out.println("2. "+ x.getAssignedProject());
				if(!check.contains(x.getAssignedProject())){
					i-- ; 
				}
				/*else if(check.contains(x.getAssignedProject())){
					removeCollisons() ;
				}*/
				
				i++ ; 
			}
			
			check.add(x.getAssignedProject()); 
			
			//System.out.println(x.getAssignedProject()); 
		}
		System.out.println(i); 
	}
		
		
	
	public int getPenalties() {
		Hashtable<String, String> assignedProjects = new Hashtable<String, String>();
	    Enumeration<CandidateAssignment> e = assignments.elements();
	    int totalPenalties = 0;
	    while(e.hasMoreElements()) {
	    	CandidateAssignment current = e.nextElement();
	    	if(assignedProjects.contains(current.getAssignedProject())) {
	    		totalPenalties += standardPenalty;
	    	}
	    	assignedProjects.put(current.getAssignedProject(), current.getAssignedProject());
	    } 
		return totalPenalties;
	}
	

	
	public void printPreferences() {
		int first, second,third, fourth, fifth, sixth, seventh, eighth, ninth, tenth ;
	    first = second  = third = fourth = fifth = sixth = seventh = eighth = ninth = tenth =0;
		
		Enumeration<CandidateAssignment> e = assignments.elements();
		while(e.hasMoreElements()) {
			CandidateAssignment current = e.nextElement();
			switch(current.getAssignmentRank()){
			    case 0: first++; break;
			    case 1: second++; break;
			    case 2: third++; break;
			    case 3: fourth++; break;
			    case 4: fifth++; break;
			    case 5: sixth++; break;
			    case 6: seventh++; break;
			    case 7: eighth++; break;
			    case 8: ninth++; break;
			    case 9: tenth++; break;   
			}
		}
		System.out.println("first Preferences awarded: " + first + "\nSecond Preferences awarded: " + second + "\nThird Preferences awarded: " + third + "\nFourth Preferences awarded: " + fourth + "\nFifth Preferences awarded: " + fifth + "\nSixth Preferences awarded: " + sixth + "\nSeventh Preferences awarded: " + seventh + "\nEight Preferences awarded: " + eighth + "\nNinth Preferences awarded: " + ninth + "\nTenth Preferences awarded: " + tenth);
	}
	

}