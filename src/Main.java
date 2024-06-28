import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Funcionario> listaFuncionarios = montarListaFuncionarios();

        exibirListaFuncionarios(listaFuncionarios);

        System.out.println("\n**************************************************************************************************\n");
        System.out.println("Removendo o João da lista");

        listaFuncionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));

        System.out.println("\n**************************************************************************************************\n");
        System.out.println("Reajustando os salários em 10%\n");

        listaFuncionarios = listaFuncionarios.stream()
                .peek(f -> f.setSalario(f.calcularReajuste(BigDecimal.TEN)))
                .collect(Collectors.toList());

        exibirListaFuncionarios(listaFuncionarios);

        Map<String, List<Funcionario>> funcionariosPorFuncao = listaFuncionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        funcionariosPorFuncao.forEach((funcao, funcionarios) -> {
            System.out.printf("\n%s%n", funcao);
            System.out.println("--------------------------------------------------------------------------");
            exibirListaFuncionarios(funcionarios);
        });

        System.out.println("\n**************************************************************************************************\n");
        System.out.println("Listando os nascidos em Outubro e Dezembro\n");

        listaFuncionarios.stream()
                .filter(f -> f.getDataNascimento().getMonth().equals(Month.DECEMBER) || f.getDataNascimento().getMonth().equals(Month.OCTOBER))
                .forEach(Main::exibirFuncionario);

        System.out.println("\n**************************************************************************************************\n");
        System.out.println("Exibindo o funcionário mais velho\n");

        Funcionario funcionarioMaisVelho = listaFuncionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElseThrow(RuntimeException::new);

        int idade = Period.between(funcionarioMaisVelho.getDataNascimento(), LocalDate.now()).getYears();

        System.out.printf("Nome: %s - Idade: %d anos", funcionarioMaisVelho.getNome(), idade);

        System.out.println("\n**************************************************************************************************\n");
        System.out.println("Exibindo a lista ordenada por nome\n");

        Collections.sort(listaFuncionarios);

        exibirListaFuncionarios(listaFuncionarios);

        System.out.println("\n**************************************************************************************************\n");
        System.out.println("Exibindo a total dos salários dos funcionários\n");

        BigDecimal totalSalarios = listaFuncionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        DecimalFormatSymbols simbolos = new DecimalFormatSymbols(new Locale("pt", "BR"));
        NumberFormat formatadorNumeros = new DecimalFormat("¤ #,##0.00;(¤ #,##0.00)", simbolos);

        System.out.println(formatadorNumeros.format(totalSalarios));

        System.out.println("\n**************************************************************************************************\n");
        System.out.println("Exibindo o salário de cada funcionário por cálculo de salário mínimo\n");

        listaFuncionarios.forEach(f -> {
            BigDecimal valor = f.getSalario().divide(new BigDecimal("1212.00"), 2, RoundingMode.UP);
            System.out.printf("Funcionário %s recebe %s salários mínimos%n", f.getNome(), valor);
        });
    }

    private static List<Funcionario> montarListaFuncionarios() {
        Funcionario funcionario1 = new Funcionario("Maria", LocalDate.of(2000, Month.OCTOBER, 18), new BigDecimal("2009.44"), "Operador");
        Funcionario funcionario2 = new Funcionario("João", LocalDate.of(1990, Month.MAY, 12), new BigDecimal("2284.38"), "Operador");
        Funcionario funcionario3 = new Funcionario("Caio", LocalDate.of(1961, Month.MAY, 2), new BigDecimal("9836.14"), "Coordenador");
        Funcionario funcionario4 = new Funcionario("Miguel", LocalDate.of(1988, Month.OCTOBER, 14), new BigDecimal("19119.88"), "Diretor");
        Funcionario funcionario5 = new Funcionario("Alice", LocalDate.of(1995, Month.JANUARY, 5), new BigDecimal("2234.68"), "Recepcionista");
        Funcionario funcionario6 = new Funcionario("Heitor", LocalDate.of(1999, Month.NOVEMBER, 19), new BigDecimal("1582.72"), "Operador");
        Funcionario funcionario7 = new Funcionario("Arthur", LocalDate.of(1993, Month.MARCH, 31), new BigDecimal("4071.84"), "Contador");
        Funcionario funcionario8 = new Funcionario("Laura", LocalDate.of(1994, Month.JULY, 8), new BigDecimal("3017.45"), "Gerente");
        Funcionario funcionario9 = new Funcionario("Heloísa", LocalDate.of(2003, Month.MAY, 24), new BigDecimal("1606.85"), "Eletricista");
        Funcionario funcionario10 = new Funcionario("Helena", LocalDate.of(1996, Month.NOVEMBER, 2), new BigDecimal("2799.93"), "Gerente");

        Object[] array = {funcionario1, funcionario2, funcionario3, funcionario4, funcionario5, funcionario6, funcionario7, funcionario8, funcionario9, funcionario10};

        List<Funcionario> listaFuncionarios = new ArrayList<>();

        for (Object func : array) {
            listaFuncionarios.add((Funcionario) func);
        }

        return listaFuncionarios;
    }

    private static void exibirFuncionario(Funcionario funcionario) {
        DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        DecimalFormatSymbols simbolos = new DecimalFormatSymbols(new Locale("pt", "BR"));
        NumberFormat formatadorNumeros = new DecimalFormat("¤ #,##0.00;(¤ #,##0.00)", simbolos);

        System.out.printf("Nome: %s, Data de nascimento: %s, Salário: %s, Função: %s%n",
                funcionario.getNome(), funcionario.getDataNascimento().format(formatadorData),
                formatadorNumeros.format(funcionario.getSalario()), funcionario.getFuncao());
    }

    private static void exibirListaFuncionarios(List<Funcionario> funcionarios) {
        funcionarios.forEach(Main::exibirFuncionario);
    }
}