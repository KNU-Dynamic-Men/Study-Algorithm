package elevator;

public class ElevatorSimulator {
	public static void main(String args[]) {
		// ¹®Á¦
//		String userKey = "tester";
//		int problemId = 0;
//		int numOfElevators = 4;

//		String userKey = "tester";
//		int problemId = 1;
//		int numOfElevators = 4;

		String userKey = "tester";
		int problemId = 2;
		int numOfElevators = 4;

		Solution response =new Solution(userKey, problemId, numOfElevators);
		response.simulator();
	}
}


