package biblioteca;

public class ItemEmprestimo {
    private int codigoItem;
    private int codigoEmprestimo;
    private int codigoLivro;
    private int codigoPeriodico;
    private String dataDevolucao;

    public ItemEmprestimo(int codigoItem, int codigoEmprestimo, int codigoLivro, int codigoPeriodico,
            String dataDevolucao) {
        this.codigoItem = codigoItem;
        this.codigoEmprestimo = codigoEmprestimo;
        this.codigoLivro = codigoLivro;
        this.codigoPeriodico = codigoPeriodico;
        this.dataDevolucao = dataDevolucao;
    }

    public int getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(int codigoItem) {
        this.codigoItem = codigoItem;
    }

    public int getCodigoEmprestimo() {
        return codigoEmprestimo;
    }

    public void setCodigoEmprestimo(int codigoEmprestimo) {
        this.codigoEmprestimo = codigoEmprestimo;
    }

    public int getCodigoLivro() {
        return codigoLivro;
    }

    public void setCodigoLivro(int codigoLivro) {
        this.codigoLivro = codigoLivro;
    }

    public int getCodigoPeriodico() {
        return codigoPeriodico;
    }

    public void setCodigoPeriodico(int codigoPeriodico) {
        this.codigoPeriodico = codigoPeriodico;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String toDatabase() {
        return codigoItem
                + ";" + codigoEmprestimo
                + ";" + codigoLivro
                + ";" + codigoPeriodico
                + ";" + dataDevolucao;
    }
    
}
