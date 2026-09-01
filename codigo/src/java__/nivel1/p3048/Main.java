package java__.nivel1.p3048;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int x = s.nextInt();
        int l [] = new int[x];
        int a = 0;
        int atual = 0;
        for (int j = 0; j < x; j++) {
            l[j] = s.nextInt();
        }
        atual = l[0];
        a++;
        for (int i = 1; i < x; i++) {
            if(atual != l[i]){
                atual = l[i];
                a++;
            }
        }
        System.out.println(a);
    }
}