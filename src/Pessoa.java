import java.time.LocalDate;
import java.util.Objects;

public class Pessoa {

    private String nome;

    private LocalDate dataNascimento;

    public Pessoa() {

    }

    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome = '" + nome + '\'' +
                ", dataNascimento = " + dataNascimento +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(dataNascimento, pessoa.dataNascimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataNascimento);
    }
}
