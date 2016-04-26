import java.io.IOException;

public class TestClass {
		
		public static void main(String[] args) throws IOException{
			PreferenceTable table = new PreferenceTable("tabfile.txt");
			SimulatedAnnealing SA = new SimulatedAnnealing(table);	
		}
	}


