import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Atleta {
    public int numInscricao;
    public double movimentosObrigatorios, complexidadeAcrobacias, exatidaoMovimentos, pontuacaoTotal;
    
    Atleta (int numInscricao, double movimentosObrigatorios, double complexidadeAcrobacias, double exatidaoMovimentos, double pontuacaoTotal){
        this.numInscricao = numInscricao;
        this.movimentosObrigatorios = movimentosObrigatorios;
        this.complexidadeAcrobacias = complexidadeAcrobacias;
        this.exatidaoMovimentos = exatidaoMovimentos;
        this.pontuacaoTotal = pontuacaoTotal;
    }

    boolean estaEliminado() {
        return movimentosObrigatorios == 0 || complexidadeAcrobacias == 0 || exatidaoMovimentos == 0;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner leia = new Scanner(System.in);
        List<Atleta> atletas = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int numInscricao = lerNumInscricao(leia);
            double movimentosObrigatorios = lerPontuacao(leia, "Totalidade dos movimentos obrigatórios", 0, 2.5);
            double complexidadeAcrobacias = lerPontuacao(leia, "Complexidade das acobracias", 0.9, 9);
            double exatidaoMovimentos = lerPontuacao(leia, "Exatidão dos movimentos e acrobacias", 0, 10);

            Atleta atleta = new Atleta(numInscricao, movimentosObrigatorios, complexidadeAcrobacias, exatidaoMovimentos, exatidaoMovimentos);
            atletas.add(atleta);
        }

        informarVencedor(atletas);
    }

    private static int lerNumInscricao(Scanner leia){
        while (true) {
            System.out.print("Número de Inscrição do Atleta: ");
            int numInscricao = leia.nextInt();

            if (numInscricao >= 101 && numInscricao <= 200) {
                return numInscricao;
            } else {
                System.out.println("Númeração inválida! Escreva um valor entre 101 - 200.");
            }
        }
    }

    private static double lerPontuacao(Scanner leia, String tipoPontuacao, double minimo, double maximo){
        while (true) {
            System.out.print(tipoPontuacao + ": ");
            double pontuacao = leia.nextDouble();


            if (pontuacao >= minimo && pontuacao <=maximo) {
                return pontuacao;
            } else {
                System.out.println("Pontuação inválida! A nota deve ser entre " + minimo + " e " + maximo + ".");
            }
        }
    }

    private static void informarVencedor(List<Atleta> atletas) {
        List<Atleta> naoEliminados = new ArrayList<>();
        int eliminados = 0;

        for (Atleta atleta : atletas) {
            if (!atleta.estaEliminado()) {
                naoEliminados.add(atleta);
            } else {
                eliminados++;
            }
        }

        naoEliminados.sort(Comparator.comparingDouble((Atleta a) -> a.pontuacaoTotal)
        .thenComparingDouble(a -> a.complexidadeAcrobacias)
        .thenComparingDouble(a -> a.exatidaoMovimentos)
        .thenComparingDouble(a -> a.movimentosObrigatorios)
        .thenComparingDouble(a -> a.numInscricao)
        .reversed());

        if (!naoEliminados.isEmpty()) {
            Atleta vencedor = naoEliminados.get(0);
            System.out.println("O nº de inscrição do primeiro colocado é: " + vencedor.numInscricao + ", e a sua pontuação total é: " + vencedor.pontuacaoTotal);
        } else {
            System.out.println("Não houve atletas selecionados.");
        }

        double percentualEliminados = (eliminados / (double) atletas.size()) * 100;
        System.out.println("Quantidade de atletas eliminados: " + eliminados);
        System.out.println("Percentual de atletas eliminados: " + percentualEliminados + "%");
    }
}
