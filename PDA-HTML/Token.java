public enum Token {
    ABRE_DIV("<div>", "div", true),
    FECHA_DIV("</div>", "div", false),
    ABRE_P("<p>", "p", true),
    FECHA_P("</p>", "p", false),
    ABRE_SPAN("<span>", "span", true),
    FECHA_SPAN("</span>", "span", false),
    INVALIDO("token invalido", "", false);

    private final String representacao;
    private final String tag;
    private final boolean abertura;

    Token(String representacao, String tag, boolean abertura) {
        this.representacao = representacao;
        this.tag = tag;
        this.abertura = abertura;
    }

    public String getTag() {
        return tag;
    }

    public boolean isAbertura() {
        return abertura;
    }

    @Override
    public String toString() {
        return representacao;
    }
}
