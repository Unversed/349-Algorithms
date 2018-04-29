import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MatrixTest {

    public static void main(String [] args) throws FileNotFoundException{
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter filename");
        String fileName = reader.next();
        Scanner fileReader = null;
        //System.out.println(fileName);

        try {
            fileReader = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file: " + fileName);
        }

        int rowsA = fileReader.nextInt();
        int columnsA = fileReader.nextInt();
        System.out.print(rowsA + " ");
        System.out.print(columnsA + " ");
        System.out.println();
        int[][]A = new int[rowsA][columnsA];

        for (int i = 0; i < rowsA; i++){
            for (int j = 0; j < columnsA; j++){
                A[i][j]=fileReader.nextInt();
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }

        int rowsB = fileReader.nextInt();
        int columnsB = fileReader.nextInt();
        System.out.print(rowsB + " ");
        System.out.print(columnsB + " ");
        System.out.println();
        int[][]B = new int[rowsB][columnsB];

        for (int i = 0; i < rowsB; i++){
            for (int j = 0; j < columnsB; j++){
                B[i][j]=fileReader.nextInt();
                System.out.print(B[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        int [][] result = new int [A.length][B[0].length];

        try {
            result = MatrixProduct./*matrixProduct_DAC(A, B);*/matrixProduct_Strassen(A, B);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return;
        }

        for (int i = 0; i < rowsA; i++){
            for (int j = 0; j < columnsB; j++){
                System.out.print(result[i][j]+ " ");
            }
            System.out.println();
        }

    }


    public static int[][] matrixProduct (int [][] A, int[][] B) throws IllegalArgumentException {
        if (A[0].length != B.length)
        {
            throw new IllegalArgumentException("Columns and rows not equal!");
        }
        int [][] result = new int [A.length][B[0].length];
        for (int i = 0; i < A.length; i++){
            for (int j = 0; j < B[0].length; j++){
                result[i][j] = 0;
                for (int k = 0; k < B.length; k++){
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }
}
