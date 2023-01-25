import java.util.Scanner;
import java.util.Arrays;

public class CholeskiProgram {

    public static double[][] obliczanieMacierzyL(int n, double[][] A) {
        double[][] L = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                double sum = 0;
                for (int k = 0; k < j; k++) {
                    sum += L[i][k] * L[j][k];
                }
                if (i == j) {
                    L[i][j] = Math.sqrt(A[i][i] - sum);
                } else {
                    L[i][j] = (A[i][j] - sum) / L[j][j];
                }
            }
        }
        return L;
    }

    public static double[] rozwiazanieMacierzTrojkatnaDolna(int n, double[][] L, double[] b) {
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < i; j++) {
                sum += L[i][j] * y[j];
            }
            y[i] = (b[i] - sum) / L[i][i];
        }
        return y;
    }

    public static double[] rozwiazanieMacierzTrojkatnaGorna(int n, double[][] L, double[] y) {
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += L[j][i] * x[j];
            }
            x[i] = (y[i] - sum) / L[i][i];
        }
        return x;
    }

    public static void choleski() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Podaj wymiar macierzy: ");
        int n = scan.nextInt();

        double[][] A = new double[n][n];
        System.out.println("Podaj macierz A: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("Wprowadz element ["+i+"]"+"["+j+"]");
                A[i][j] = scan.nextDouble();
            }
        }

        double[] b = new double[n];
        System.out.println("Podaj wektor wyrazów wolnych b: ");
        for (int i = 0; i < n; i++) {
            System.out.println("Podaj element "+i+": ");
            b[i] = scan.nextDouble();
        }

        double[][] L = obliczanieMacierzyL(n, A);
        System.out.println("Macierz L: ");
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(L[i]));
        }

        double[] y = rozwiazanieMacierzTrojkatnaDolna(n, L, b);
        System.out.println("Wektor y: " + Arrays.toString(y)+"^T");

        double[] x = rozwiazanieMacierzTrojkatnaGorna(n, L, y);
        System.out.println("Wektor x: " + Arrays.toString(x)+"^T");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Rozwiazaniem ukladu rownan jest wektor x: " + Arrays.toString(x)+"^T");
    }
}

