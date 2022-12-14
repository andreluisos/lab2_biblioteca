package biblioteca;

public class Emprestimo {
    private int codigo;
    private int matriculaCliente;
    private int matriculaFuncionario;
    private String dataEmprestimo;
    private String dataDevolucao;

    public Emprestimo(int codigo, int matriculaCliente, int matriculaFuncionario, String dataEmprestimo,
            String dataDevolucao) {
        this.codigo = codigo;
        this.matriculaCliente = matriculaCliente;
        this.matriculaFuncionario = matriculaFuncionario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getMatriculaCliente() {
        return matriculaCliente;
    }

    public void setMatriculaCliente(int matriculaCliente) {
        this.matriculaCliente = matriculaCliente;
    }

    public int getMatriculaFuncionario() {
        return matriculaFuncionario;
    }

    public void setMatriculaFuncionario(int matriculaFuncionario) {
        this.matriculaFuncionario = matriculaFuncionario;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String toDatabase() {
        return codigo
                + ";" + matriculaCliente
                + ";" + matriculaFuncionario
                + ";" + dataEmprestimo
                + ";" + dataDevolucao;
    }

    @Override
    public String toString() {
        return "Emprestimo [codigo=" + codigo + ", matriculaCliente=" + matriculaCliente + ", matriculaFuncionario="
                + matriculaFuncionario + ", dataEmprestimo=" + dataEmprestimo + ", dataDevolucao=" + dataDevolucao
                + "]";
    }

}
