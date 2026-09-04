package java__.nivel4.p1022;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int quantidade = sc.nextInt();
        sc.nextLine();
        String input;
        for(int i = 0; i < quantidade; i++){
            input = sc.nextLine();
            String[] inputSplit = input.split(" ");
            int n1 = 0;
            int d1 = 0;
            int n2 = 0;
            int d2 = 0;
            for(int j = 0; j < inputSplit.length; j+=2){
                if(j == 0){
                    n1 = Integer.parseInt(inputSplit[j]);
                }
                if(j == 2){
                    d1 = Integer.parseInt(inputSplit[j]);
                }
                if(j == 4){
                    n2 = Integer.parseInt(inputSplit[j]);
                }
                if(j == 6){
                    d2 = Integer.parseInt(inputSplit[j]);
                }
            }
            int den = 0;
            int div = 0;
            if(inputSplit[3].equals("+")){
                den = n1*d2+n2*d1;
                div = d1*d2;
            }
            if(inputSplit[3].equals("-")){
                den = n1*d2 - n2*d1;
                div = d1*d2;
            }
            if(inputSplit[3].equals("*")){
                den = n1*n2;
                div = d1*d2;
            }
            if(inputSplit[3].equals("/")){
                den = n1*d2;
                div = n2*d1;
            }
            int denReduzido = 0;
            int divReduzido = 0;
            boolean achou = false;
            for(int k = 2; k <=Math.abs(den) && k<= Math.abs(div) ; k++){
                if(den%k==0 && div%k==0){
                    denReduzido = den/k;
                    divReduzido = div/k;
                    achou = true;
                }
            }
            if(!achou){
                denReduzido = den;
                divReduzido = div;
            }
            System.out.println(den+"/"+div+" = "+denReduzido+"/"+divReduzido);
        }
        sc.close();
    }
}
