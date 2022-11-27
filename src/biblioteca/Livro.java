package biblioteca;

public class Livro {
    private int codigo;
    private String autores;
    private String titulo;
    private String editora;
    private char tipo;
    private int ano;
    private int issn;

    public Livro(int codigo, String autores, String titulo, String editora, char tipo, int ano, int issn) {
        this.codigo = codigo;
        this.autores = autores;
        this.titulo = titulo;
        this.editora = editora;
        this.tipo = tipo;
        this.ano = ano;
        this.issn = issn;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getIssn() {
        return issn;
    }

    public void setIssn(int issn) {
        this.issn = issn;
    }

    public String toDatabase() {
        return codigo
                + ";" + autores
                + ";" + titulo
                + ";" + editora
                + ";" + tipo
                + ";" + ano
                + ";" + issn;
    }

    @Override
    public String toString() {
        return "Livro [codigo=" + codigo + ", autores=" + autores + ", titulo=" + titulo + ", editora=" + editora
                + ", tipo=" + tipo + ", ano=" + ano + ", issn=" + issn + "]";
    }

}
