import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

// Reconhece o aninhamento correto de div, p e span. 
public class PDA {
    private final Deque<String> pilha = new ArrayDeque<>();

    public void validar(Yylex lexer) throws IOException, ValidacaoException {
        pilha.clear();
        Token token;

        while ((token = lexer.yylex()) != null) {
            if (token == Token.INVALIDO) {
                throw new ValidacaoException(
                    "Simbolo ou tag nao reconhecida: '" + lexer.textoAtual() + "'" +
                    " (linha " + lexer.linhaAtual() + ", coluna " + lexer.colunaAtual() + ")."
                );
            }

            if (token.isAbertura()) {
                pilha.push(token.getTag());
            } else {
                if (pilha.isEmpty()) {
                    throw new ValidacaoException("Fechamento " + token +
                        " sem tag de abertura correspondente (linha " + lexer.linhaAtual() + ").");
                }

                String topo = pilha.peek();
                if (!topo.equals(token.getTag())) {
                    throw new ValidacaoException("Encontrado " + token +
                        ", mas era esperado </" + topo + "> (linha " + lexer.linhaAtual() + ").");
                }
                pilha.pop();
            }
        }

        if (!pilha.isEmpty()) {
            throw new ValidacaoException("Fim da entrada com tag(s) ainda aberta(s). " +
                "Ultima tag aberta sem fechamento: <" + pilha.peek() + ">.");
        }
    }

    public static class ValidacaoException extends Exception {
        public ValidacaoException(String mensagem) {
            super(mensagem);
        }
    }
}
