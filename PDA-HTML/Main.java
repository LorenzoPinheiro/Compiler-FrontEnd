import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {
    public static void main(String[] args) {
        String arquivo = args.length > 0 ? args[0] : "input.html";

        System.out.println("=== VALIDADOR DE TAGS HTML COM PDA ===");
        System.out.println("Arquivo: " + arquivo);
        System.out.println();

        // Etapa 1: analise lexica e exibicao dos tokens na ordem encontrada.
        try (Reader leitor = new FileReader(arquivo)) {
            Yylex lexer = new Yylex(leitor);
            Token token;
            int quantidade = 0;

            System.out.println("=== ANALISE LEXICA: TOKENS IDENTIFICADOS ===");
            System.out.printf("%-7s %-13s %-12s %s%n", "N.", "LEXEMA", "TOKEN", "POSICAO");

            while ((token = lexer.yylex()) != null) {
                quantidade++;
                System.out.printf("%-7d %-13s %-12s linha %d, coluna %d%n",
                    quantidade,
                    lexer.textoAtual(),
                    token.name(),
                    lexer.linhaAtual(),
                    lexer.colunaAtual());
            }

            System.out.println("Total de tokens: " + quantidade);
            System.out.println();

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            System.exit(1);
        }

        // Etapa 2: nova leitura do arquivo para validacao do aninhamento pelo PDA!!
        System.out.println("=== VALIDACAO DA CADEIA ===");
        try (Reader leitor = new FileReader(arquivo)) {
            Yylex lexer = new Yylex(leitor);
            PDA automato = new PDA();
            automato.validar(lexer);
            System.out.println("Resultado: CADEIA ACEITA");
        } catch (PDA.ValidacaoException e) {
            System.out.println("Resultado: CADEIA REJEITADA");
            System.out.println("Motivo: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
            System.exit(1);
        }
    }
}
