package java__.nivel7.p1232;

import java.util.Scanner;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

public class Main {
    private static final short TA = 3;
    private static final short FA = 6;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Cubo cubo = new Cubo();
        //cubo.print();
        // dir = oposto da face
        // ex = eixo x? entao y
        // ex2 = não é meio?
        // ex3 = não envolve moviment das faces de cima e baixo?
        // D = 1, 1, 1, 1
        // U = 0, 1, 1, 1
        // E = x, 1, 0, 1
        // R = 1, 0, 1, 0
        cubo.rotate(true, true, true, true);
        System.out.println();
        cubo.print();
        sc.close();

    }
    private static class Cubo{
        int[][][] cubo = new int[FA][TA][TA];
        Cubo(){
            preenche();
        }
        private void preenche(){
            int n=0;
            for(int i = 0 ; i < FA; i++){
                for(int j = 0; j< TA; j++){
                    for(int k = 0; k < TA; k++){
                        cubo[i][j][k] =  i;
                        n++;
                    }
                }
            }
        }
        private void print(){
            for(int i = 0 ; i < FA; i++){
                for(int j = 0; j< TA; j++){
                    for(int k = 0; k < TA; k++){
                        System.out.print(cubo[i][j][k]+" ");
                    }System.out.println();
                }System.out.println();
            }
        }
        enum dirTraducao {
            F,D,R,M,
            B,
            U,
            S,
            L;
        }
        private void rotate(boolean dir, boolean ex, boolean ex2, boolean ex3){
            int t = TA -1;
            @FunctionalInterface
            interface IntTernaryOperator { int applyAsInt(int a, int b, int c);}
            IntTernaryOperator ref2 = (x, n, z) ->(x+n)%(TA+z);
            IntUnaryOperator ref = n -> Math.abs(t - n)%TA;
            IntUnaryOperator ref3 = n -> Math.abs(n - TA + 1 );
            int m =  (!ex3 ? 2 : 1);
            int o =(!ex3 ? (FA - TA ) : 1);
            int x;
            int i = 0;
            int j, k;
            if(ex){k = 0; j = t - (!ex2 ? 1 : 0);}else{k = t - (!ex2 ? 1: 0); j = 0;}
            if(ex3){ x = t;}else{ x = FA - 1;}
            //int k = ex == 0 ? 0 : ex == 1 ? t : ex == 2 ? t - 1 : 0;
            //int j = ex == 0 ? t : ex == 1 ? 0 : ex == 2 ? 0 : t - 1;
            int vj = 1;
            int vk = 1;
            for(int g=0; g<TA; g++){
                int p = ref2.applyAsInt(i,m, o );
                int pp = ref2.applyAsInt(i,m*2, o);
                int l = Math.max(ref2.applyAsInt(i,m*3, o), x);
                //int l = x;
                // + ref2.applyAsInt(i,m*3, o-1)
                if(ex){ k=g;}else{j=g;}
                //k = ex == 0 ? g : k;
                //j = ex == 1 ? g : j;
                int vjj = j*vj;
                int vkk = k*vk;
                int vjj_ = !dir ? ref.applyAsInt(vjj) : vjj;
                int vkk_ = !dir ? ref.applyAsInt(vkk) : vkk;
                int vkk__ = ref3.applyAsInt(vkk_);
                int vjj__ = ref3.applyAsInt(vjj_);
                int vjjd = dir ? vjj : vjj_;
                int vkkd = dir ? vkk : vkk_;
                int temp2 = cubo[p][vjjd][vkkd];
                int temp3 = cubo[pp][ex ? vjjd : vjj__][ex ? vkkd: vkk__];
                cubo[p][vjjd][vkkd] = cubo[!ex3 ? i: pp][vjjd][vkkd];
                cubo[i][vjjd][vkkd] = cubo[l][vjjd][vkkd];
                cubo[pp][ex ? vjjd: vjj__][ex? vkkd: vkk__] = !ex3 ? temp2: cubo[i][vjjd][vkkd] ;
                cubo[l][vjjd][vkkd] = !ex3 ? temp3: cubo[pp][ex ? vjjd: vjj__][ex? vkkd: vkk__];

            }
        }
    }
}
