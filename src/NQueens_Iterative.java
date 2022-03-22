// import java.util.Arrays;

public class NQueens_Iterative {
	// Constant is initialized for puzzle size (# Queens and Matrix Size)
	// Variable is declared to keep track of number of captured solutions
	// Array is instantiated with size N for position of a queen for a given column (ranges from 0 to N-1)
	final int N = 9;
	int counter = 0;
	int [] position = new int[N];
	
	public static void main(String[] args) {
		// Start-time is recorded before function call to solve the puzzle
		long startTime = System.currentTimeMillis();
		
        NQueens_Iterative Solution = new NQueens_Iterative();
        Solution.solveNQ(352);
        
        // End-time is recorded once the solveNQ function has completed its execution
        // Elapsed-time is determined and displayed
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        System.out.println(timeElapsed + "ms");
	}
	
	// Function to solve N-Queen puzzle, parameter numSol refers to number of solutions desired
    boolean solveNQ(int numSol) {
        // Board (2D-Array) is instantiated and initialized with values of 0
        int [][] board = new int [N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = 0;
            }
        }

        // Variable is initialized for if a change needs to be forced in the solution pattern to achieve a different solution
        // An index value is initialized (to prevent any reading errors for different scope) for the index/column that will have its position changed
        boolean forceChange = false;;
        int changeIndex=-1;

        // Loops until number of desired solutions are created
        while(counter<numSol) {
        	// j -> loop through columns  i -> loop through unchecked rows
        	for (int j = 0; j < N; j++) {
        		// Checks if a change needs to be forced on the pattern solution; changes the index/column in this case
        		if (forceChange==true) {
	        		j = changeIndex;
	        		forceChange = false;
	        	}
        		
	        	for (int i = position[j]; i < N; i++) {
	        		// if a queen is safe to be placed somewhere in the column, a 1 is set on the board and the position is updated
	        		if (isSafe(board, i, j)) {
	            		board[i][j] = 1;
	            		position[j] = i;
	            		/* when backtracking solutions, we might run into scenarios where a previously checked column
	            		 * is now unchecked, but its position in the 2D-array is not restored to default (0). The change is made here
	            		 */
	            		if (j!=N-1)
	            			position[j+1] = 0;
		        	}
        		}
	        		
	            
	        	/* After traversing a column, if no safe space was found, the 1 in the previous column is removed and its position is shifted
	        	 * and the outer for-loop decrements by 2 - when this iteration ends, the for-loop increments by +1, hence a -2 is required
	        	 */
	            if (colIsEmpty(board, j)) {
	            	board[position[j-1]][j-1] = 0;
	            	position[j-1]++;
	            	j-=2;
	            }
	        }
	        
        	// Solution is printed and the counter is incremented
	        printSolution(board);
        	// System.out.print(Arrays.toString(position) + "\n"); // Can be used to check the positions of the queens along the columns
	        counter++;
	        
	        /* Traversing the columns backwards, we find the farthest column to the right in which a row can be shifted by +1 (i+1) while 
	         * staying within the matrix size (to prevent OutOfBounds Exception from being thrown)
	         * This code fragment is required to continue the search for multiple solutions from the point where a previous search ended
	         */
	        for (int j = N-1; j > 0; j--) {
	        		if (position[j]<N-1) {
	        			for (int i = N-1; i>=j; i--) {
	        				board[position[i]][i] = 0;
	        				if (counter==34) {
	        					System.out.println("run" + board[position[i]][i]);
	        				}
        				}
	        			position[j]++;
	        			forceChange = true;
	        			changeIndex=j;
	        			j=0;
	        		}
	        }
	        
        }
    	return true;
    }
	
    // Function to determine whether a queen can be safely placed in a given position on the board
    boolean isSafe(int [][] board, int row, int col) {
    	// Checks the row
    	for (int j = 0; j < col; j++)
    		if (board[row][j] == 1)
    			return false;

    	// Checks the column
    	for (int i = 0; i < row; i++)
    		if (board[i][col] == 1)
    			return false;

    	// Checks upper diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        // Checks lower diagonal
        for (int i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    // Determines if a column has no queen and returns a boolean value
    boolean colIsEmpty(int [][] board, int col) {
    	// Check if column is empty
    	for (int i = 0; i < N; i++) {
    		if (board[i][col] == 1)
    			return false;
    	}
    	return true;
    }
    
    // Prints current board state when called - in this case it is only called to print a solution
    void printSolution(int board[][]) {
        System.out.println("Solution " + (counter+1) + ":");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print("  " + board[i][j] + "  ");
            }
            System.out.println("\n");
        }
        System.out.println();
    }
}
