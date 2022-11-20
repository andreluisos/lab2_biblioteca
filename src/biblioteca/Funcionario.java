package biblioteca;

public class Funcionario {
    private int matricula;
    private String nome;
    private String end;
    private String dataIngresso;
    private String setor;
    private String login;
    private String senha;

    public Funcionario(int matricula, String nome, String end, String dataIngresso, String setor, String login,
            String senha) {
        this.matricula = matricula;
        this.nome = nome;
        this.end = end;
        this.dataIngresso = dataIngresso;
        this.setor = setor;
        this.login = login;
        this.senha = senha;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return ";" + matricula
                + ";" + nome
                + ";" + end
                + ";" + dataIngresso
                + ";" + setor
                + ";" + login
                + ";" + senha;
    }

}
