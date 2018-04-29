/* Kyler Ramsey karamsey@calpoly.edu Liam Lefferts ljcates@calpoly.edu 2/1/2018 Project2 Matrix Multiplication Algorithms */

public class MatrixProduct {

	public static int[][] matrixProduct_DAC(int[][] A, int[][] B) throws IllegalArgumentException {
		checkMatrix(A[0].length, B.length, A.length, B[0].length);
		return matrixProduct_DAC(A, 0, 0, B, 0, 0, A[0].length);
	}

	private static int[][] matrixProduct_DAC(int[][] A, int startcolA, int startrowA, int[][] B, int startcolB,
			int startrowB, int n) {

		int[][] C = new int[n][n];
		if (n == 1) {
			
			C[0][0] = A[startrowA][startcolA] * B[startrowB][startcolB];
		} else {
			
			int newSize = n / 2;
			addMatrix(
					C, matrixProduct_DAC(A, startcolA, startrowA, B, startcolB, startrowB, newSize),
					matrixProduct_DAC(A, startcolA + newSize, startrowA, B, startcolB, startrowB + newSize, newSize), 
					0, 0);

			addMatrix(
					C, matrixProduct_DAC(A, startcolA, startrowA, B, startcolB + newSize, startrowB, newSize), 
					matrixProduct_DAC(A, startcolA + newSize, startrowA, B, startcolB + newSize, startrowB + newSize, newSize),
					0, newSize);

			addMatrix(
					C, matrixProduct_DAC(A, startcolA, startrowA+ newSize, B, startcolB, startrowB, newSize), 
					matrixProduct_DAC(A, startcolA + newSize, startrowA + newSize, B, startcolB, startrowB + newSize, newSize),
					newSize, 0);
			
			addMatrix(
					C, matrixProduct_DAC(A, startcolA, startrowA+ newSize, B, startcolB+ newSize, startrowB, newSize), 
					matrixProduct_DAC(A, startcolA + newSize, startrowA + newSize, B, startcolB + newSize, startrowB + newSize, newSize),
					newSize, newSize);
		}
		return C;
	}

	private static void addMatrix(int[][] C, int[][] A, int[][] B, int rowC, int colC) {
		int n = A.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				C[i + rowC][j + colC] = A[i][j] + B[i][j];
			}
		}
	}

	public static int[][] matrixProduct_Strassen(int[][] A, int[][] B) throws IllegalArgumentException {
		checkMatrix(A[0].length, B.length, A.length, B[0].length);
		return matrixProduct_Strassen(A, 0, 0, B, 0, 0, A[0].length);
	}

	private static int[][] matrixProduct_Strassen(int[][] A, int startrowA, int startcolA, 
												  int[][] B, int startrowB, int startcolB, 
												  int n) {
		
		int[][] C = new int[n][n];
		
		if (n == 1) {
			C[0][0] = A[startrowA][startcolA] * B[startrowB][startcolB];
			
		} else {
			//A11 * (B12 - B22)
			int[][] p1 = matrixProduct_Strassen(
					A, startrowA, startcolA, 
					subtract(B, startrowB, startcolB+(n/2), B, startrowB+(n/2), startcolB+(n/2), (n/2)), 0, 0, 
					(n/2));

			//(A11+A12) * B22
			int[][] p2 = matrixProduct_Strassen(
					add(A, startrowA, startcolA, A, startrowA, startcolA + (n/2), (n/2)), 0, 0,
					B, startrowB+(n/2), startcolB+(n/2), 
					(n/2));

			//(A21 + A22) * B11
			int[][] p3 = matrixProduct_Strassen(
					add(A, startrowA+(n/2), startcolA, A, startrowA+(n/2), startcolA+(n/2), (n/2)), 0, 0, 
					B, startrowB, startcolB, 
					(n/2));

			// A22 * (B21 - B11)
			int[][] p4 = matrixProduct_Strassen(
					A, startrowA+(n/2), startcolA+(n/2), 
					subtract(B, startrowB+(n/2), startcolB, B, startrowB, startcolB, (n/2)), 0, 0, 
					(n/2));

			//(A11 + A22) * (B11 + B22)
			int[][] p5 = matrixProduct_Strassen(
					add(A, startrowA, startcolA, A, startrowA+(n/2), startcolA+(n/2), (n/2)), 0, 0, 
					add(B, startrowB, startcolB, B, startrowB+(n/2), startcolB+(n/2), (n/2)), 0, 0, 
					(n/2));

			//(A12 - A22) * (B21 + B22)
			int[][] p6 = matrixProduct_Strassen(
					subtract(A, startrowA, startcolA + (n/2), A, startrowA+(n/2), startcolA+(n/2), (n/2)), 0, 0, 
					add(B, startrowB+(n/2), startcolB, B, startrowB+(n/2), startcolB+(n/2), (n/2)), 0, 0, 
					(n/2));
	
			//(A11 - A21) (B11 + B12)
			int[][] p7 = matrixProduct_Strassen(
					subtract(A, startrowA, startcolA, A, startrowA+(n/2), startcolA, (n/2)), 0, 0, 
					add(B, startrowB, startcolB, B, startrowB, startcolB+(n/2), (n/2)), 0, 0,
					(n/2));


			
			int[][] c1 = add(subtract( add(p5, 0, 0, p4, 0, 0, (n/2)), 0, 0, p2, 0, 0, (n/2)), 0, 0, p6, 0, 0, (n/2));

			int[][] c2 = add(p1, 0, 0, p2, 0, 0, (n/2));

			int[][] c3 = add(p3, 0, 0, p4, 0, 0, (n/2));

			int[][] c4 = subtract(subtract(add(p5, 0, 0, p1, 0, 0, (n/2)), 0, 0, p3, 0, 0, (n/2)), 0, 0, p7, 0, 0, (n/2));


			for (int i = 0; i < (n/2); i++) {
				for (int j = 0; j < (n/2); j++) {
					C[i][j] = c1[i][j];
					C[i][j + (n/2)] = c2[i][j];
					C[i + (n/2)][j] = c3[i][j];
					C[i + (n/2)][j + (n/2)] = c4[i][j];
				}
			}
		}
		return C;
	}

	private static int[][] add(int[][] A, int startrowA, int startcolA, int[][] B, int startrowB, int startcolB,
			int n) {
		
		int[][] C = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = A[i + startrowA][j + startcolA] + B[i + startrowB][j + startcolB];
			}
		}
		return C;
	}

	private static int[][] subtract(int[][] A, int startrowA, int startcolA, int[][] B, int startrowB, int startcolB,
			int n) {
		
		int[][] C = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = A[i + startrowA][j + startcolA] - B[i + startrowB][j + startcolB];
			}
		}
		return C;
	}

	private static void checkMatrix(int x1, int y1, int x2, int y2) throws IllegalArgumentException {
		if (x1 != y1 || x2 != y2 ) {
			throw new IllegalArgumentException("Columns and rows not equal! Matrix invalid!");
		}

		if ((x1 != 0) && !((x1 & (x1 - 1)) ==  0)) {
			throw new IllegalArgumentException("Matrix dimensions not power of 2!");

		}
	}
}
