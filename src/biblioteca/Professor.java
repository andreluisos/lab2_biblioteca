package biblioteca;

public class Professor {
    private int matricula;
    private String nome;
    private String end;
    private String dataIngresso;
    private String setor;

    public Professor(int matricula, String nome, String end, String dataIngresso, String setor) {
        this.matricula = matricula;
        this.nome = nome;
        this.end = end;
        this.dataIngresso = dataIngresso;
        this.setor = setor;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDataIngresso() {
        return dataIngresso;
    }

    public void setDataIngresso(String dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String toDatabase() {
        return matricula
                + ";" + nome
                + ";" + end
                + ";" + dataIngresso
                + ";" + setor;
    }

    @Override
    public String toString() {
        return matricula
                + ";" + nome
                + ";" + end
                + ";" + dataIngresso
                + ";" + setor;
    }

}
