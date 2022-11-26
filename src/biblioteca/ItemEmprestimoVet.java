package biblioteca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ItemEmprestimoVet {
    private ArrayList<ItemEmprestimo> itemEmprestimos = new ArrayList<ItemEmprestimo>();
    Scanner in = new Scanner(System.in);

    public void ler(List<String> linhas) {
        int numLinhas = linhas.size();
        String linha, valorComSplit[];

        for (int i = 0; i < numLinhas; i++) {
            linha = linhas.get(i);
            if (!linha.isEmpty()) {
                valorComSplit = linha.split(";");
                this.itemEmprestimos.add(
                        new ItemEmprestimo(
                                Integer.parseInt(valorComSplit[0]),
                                Integer.parseInt(valorComSplit[1]),
                                Integer.parseInt(valorComSplit[2]),
                                Integer.parseInt(valorComSplit[3]),
                                valorComSplit[4]));
            }
        }
    }

    public void cadastrar(
            List<Livro> livros, 
            List<Periodico> periodicos, 
            List<Emprestimo> emprestimos
    ) throws IOException {
        int codigoItem;
        int codigoEmprestimo = emprestimos.size() - 1;
        int codigoLivro;
        int codigoPeriodico;
        String dataDevolucao = emprestimos.get(emprestimos.size() - 1).getDataDevolucao();

        if (this.itemEmprestimos.size() == 0) {
            codigoItem = 0;
        } else {
            codigoItem = this.itemEmprestimos.get(this.itemEmprestimos.size() - 1).getCodigoItem() + 1;
        }

        while (true) {
            System.out.println("Escolha o tipo item a ser emprestado:\n1- Livro\n2- Periódico");
            if (in.nextInt() == 1) {
                for (int i = 0; i < livros.size(); i++) {
                    System.out.println(livros.get(i).toString());
                }
                System.out.print("Escolha o código do livro a ser emprestado: ");
                codigoLivro = in.nextInt();
                codigoPeriodico = -1;
                break;
            } else {
                for (int i = 0; i < periodicos.size(); i++) {
                    System.out.println(periodicos.get(i).toString());
                }
                System.out.print("Escolha o código do periódico a ser emprestado: ");
                codigoLivro = -1;
                codigoPeriodico = in.nextInt();
                break;
            }
        }

        this.itemEmprestimos.add(new ItemEmprestimo(codigoItem, codigoEmprestimo, codigoLivro, codigoPeriodico,
                dataDevolucao));

        this.saveToDB(itemEmprestimos);
    }
    
    public void saveToDB(ArrayList<ItemEmprestimo> itemEmprestimos) throws IOException {
        String dbString = "";
        for (int i = 0; i < this.itemEmprestimos.size(); i++) {
            dbString += this.itemEmprestimos.get(i).toDatabase() + "\n";
        }
        ManipulaArquivo.escritor("./itemEmprestimos.csv", dbString);
    }

    public void relatar() {
        for (int i = 0; i < this.itemEmprestimos.size(); i++) {
            System.out.println(this.itemEmprestimos.get(i).toString());
        }
    }

    public ArrayList<ItemEmprestimo> getItemEmprestimos() {
        return this.itemEmprestimos;
    }

    @Override
    public String toString() {
        return "ItemEmprestimoVet [itemEmprestimos=" + itemEmprestimos + ", in=" + in + "]";
    }
}
