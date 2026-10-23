%%
%public
%class Yylex
%unicode
%type Token
%line
%column

%{
    public int linhaAtual() {
        return yyline + 1;
    }

    public int colunaAtual() {
        return yycolumn + 1;
    }

    public String textoAtual() {
        return yytext();
    }
%}

Espacos = [ \t\r\n\f]+

%%

"<div>"       { return Token.ABRE_DIV; }
"</div>"      { return Token.FECHA_DIV; }
"<p>"         { return Token.ABRE_P; }
"</p>"        { return Token.FECHA_P; }
"<span>"      { return Token.ABRE_SPAN; }
"</span>"     { return Token.FECHA_SPAN; }

{Espacos}     {  }

[^]           { return Token.INVALIDO; }
