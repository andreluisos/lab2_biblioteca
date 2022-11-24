package biblioteca;

public class Periodico {
    private int codigo;
    private String autores;
    private String titulo;
    private char tipo;
    private int fatorImpacto;
    private int issn;

    public Periodico(int codigo, String autores, String titulo, char tipo, int fatorImpacto, int issn) {
        this.codigo = codigo;
        this.autores = autores;
        this.titulo = titulo;
        this.tipo = tipo;
        this.fatorImpacto = fatorImpacto;
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

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public int getFatorImpacto() {
        return fatorImpacto;
    }

    public void setFatorImpacto(int fatorImpacto) {
        this.fatorImpacto = fatorImpacto;
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
                + ";" + tipo
                + ";" + fatorImpacto
                + ";" + issn;
    }

    @Override
    public String toString() {
        return "Periodico [codigo=" + codigo + ", autores=" + autores + ", titulo=" + titulo + ", tipo=" + tipo
                + ", fatorImpacto=" + fatorImpacto + ", issn=" + issn + "]";
    }

}
