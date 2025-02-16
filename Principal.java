import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1984, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloisa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // Removendo João
        funcionarios.remove(1);

        // Imprimindo funcionários
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }

        // Aumento de 10%
        for (Funcionario f : funcionarios) {
            f.aumentarSalario(new BigDecimal("10"));
        }

        // Agrupando por função
        Map<String, List<Funcionario>> agrupadosPorFuncao = new HashMap<>();

        for (Funcionario f : funcionarios) {
            String funcao = f.getFuncao();

            // Se a função ainda não existe no mapa, cria uma nova lista
            if (!agrupadosPorFuncao.containsKey(funcao)) {
                agrupadosPorFuncao.put(funcao, new ArrayList<>());
            }

            // Adiciona o funcionário à lista correspondente
            agrupadosPorFuncao.get(funcao).add(f);
        }

        // Imprime os funcionarios agrupados por função
        for (Map.Entry<String, List<Funcionario>> entry : agrupadosPorFuncao.entrySet()) {
            String funcao = entry.getKey();
            List<Funcionario> lista = entry.getValue();

            System.out.println("\nFunção: " + funcao);
            for (Funcionario funcionario : lista) {
                System.out.println(funcionario);
            }
        }

        // Funcionários que fazem aniversário nos meses 10 e 12
        System.out.println("\nAniversariantes de outubro e dezembro:");
        for (Funcionario f : funcionarios) {
            int mesNascimento = f.getDataNascimento().getMonthValue();
            if (mesNascimento == 10 || mesNascimento == 12) {
                System.out.println(f);
            }
        }

        // Funcionário mais velho
        Funcionario maisVelho = Collections.min(funcionarios, Comparator.comparing(Funcionario::getDataNascimento));
        System.out.println("\nMais velho: " + maisVelho.getNome() + " | Idade: " +
                (LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear()));

        // Ordenação alfabética
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        System.out.println("\nFuncionários ordenados alfabeticamente:");
        funcionarios.forEach(System.out::println);

        // Total dos salários
        BigDecimal totalSalarios = new BigDecimal("0");
        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }
        System.out.println("\nTotal dos salários: " + new Funcionario("", null, totalSalarios, "").formatarSalario());

// Quantos salários mínimos cada funcionário ganha
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        System.out.println("\nQuantidade de salários mínimos por funcionário:");
        for (Funcionario f : funcionarios) {
            BigDecimal salariosMinimos = f.getSalario().divide(salarioMinimo, 2);
            String salarioMinimoFormatado = String.format("%.2f", salariosMinimos.doubleValue());
            System.out.println(f.getNome() + " ganha " + salarioMinimoFormatado + " salários mínimos");
        }
    }
}