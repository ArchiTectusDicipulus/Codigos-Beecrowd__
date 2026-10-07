package java__.nivel7.p1232;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final short TA = 3;
    private static final short FA = 6;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        new Gerente(sc);
        sc.close();
    }
    private static class Gerente{
        Gerente(Scanner sc){
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                Cubo.preenche();
                System.out.println(retornaQuantidadeDeVezes(retornaMovimentos(s)));
            }
        }
        private int retornaQuantidadeDeVezes(ArrayList<Cubo.Movimentos> movimentos){
            int i = 0;
            do{
                for(Cubo.Movimentos movimento : movimentos){
                    movimento.move();
                }i++;
            }while(!Cubo.isResolvido());
            return i;
        }
        private ArrayList<Cubo.Movimentos> retornaMovimentos(String s){
            ArrayList<Cubo.Movimentos> movimentos = new ArrayList<Cubo.Movimentos>();
            for(int i = 0; i < s.length(); i++){
                movimentos.add(decompoem(s.charAt(i)+""));
            }
            return movimentos;
        }
        private Cubo.Movimentos decompoem(String elemento){
            return Cubo.Movimentos.valueOf(elemento);
        }
    }
    private static class Cubo {
        // Faces vistas de fora: 0=F, 1=R, 2=B, 3=L, 4=U, 5=D.
        // Camadas: 1=face de referencia, 2=meio, 3=face oposta.
        static int[][][] cubo = new int[FA][TA][TA];
        private static class Movimento{
            boolean direcao;
            int index;
            int eixo;
            public Movimento(boolean direcao, int index, int eixo) {
                this.eixo = eixo;
                this.index = index;
                this.direcao = direcao;
            }
            Movimento(){}
        }
        enum Movimentos{
            F(true, 1, 0),
            D(true, 1, 1),
            R(true, 1, 2),
            M(true, 2, 1),
            S(true, 2, 0),

            B(oposto(F)),
            U(oposto(D)),
            L(oposto(R)),
            f(direcaoInversa(F)),
            d(direcaoInversa(D)),
            r(direcaoInversa(R)),
            m(direcaoInversa(M)),
            s(direcaoInversa(S)),
            b(direcaoInversa(B)),
            u(direcaoInversa(U)),
            l(direcaoInversa(L));

            private static int inv(int index){
                return TA + 1 - index;
            }
            private static Movimento direcaoInversa(Movimentos x){
                return new Movimento(!x.movimento.direcao, x.movimento.index, x.movimento.eixo);
            }
            private static Movimento oposto(Movimentos x){
                return new Movimento(!x.movimento.direcao, inv(x.movimento.index), x.movimento.eixo);
            }
            private final Movimento movimento;
            public boolean move(){
                return seletor_movimento(movimento.direcao, movimento.index, movimento.eixo);
            }
            Movimentos(Movimento movimento){
                this.movimento = movimento;
            }
            Movimentos(boolean direcao, int index, int eixo) {
                movimento = new Movimento(direcao,index, eixo);
            }
        }
        private static boolean isResolvido(){
            int corAtual;
            for (int i = 0; i < FA; i++) {
                corAtual = cubo[i][0][0];
                for (int j = 0; j < TA; j++) {
                    for (int k = 0 ; k < TA; k++) {
                        if(cubo[i][j][k] != corAtual){
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        private static boolean seletor_movimento(boolean dir, int index, int n){
            switch (n) {
                case 0 :{
                    F(dir, index);
                    break;
                }
                case 1 : {
                    D(dir, index);
                    break;
                }
                case 2 : {
                    R(dir, index);
                    break;
                }
                default : {
                    System.out.println("ERROR");
                    return false;
                }
            }
            return true;
        }
        private static int[][][] R(boolean dir, int index){
            int[][] temp = new int[4][TA];
            int coluna = TA - index;
            int oposta = index - 1;
            // Ciclo F -> U -> B -> D; a coluna de B fica invertida.
            for (int i = 0; i < TA; i++) {
                temp[0][i] = cubo[0][i][coluna];
                temp[1][i] = cubo[4][i][coluna];
                temp[2][i] = cubo[2][TA - 1 - i][oposta];
                temp[3][i] = cubo[5][i][coluna];
            }
            temp = move(temp, dir);
            for (int i = 0; i < TA; i++) {
                cubo[0][i][coluna] = temp[0][i];
                cubo[4][i][coluna] = temp[1][i];
                cubo[2][TA - 1 - i][oposta] = temp[2][i];
                cubo[5][i][coluna] = temp[3][i];
            }
            if (index != 2) {
                int face = index == 1 ? 1 : 3;
                cubo[face] = rotate(cubo[face], index == 1 ? dir : !dir);
            }
            return cubo;
        }
        private static int[][][] D(boolean dir, int index){

            int[][] temp = new int[4][TA];
            int n = TA - index;
            for(int i = 0; i < 4; i++){
                temp[i] = cubo[i][n].clone();
            }
            temp = move(temp, dir);
            for(int i = 0; i < 4; i++){
                cubo[i][n] = temp[i].clone();
            }
            if(index!=2){
                int i = index == 1 ? 5: 4;
                cubo[i] = rotate(cubo[i], index == 1 ? dir : !dir);
            }
            return cubo;
        }
        private static int[][][] F(boolean dir, int index){
            int[][] temp = new int[4][TA];
            int perto = index - 1;
            int longe = TA - index;
            // Ciclo U -> R -> D -> L, seguindo a borda da face F.
            // Guarda todas as bordas antes de escrever no cubo.
            for (int i = 0; i < TA; i++) {
                temp[0][i] = cubo[4][longe][i];
                temp[1][i] = cubo[1][i][perto];
                temp[2][i] = cubo[5][perto][TA - 1 - i];
                temp[3][i] = cubo[3][TA - 1 - i][longe];
            }
            temp = move(temp, dir);
            for (int i = 0; i < TA; i++) {
                cubo[4][longe][i] = temp[0][i];
                cubo[1][i][perto] = temp[1][i];
                cubo[5][perto][TA - 1 - i] = temp[2][i];
                cubo[3][TA - 1 - i][longe] = temp[3][i];
            }
            if (index != 2) {
                int face = index == 1 ? 0 : 2;
                cubo[face] = rotate(cubo[face], index == 1 ? dir : !dir);
            }
            return cubo;
        }
        Cubo() {

        }
        private static int[][] rotate(int[][] vetor, boolean dir){
            int tamanho = vetor.length;
            int[][] temp = new int[tamanho][tamanho];
            // true: 90 graus no sentido horario; false: anti-horario.
            for (int i = 0; i < tamanho; i++) {
                for (int j = 0; j < tamanho; j++) {
                    temp[i][j] = dir ? vetor[tamanho - 1 - j][i] : vetor[j][tamanho - 1 - i];
                }
            }
            return temp;
        }
        public static int[][] move(int[][] vetor, boolean dir){
            int[][] temp = vetor.clone();
            if(dir){
                temp[0] = vetor[3];
                temp[1] = vetor[0];
                temp[2] = vetor[1];
                temp[3] = vetor[2];
            }else{
                temp[0] = vetor[1];
                temp[1] = vetor[2];
                temp[2] = vetor[3];
                temp[3] = vetor[0];
            }
            return temp;
        }
        private static void preenche() {
            for (int i = 0; i < FA; i++) {
                for (int j = 0; j < TA; j++) {
                    for (int k = 0; k < TA; k++) {
                        cubo[i][j][k] = i;
                    }
                }
            }
        }
        private static void print() {
            for (int i = 0; i < FA; i++) {
                for (int j = 0; j < TA; j++) {
                    for (int k = 0; k < TA; k++) {
                        System.out.print(cubo[i][j][k] + " ");
                    }
                    System.out.println();
                }
                System.out.println();
            }
        }
        private static int[][]  setValor(int[][] a, int[][] b){
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    a[i][j] = b[i][j];
                }
            }
            return a;
        }
    }
}
