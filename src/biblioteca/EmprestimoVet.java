package biblioteca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmprestimoVet {
    private ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
    Scanner in = new Scanner(System.in);

    public void ler(List<String> linhas) {
        int numLinhas = linhas.size();
        String linha, valorComSplit[];

        for (int i = 0; i < numLinhas; i++) {
            linha = linhas.get(i);
            if (!linha.isEmpty()) {
                valorComSplit = linha.split(";");
                this.emprestimos.add(
                        new Emprestimo(
                                Integer.parseInt(valorComSplit[0]),
                                Integer.parseInt(valorComSplit[1]),
                                Integer.parseInt(valorComSplit[2]),
                                valorComSplit[3],
                                valorComSplit[4]));
            }
        }
    }

    public void cadastrar(int funcionario) throws IOException {
        int codigo;
        int matriculaCliente;
        int matriculaFuncionario = funcionario;
        String dataEmprestimo;
        String dataDevolucao;
        String dbString = "";

        System.out.println("Digite código do empréstimo:");
        codigo = in.nextInt();

        System.out.println("Digite a matrícula do cliente:");
        matriculaCliente = in.nextInt();

        System.out.println("Digite a data do empréstimo:");
        in.nextLine();
        dataEmprestimo = in.nextLine();

        System.out.println("Digite a data da devolução:");
        dataDevolucao = in.nextLine();
        
        this.emprestimos.add(new Emprestimo(
                codigo,
                matriculaCliente,
                matriculaFuncionario,
                dataEmprestimo,
                dataDevolucao));

        for (int i = 0; i < this.emprestimos.size(); i++) {
            dbString += this.emprestimos.get(i).toDatabase() + "\n";
        }

        ManipulaArquivo.escritor("./emprestimos.csv", dbString);

        System.out.println("Empréstimo cadastrado com sucesso!");
    }

    public ArrayList<Emprestimo> getEmprestimos() {
        return this.emprestimos;
    }
}
