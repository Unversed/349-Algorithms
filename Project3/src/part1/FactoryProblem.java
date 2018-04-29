package part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.out;

public class FactoryProblem {

	static int stationCount;
	static int e1;
	static int e2;
	static int x1;
	static int x2;
	static int[] stations1;
	static int[] stations2;
	static int[] transfers1;
	static int[] transfers2;

	static int[] f1;
	static int[] f2;
	static int[] l1;
	static int[] l2;

	static int F;
	static int L;

	public static void main(String[] args) {
		initialize();
		assemblyLineScheduler();
		printSolution();
	}

	private static void initialize() {

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

		stationCount = fileReader.nextInt();
		e1 = fileReader.nextInt();
		e2 = fileReader.nextInt();
		x1 = fileReader.nextInt();
		x2 = fileReader.nextInt();

		stations1 = new int[stationCount];
		for (int i = 0; i < stationCount; i++) {
			stations1[i] = fileReader.nextInt();
		}

		stations2 = new int[stationCount];
		for (int i = 0; i < stationCount; i++) {
			stations2[i] = fileReader.nextInt();
		}

		transfers1 = new int[stationCount - 1];
		for (int i = 0; i < stationCount - 1; i++) {
			transfers1[i] = fileReader.nextInt();
		}

		transfers2 = new int[stationCount - 1];
		for (int i = 0; i < stationCount - 1; i++) {
			transfers2[i] = fileReader.nextInt();
		}

		f1 = new int[stationCount];
		f2 = new int[stationCount];
		l1 = new int[stationCount - 1];
		l2 = new int[stationCount - 1];

	}

	private static void printSolution() {

		out.println("Fastest time is: " + F);
		out.println("The optimal route is:");

		out.println();

		ArrayList<String> printSolution = new ArrayList<String>();
		for (int j = stationCount; j >= 2; j--) {

			if (L == 1) {
				printSolution.add("station " + (j - 1) + ", line " + l1[j - 2]);
				L = l1[j - 2];
			} else {
				printSolution.add("station " + (j - 1) + ", line " + l2[j - 2]);
				L = l2[j - 2];
			}
		}

		for (int j = printSolution.size() - 1; j >= 0; j--) {
			out.println(printSolution.get(j));
		}

		out.println("station " + stationCount + ", line " + L);
	}

	static int mindex(int a, int b, int index, boolean firstList) {

		if (firstList) {
			if (a <= b) {
				l1[index] = 1;
				return a;
			} else {
				l1[index] = 2;
				return b;
			}
			
		} else {
			if (a <= b) {
				l2[index] = 2;
				return a;
			} else {
				l2[index] = 1;
				return b;
			}
		}
	}

	public static void assemblyLineScheduler() {

		f1[0] = e1 + stations1[0];
		f2[0] = e2 + stations2[0];

		for (int i = 1; i < stationCount; i++) {

			f1[i] = mindex(f1[i - 1] + stations1[i], f2[i - 1] + stations1[i] + transfers2[i - 1], i - 1, true);

			f2[i] = mindex(f2[i - 1] + stations2[i], f1[i - 1] + stations2[i] + transfers1[i - 1], i - 1, false);
		}

		if (f1[stationCount - 1] + x1 <= f2[stationCount - 1] + x2) {
			F = f1[stationCount - 1] + x1;
			L = 1;
		} else {
			F = f2[stationCount - 1] + x2;
			L = 2;
		}
	}
}
