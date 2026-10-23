# Validador de Autômato com Pilha — HTML

Projeto desenvolvido para a disciplina de **Aspectos Teóricos da Computação**, com o objetivo de realizar a análise léxica e validar o aninhamento de tags HTML por meio de um autômato com pilha (PDA). O projeto utiliza o repositório base disponibilizado pelo professor como auxilio para a análise léxica.


## Funcionamento

1. O **JFlex** identifica os tokens de abertura e fechamento das tags.
2. O programa exibe os tokens encontrados na entrada.
3. O **PDA** utiliza uma pilha para verificar se as tags são fechadas na ordem correta.
4. Ao final, informa se a cadeia foi **ACEITA** ou **REJEITADA**.

A linguagem simplificada reconhece **3 tags: `<div>`, `<p>` e `<span>`**, sem atributos ou conteúdo textual. Espaços e quebras de linha entre as tags são permitidos.

## Arquivos principais

| Arquivo | Descrição |
|---|---|
| `HTML.flex` | Regras léxicas para reconhecer as tags HTML. |
| `Token.java` | Tipos de tokens reconhecidos. |
| `PDA.java` | Validação do aninhamento usando uma pilha. |
| `Main.java` | Exibição dos tokens e do resultado da validação. |
| `input.html` e outros `.html`/`.txt` | Exemplos de entrada. |
| `run.bat` | Menu de seleção de entrada, geração, compilação e execução. |

O arquivo `Yylex.java` é gerado automaticamente pelo JFlex.

## Como executar

**Requisitos:** JDK instalado e disponível no terminal (`java` e `javac`), Windows e o arquivo `jflex-full-1.9.1.jar` na pasta raiz do repositório.

1. Abra a pasta `PDA-HTML`.
2. Execute `run.bat`.
3. Escolha o número correspondente ao arquivo de entrada.
4. Veja a lista de tokens e o resultado da validação no terminal.

```powershell
cd PDA-HTML
./run.bat
```

## Linguagem aceita

- Tags permitidas: `<div>`, `</div>`, `<p>`, `</p>`, `<span>`, `</span>`.
- Tags devem estar corretamente aninhadas; sequencias consecutivas sao permitidas.
- Espacos e quebras de linha **entre tags** sao ignorados.
- Texto, atributos, outras tags e tags auto-fechadas **nao sao aceitos**.
- Entrada vazia (ou so espacos) e aceita, conforme a linguagem definida.

## Exemplos

**Cadeia aceita:**

```html
<div>
    <p><span></span></p>
</div>
```

**Cadeia rejeitada:**

```html
<div>
    <p></div>
</p>
```
