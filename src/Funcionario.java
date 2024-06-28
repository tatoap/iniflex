import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Comparator;

public class Funcionario extends Pessoa implements Comparable<Funcionario> {

    private BigDecimal salario;

    private String funcao;

    public Funcionario() {

    }

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public BigDecimal calcularReajuste(BigDecimal porcentagem) {
        return getSalario().add(getSalario().multiply(porcentagem.divide(new BigDecimal("100"), 2, RoundingMode.HALF_EVEN)));
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome = " + super.getNome() +
                ", data de nascimento = " + super.getDataNascimento() +
                ", salario = " + salario +
                ", funcao = '" + funcao + '\'' +
                '}';
    }

    @Override
    public int compareTo(Funcionario f) {
        return getNome().compareTo(f.getNome());
    }
}
