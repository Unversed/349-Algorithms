package part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameProblem {

	static int n;
	static int m;
	static int[][] A;
	static int[][] S;
	
	static void main() {
		initialize();
		game(n, m, A);
	}
	
	public static void game(int n, int m, int[][] A) {
		
		int maxStart = -9999999;
		int maxX = n;
		int maxY = m;
		
		for (int i = n-1; i >= 0; i--) {
			for (int j = m-1; j >= 0; j--) {
				
				if (i == n-1 && j == m-1 ) {
					S[0][0] = A[0][0];
				} else if(i == n-1 && j < m-1 ) {
					S[i][j] = max(S[i][j+1], 0) + A[i][j];
				} else if ( i < n-1 && j == m - 1 ) {
					S[i][j] = max(S[i+1][j], 0) + A[i][j];
				} else {
					S[i][j] = max(S[i+1][j], S[i][j+1]) + A[i][j];
				}
				
				if(S[i][j] > maxStart) {
					maxStart = S[i][j];
					maxX = i;
					maxY = j;
				}
	
			}
		}
	}
	
	private static int max(int a, int b) {
		return a > b ? a : b;
	}
	
	private static void initialize(){
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter filename");
		String fileName = reader.next();
		Scanner fileReader = null;
		try {
			fileReader = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find file: " + fileName);
		}

		n = fileReader.nextInt();
		m = fileReader.nextInt();
		
		A = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				A[n][m] = fileReader.nextInt();
			}
		}
	}
	
}
