import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by alex on 17.12.2014.
 */
public class Matrix {

    static int[][] matr, rmatr;
    static int n, m, alCoordinatesSize;
    static ArrayList<Coordinates> alCoordinates;

    Matrix(int n, int m) {
        this.n = n;
        this.m = m;
        alCoordinates = new ArrayList<Coordinates>();

        form();
        obr1();
        obr2();
    }

    static void form() {

        System.out.println("\nmatr:");

        matr = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i + 1 > n - j) {
                    matr[i][j] = 0;
                } else {
                    matr[i][j] = i + 1;
                }
            }
        }

        out(matr);

        System.out.println("\nrmatr:");

        rmatr = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rmatr[i][j] = new Random().nextInt(100);
            }
        }

        out(rmatr);
    }

    static void obr1() {

        int count = 0, sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i < j && rmatr[i][j] > 0) {
                    sum += rmatr[i][j];
                    count++;
                }
            }
        }

        System.out.println("Количество положительных элиментов: " + count + "\nСумма: " + sum + "\n");
    }

    static void obr2() {

        int max = Math.abs(rmatr[0][0]), count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                int element = Math.abs(rmatr[i][j]);

                if (element > max) {
                    alCoordinates = new ArrayList<Coordinates>();
                    alCoordinates.add(new Coordinates(i, j));
                    max = element;
                    count = 0;
                } else if (element == max && (i != 0 && j != 0)) {
                    alCoordinates.add(new Coordinates(i, j));
                    count++;
                }
            }
        }

        alCoordinatesSize = alCoordinates.size();

        System.out.println("max = " + max + "\nКоординаты:");

        for(int i=0; i<alCoordinatesSize; i++){
            Coordinates coordinates = alCoordinates.get(i);
            System.out.println("i = " + coordinates.getI() + "; j = " + coordinates.getJ());
        }

        int[][] newRmatr = new int[n-alCoordinatesSize][m-alCoordinatesSize];
        int newI=0,newJ=0;

        for(int i=0; i<n; i++){

            if(hitI(i)){
                continue;
            }

            for(int j=0; j<m; j++){
                if(hitJ(j)){
                    continue;
                }

                newRmatr[newI][newJ] = rmatr[i][j];

                newJ++;
            }

            newJ=0;
            newI++;
        }

        out(newRmatr);
    }

    static boolean hitI(int a){

        for (int i=0;i<alCoordinatesSize; i++){
            if(a==alCoordinates.get(i).getI()){
                return true;
            }
        }

        return false;
    }

    static boolean hitJ(int a){

        for (int i=0;i<alCoordinatesSize; i++){
            if(a==alCoordinates.get(i).getJ()){
                return true;
            }
        }

        return false;
    }

    static void out(int[][] matrix) {

        int l1 = matrix.length;
        int l2 = matrix[0].length;

        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {

        int n, m;
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите n: ");
        n = sc.nextInt();

        System.out.print("Введите m: ");
        m = sc.nextInt();

        new Matrix(n, m);
    }

    static class Coordinates {
        int i, j;

        Coordinates(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }
    }
}
