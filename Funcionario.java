import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void aumentarSalario(BigDecimal percentual) {
        BigDecimal divisor = new BigDecimal("100");
        this.salario = this.salario.add(this.salario.multiply(percentual.divide(divisor)));
    }

    public String formatarSalario() {
        NumberFormat formatoBR = NumberFormat.getInstance(new Locale("pt", "BR"));
        formatoBR.setMinimumFractionDigits(2);
        formatoBR.setMaximumFractionDigits(2);
        return formatoBR.format(salario);
    }

    public String formatarDataNascimento() {
        return dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String toString() {
        return String.format("Nome: %s | Data Nascimento: %s | Salário: %s | Função: %s",
                nome, formatarDataNascimento(), formatarSalario(), funcao);
    }
}