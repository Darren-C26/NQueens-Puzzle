import java.util.Arrays;

public class NQueens_Iterative {
	final int N = 4;
	int counter = 0;
	int [] position = new int[N];
	public static void main(String[] args) {

        NQueens_Iterative Solution = new NQueens_Iterative();
        for (int i = 0; i < 1; i++)
            Solution.solveNQ();

	}
	
	
    boolean solveNQ() {
        // Create board and initialize all values to 0
        int [][] board = new int [N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = 0;
            }
        }
        
        for (int j = 0; j < N; j++) {
        	for (int i = position[j]; i < N; i++) {
        		if (isSafe(board, i, j)) {
            		board[i][j] = 1;
            		position[j] = i;
            		if (j!=N-1)
            			position[j+1] = 0;
        		}
        	}
            printSolution(board);
            
            if (colIsEmpty(board, j)) {
            	board[position[j-1]][j-1] = 0;
            	position[j-1]++;
            	if (position[j-1]==N){
            		
            	}
            	j-=2;

            	System.out.println(Arrays.toString(position));
            }
        }
        
    	return true;
    }
	
    boolean isSafe(int [][] board, int row, int col) {
    	// Check this row
    	for (int j = 0; j < col; j++)
    		if (board[row][j] == 1)
    			return false;

    	// Check the column
    	for (int i = 0; i < row; i++)
    		if (board[i][col] == 1)
    			return false;

    	// Check upper diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        // Check lower diagonal
        for (int i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    boolean colIsEmpty(int [][] board, int col) {
    	// Check if column is empty
    	for (int i = 0; i < N; i++) {
    		if (board[i][col] == 1)
    			return false;
    	}
    	return true;
    }
    
    // Print and write solution to array
    void printSolution(int board[][]) {
        System.out.println("Solution " + counter + ":");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print("  " + board[i][j] + "  ");
            }
            System.out.println("\n");
        }
        System.out.println();
    }
}
