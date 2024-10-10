import java.util.*;
public class Sudoku {

	static int N = 9;//size of the 2D matrix (9*9)

	/*fills the empty(null) values in the matrix according to the conditions(non-duplication across rows,
	columns, and boxes)*/
	static boolean solveSudoku(int arr[][], int row,int col){

		/*if we reached the end of matrix [row(8) and column(9)] return true to avoid backtracking*/
		if (row == N - 1 && col == N)
			return true;

		//if column value becomes 9, we go for next row and column starts from 0
		if (col == N) {
			row++;
			col = 0;
		}

		//if the current position of the array contains value other than 0, we go to next column
		if (arr[row][col] != 0)
			return solveSudoku(arr, row, col + 1);

		for (int num = 1; num < 10; num++) {

			// Check if it is safe to place the num (1-9) in the given row ,col then move to next column
			if (isSafe(arr, row, col, num)) {

				/* assigning the num in the current (row,col) position of the arr and assuming our assigned num 
				in the position is correct */
				arr[row][col] = num;

				//moving to next column after assigning number
				if (solveSudoku(arr, row, col + 1))
					return true;
			}
			// removing the assigned num if our assumption was wrong and we go for another number
			arr[row][col] = 0;
		}
		return false;
	}

	//function to print matrix
	static void print(int[][] arr)
	{
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				System.out.print(arr[i][j] + " ");
			System.out.println();
		}
	}

	// Check whether it will be legal to assign num to the given row, col
	static boolean isSafe(int[][] arr, int row, int col,int num)
	{
		//if we find the same num in the similar row , we return false
		for (int x = 0; x <= 8; x++)
			if (arr[row][x] == num)
				return false;

		//if we find the same num in the similar column ,we return false
		for (int x = 0; x <= 8; x++)
			if (arr[x][col] == num)
				return false;

		//if we find the same num in the particular 3*3 matrix, we return false
		int sRow = row - row % 3;
		int sCol = col - col % 3;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (arr[i + sRow][j + sCol] == num)
					return false;

		return true;
	}

	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		int arr[][]=new int[N][N];
        for(int i = 0 ; i < N ; i++){
			for(int j = 0 ; j < N ; j++ ){
				arr[i][j]=0;
			}
		}
		/*int arr[][] =  { { 0, 0, 6, 5, 0, 8, 0, 0, 0 },
						 { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
						 { 0, 0, 7, 0, 0, 0, 0, 0, 1 },
						 { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
						 { 9, 0, 0, 8, 0, 3, 0, 0, 5 },
						 { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
						 { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
						 { 0, 0, 0, 0, 0, 0, 0, 0, 4 },
						 { 0, 0, 5, 0, 0, 6, 0, 0, 0 } };*/
        System.out.println("Initially the puzzle is:");
		print(arr);
		System.out.println("---------------------------");
		if (solveSudoku(arr, 0, 0))
			print(arr);
		else
			System.out.println("THE GIVEN SUDOKU PUZZLE HAS NO SOLUTION");
	}
}