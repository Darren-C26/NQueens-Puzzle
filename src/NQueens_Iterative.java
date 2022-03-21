
public class NQueens_Iterative {
	final int N = 8;
	int counter = 0;
	public static void main(String[] args) {

        NQueens_Iterative Solution = new NQueens_Iterative();
        for (int i = 0; i < 10; i++)
            Solution.solveNQ();

	
	}
	
	
    boolean solveNQ() {
        // Create board and initialize all values to 0
        int [][] board = new int [N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board [i][j] = 0;
            }
        }
    	return true;
    }
	

}
