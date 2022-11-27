package biblioteca;

public class Aluno {
    private int matricula;
    private String nome;
    private String end;
    private String curso;
    private String dataIngresso;
    private double multa;

    public Aluno(int matricula, String nome, String end, String curso, String dataIngresso, double multa) {
        this.matricula = matricula;
        this.nome = nome;
        this.end = end;
        this.curso = curso;
        this.dataIngresso = dataIngresso;
        this.multa = multa;
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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getDataIngresso() {
        return dataIngresso;
    }

    public void setDataIngresso(String dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public String toDatabase() {
        return matricula
                + ";" + nome
                + ";" + end
                + ";" + curso
                + ";" + dataIngresso
                + ";" + multa;
    }

    @Override
    public String toString() {
        return "Aluno [matricula=" + matricula + ", nome=" + nome + ", end=" + end + ", curso=" + curso
                + ", dataIngresso=" + dataIngresso + ", multa=" + multa + "]";
    }

}
