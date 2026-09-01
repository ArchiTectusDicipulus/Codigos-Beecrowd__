package java__.nivel4.p1087;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int px[] = new int[2];
        int py[] = new int[2];
        String p = s.nextLine();
        while(p.equals("0 0 0 0") == false) {
            px[0] = (int) p.charAt(0) - '0';
            px[1] = (int) p.charAt(2) - '0';
            py[0] = (int) p.charAt(4) - '0';
            py[1] = (int) p.charAt(6) - '0';
            if(px[0] == py[0] && px[1] == py[1]) {
                System.out.println(0);
            } else if (px[0] == py[0] || px[1] == py[1]) {
                System.out.println(1);
            } else {
                if(Math.abs(px[0] - py[0]) == Math.abs(px[1] - py[1])) {
                    System.out.println(1);
                } else {
                    System.out.println(2);
                }
            }
            p = s.nextLine();
        }
        s.close();
    }
}