package java__.p1169;

import java.math.BigInteger;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int input = s.nextInt();
        for (int j = 0; j < input; j++) {
            int casos = s.nextInt();
            BigInteger primeiro = BigInteger.ONE;
            BigInteger segundo = BigInteger.ZERO;
            for (int k = 0; k < casos; k++) {
                segundo = segundo.add(primeiro);
                primeiro = primeiro.multiply(BigInteger.valueOf(2));
            }
            BigInteger kg = segundo.divide(BigInteger.valueOf(12000));
            System.out.println(kg + " kg");
        }
        s.close();
    }
}