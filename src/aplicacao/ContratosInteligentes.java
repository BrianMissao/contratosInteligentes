package aplicacao;

import entidades.Contrato;
import entidades.Trabalhador;
import enums.Nivel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class ContratosInteligentes {
    private static Trabalhador trabalhador;
    private static SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
    private static Scanner scanner = new Scanner(System.in);
    private static String periodoACalcular;

    public static void main(String[] args) throws ParseException {
        String departamento = obterDados("Digite o departamento:");
        String nome = obterDados("Entre com os dados do trabalhador:\nNome:");
        String nivel = obterDados("Nível (JUNIOR, PLENO ou SENIOR):");
        BigDecimal salarioBase = new BigDecimal(obterDados("Salário base:"));
        trabalhador = new Trabalhador(departamento, nome, Nivel.valueOf(nivel), salarioBase);
        int quantidadeDeContratosASeremAdicionados = Integer.parseInt(obterDados("Quantos contratos serão adicionados a este trabalhador?"));
        adicionarContratosAoTrabalhador(quantidadeDeContratosASeremAdicionados);
        periodoACalcular = obterDados("Calculando renda\nDigite o período a calcular (mês/ano):");
        imprimirInformacoesDoTrabalhador();
    }

    private static void imprimirInformacoesDoTrabalhador() {
        System.out.printf("Nome: %s%nDepartamento: %s%nRenda para o período %s: %s", trabalhador.getNome(), trabalhador.getDepartamento(), periodoACalcular, calcularRendaParaUmPeriodo(periodoACalcular));
    }

    private static BigDecimal calcularRendaParaUmPeriodo(String mesEAnoACalcular) {
        BigDecimal renda = trabalhador.getSalarioBase();
        Integer mesACalcular = Integer.parseInt(mesEAnoACalcular.substring(0, 2));
        Integer anoACalcular = Integer.parseInt(mesEAnoACalcular.substring(3));
        Calendar calendar = Calendar.getInstance();
        for (Contrato contrato : trabalhador.getContratos()) {
            calendar.setTime(contrato.getData());
            Integer mesDoContrato = calendar.get(Calendar.MONTH) + 1;
            Integer anoDoContrato = calendar.get(Calendar.YEAR);
            if (mesACalcular.equals(mesDoContrato) && anoACalcular.equals(anoDoContrato)) {
                BigDecimal rendaPorContrato = new CalculaValorTotalDoContrato(contrato).calcular();
                renda = renda.add(rendaPorContrato);
            }
        }
        return renda;
    }

    private static void adicionarContratosAoTrabalhador(int quantidadeDeContratosASeremAdicionados) throws ParseException {
        for (int i = 1; i <= quantidadeDeContratosASeremAdicionados; i++) {
            System.out.println("Entre com os dados do contrato de número " + i + ":");
            Date data = dataFormatada.parse(obterDados("Digite a data (dia/mês/ano):"));
            BigDecimal valorPorHora = new BigDecimal(obterDados("Valor por hora:")).setScale(3, RoundingMode.HALF_EVEN);
            Integer duracaoEmHoras = Integer.parseInt(obterDados("Duração (em horas) deste contrato:"));
            trabalhador.adicionarContrato(new Contrato(data, valorPorHora, duracaoEmHoras));
            System.out.println("O contrato de número " + i + " foi adicionado com sucesso!");
        }
    }

    private static String obterDados(String mensagemAoUsuario) {
        System.out.println(mensagemAoUsuario);
        return scanner.nextLine();
    }
}
