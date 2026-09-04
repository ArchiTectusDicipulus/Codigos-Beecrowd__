package java__.nivel7.p1163;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        s.useLocale(Locale.US);
        java.util.Locale.setDefault(java.util.Locale.US);
        gerente(s);
        s.close();
    }
    private static void gerente(Scanner s){
        do{
            leituraDeDados(s);
            //System.out.println(Contexto.dados());
            retornaResultados(Contexto.getDisparos(), Contexto.getVetorLocalizacao(), Contexto.getAltura(),
                    BigDecimal.valueOf(Contexto.gravidade));
        }while(s.hasNextBigDecimal());
    }
    private static void leituraDeDados(Scanner s){
        Contexto.setAltura(s.nextBigDecimal());
        Contexto.setVetorLocalizacao(new BigDecimal[]{
                Contexto.converte(s.nextInt()),
                Contexto.converte(s.nextInt())
        });
        int numerotentativas  = s.nextInt();
        Contexto.getDisparos().clear();
        for(int i=0; i<numerotentativas; i++){
            Contexto.put(s.nextDouble(), s.nextDouble());
        }
    }
    private static void retornaResultados(Map<Double, BigDecimal> disparos, BigDecimal[] localizacao, BigDecimal altura, BigDecimal gravidade){
        for(Map.Entry<Double, BigDecimal> disparo: disparos.entrySet()){
            System.out.println(calculaResultados(disparo.getKey(), disparo.getValue(), localizacao, altura, gravidade));
        }
    }
    private static BigDecimal retornaPontoDeColisaoRaizX( BigDecimal inicio, double angulo, BigDecimal velocidade, BigDecimal aceleracao){
        BigDecimal velocidadeY = getYvelocidade(converteAnguloRadianos(angulo), velocidade);
        BigDecimal velocidadeX = getXvelocidade(converteAnguloRadianos(angulo), velocidade);
        aceleracao = aceleracao.abs().multiply(BigDecimal.valueOf(-1));
        BigDecimal delta = //Math.sqrt(Math.pow(velocidadeY, 2) - 2 * inicio * aceleracao);
                velocidadeY.pow(2).
                subtract(
                    inicio.
                    multiply(aceleracao).
                    multiply(BigDecimal.TWO)).
                sqrt(MathContext.DECIMAL128);
        BigDecimal tempo1 = velocidadeY.negate().add(delta).divide(aceleracao, MathContext.DECIMAL128);
                // (-velocidadeY + delta)/aceleracao;
        BigDecimal tempo2 = velocidadeY.negate().subtract(delta).divide(aceleracao, MathContext.DECIMAL128);
                // (-velocidadeY - delta)/aceleracao;
        tempo1 = tempo1.max(tempo2);
        //System.out.println("Tempo: "+tempo1);
        return formulaPosicao(BigDecimal.ZERO, velocidadeX, tempo1, BigDecimal.ZERO);
    }
    private static BigDecimal formulaPosicao(BigDecimal inicio, BigDecimal velocidade, BigDecimal tempo, BigDecimal aceleracao){
        return inicio.add( //inicio + velocidade * tempo + aceleracao / 2 * Math.pow(tempo, 2);
                    velocidade.multiply(tempo)
                ).
                add(
                    aceleracao.divide(
                        BigDecimal.TWO, MathContext.DECIMAL128)
                        .multiply(tempo.pow(2)));
    }
    private static double converteAnguloRadianos(double angulo){
        return angulo * Contexto.pi / 180;
    }
    private static BigDecimal getYvelocidade(double angulo, BigDecimal velocidade){
        return velocidade.multiply(BigDecimal.valueOf(Math.sin(angulo)));
    }
    private static BigDecimal getXvelocidade(double angulo, BigDecimal velocidade){
        return velocidade.multiply(BigDecimal.valueOf(Math.cos(angulo)));
    }
    private static String calculaResultados(double angulo, BigDecimal velocidade, BigDecimal[] localizacao, BigDecimal altura, BigDecimal gravidade){
        BigDecimal x0 = retornaPontoDeColisaoRaizX( altura, angulo, velocidade, gravidade);
        String resultado = String.format("%.5f", x0) + " -> ";
        //if(x0 >= localizacao[0] && x0 <= localizacao[1]){
        if(x0.compareTo(localizacao[0])>=0 && x0.compareTo(localizacao[1])<=0){
            resultado+="DUCK";
        }else{
            resultado+="NUCK";
        }
        return resultado;
    }
    private static class Contexto{
        private static BigDecimal altura;
        private static BigDecimal[] vetorLocalizacao;
        private static Map<Double, BigDecimal> disparos = new LinkedHashMap <>();
        public Contexto(){}
        private static final double gravidade = 9.80665;
        private static final double pi = 3.14159;
        public static void put(double a, double b){
            disparos.put(a, converte(b));
        }
        public static BigDecimal converte(double x){
            return BigDecimal.valueOf(x);
        }
        public static BigDecimal converte(int x){
            return BigDecimal.valueOf(x);
        }
        public static BigDecimal getAltura() {
            return altura;
        }
        public static void setAltura(BigDecimal altura) {
            Contexto.altura = altura;
        }
        public static BigDecimal[] getVetorLocalizacao() {
            return vetorLocalizacao;
        }
        public static void setVetorLocalizacao(BigDecimal[] vetorLocalizacao) {
            Contexto.vetorLocalizacao = vetorLocalizacao;
        }
        public static Map<Double, BigDecimal> getDisparos() {
            return disparos;
        }
        public static void setDisparos(Map<Double, BigDecimal> disparos) {
            Contexto.disparos = disparos;
        }
        public static String dados() {
            return "Contexto{}" +" "+ altura +" "+ Arrays.toString(vetorLocalizacao) +" "+ disparos;
        }
    }
}

