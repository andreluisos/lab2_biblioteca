package biblioteca;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EmprestimoVet {
    private ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
    Scanner in = new Scanner(System.in);
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

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

    public void cadastrar(int funcionario, List<Professor> professores, List<Aluno> alunos) throws IOException {
        int codigo;
        int matriculaCliente;
        int matriculaFuncionario = funcionario;
        String dataEmprestimo;
        String dataDevolucao;
        dataEmprestimo = dateFormat.format(new Date());
        
        if (this.emprestimos.size() == 0) {
            codigo = 0;
        } else {
            codigo = this.emprestimos.get(this.emprestimos.size() - 1).getCodigo() + 1;
        }

        System.out.println("Digite a matrícula do cliente:");
        if (in.hasNextInt()) {
            matriculaCliente = in.nextInt();
        } else {
            matriculaCliente = 0;
            System.out.println("Erro de IO");
            System.exit(0);
        }
        

        System.out.println("Digite a data da devolução:");
        in.nextLine();
        dataDevolucao = in.nextLine();
        try {
            dateFormat.format(dataDevolucao);
        } catch (Exception e) {
            System.out.println("Erro de IO");
            System.exit(0);
        }

        this.emprestimos.add(new Emprestimo(
                codigo,
                matriculaCliente,
                matriculaFuncionario,
                dataEmprestimo,
                dataDevolucao));

        this.saveToDB(emprestimos);
    }
    
    public void saveToDB(ArrayList<Emprestimo> emprestimos) throws IOException {
        String dbString = "";
        for (int i = 0; i < this.emprestimos.size(); i++) {
            dbString += this.emprestimos.get(i).toDatabase() + "\n";
        }
        ManipulaArquivo.escritor("./emprestimos.csv", dbString);
    }
    
    public void relatar() {
        for (int i = 0; i < this.emprestimos.size(); i++) {
            System.out.println(this.emprestimos.get(i).toString());
        }
    }

    public ArrayList<Emprestimo> getEmprestimos() {
        return this.emprestimos;
    }
}
