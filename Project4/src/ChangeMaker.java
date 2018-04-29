/* Kyler Ramsey karamsey@calpoly.edu Liam Lefferts ljcates@calpoly.edu 3/2/2018 Project2 Matrix Multiplication Algorithms */

import java.util.Scanner;

public class ChangeMaker {

	static int[] C;
	static int[] A;
	static int change;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.println("Input total number of coin denominations:");
		int k = input.nextInt();
		int[] coinList = new int[k];
		System.out.println("Input coin denominations in decreasing order:");
		for (int i = 0; i < k; i++) {
			coinList[i] = input.nextInt();
		}

		for (;;) {
			System.out.println();
			System.out.println("Enter a positive amount to be changed (enter 0 to quit):");
			change = input.nextInt();
			if (change == 0) {
				input.close();
				System.out.println("Thanks for playing. Good Bye.");
				System.exit(0);
			}

			int[] B = change_DP(change, coinList);
			System.out.println();
			System.out.println("DP algorithm results");
			System.out.println("Amount: " + change);
			System.out.print("Optimal Distribution: ");
			for (int n = 0; n < B.length; n++) {
				if (B[n] > 0) {
					if (n > 0)
						System.out.print(" + ");
					System.out.print(B[n] + "*" + coinList[n] + "c");
					

				}
			}
			System.out.println("\nOptimal coin count: " + C[change]);
			
			
			B = change_greedy(change, coinList);
			System.out.println();
			System.out.println("Greedy algorithm results");
			System.out.println("Amount: " + change);
			System.out.print("Optimal Distribution: ");
			int count = 0;
			for (int n = 0; n < B.length; n++) {
				if (B[n] > 0) {
					if (n > 0)
						System.out.print(" + ");
					count += B[n];
					System.out.print(B[n] + "*" + coinList[n] + "c");


				}
			}
			System.out.println("\nOptimal coin count: " + count);
		}
	}

	public static int[] change_greedy(int n, int[] d) {
		int remainder = -1;
		int coinTotal = n;
		int[] solutionArray = new int[d.length];
		for (int i = 0; i < d.length; i++) {
			solutionArray[i] = coinTotal / d[i];
			remainder = coinTotal % d[i];
			coinTotal = remainder;
		}
		return solutionArray;
	}

	// n = change
	// d = coinList array (denominations)
	// k = unique # of denominations == d.length
	public static int[] change_DP(int n, int[] d) {
		C = new int[n + 1];
		A = new int[n + 1];

		C[0] = 0;
		for (int j = 1; j <= n; j++) {
			int lowIndex = Integer.MAX_VALUE;
			int tempIndex = 0;
			int coin = -Integer.MAX_VALUE;

			for (int i = 0; i < d.length; i++) {
				if ((j - d[i]) >= 0) {
					tempIndex = C[j - d[i]];
					if (tempIndex < lowIndex) {
						lowIndex = tempIndex;
						coin = i;

					}
				}
			}

			C[j] = 1 + lowIndex;
			A[j] = coin;
		}

		int[] B = new int[d.length];

		for (int k = n; k > 0; k -= d[A[k]])
			B[A[k]]++;

		return B;
	}

}
