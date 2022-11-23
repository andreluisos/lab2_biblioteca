package biblioteca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FuncionarioVet {
    private ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
    Scanner in = new Scanner(System.in);

    public void ler(List<String> linhas) {
        int numLinhas = linhas.size();
        String linha, valorComSplit[];

        for (int i = 0; i < numLinhas; i++) {
            linha = linhas.get(i);
            if (!linha.isEmpty()) {
                valorComSplit = linha.split(";");
                this.funcionarios.add(
                        new Funcionario(
                                Integer.parseInt(valorComSplit[0]),
                                valorComSplit[1],
                                valorComSplit[2],
                                valorComSplit[3],
                                valorComSplit[4],
                                valorComSplit[5],
                                valorComSplit[6]));
            }
        }
    }

    public void cadastrar() throws IOException {
        int matricula;
        String nome;
        String end;
        String dataIngresso;
        String setor;
        String login;
        String senha;

        String profString = "";

        System.out.println("Digite a matrícula do funcionário:");
        matricula = in.nextInt();

        System.out.println("Digite o nome do funcionário:");
        in.nextLine();
        nome = in.nextLine();

        System.out.println("Digite endereço do funcionário:");
        end = in.nextLine();

        System.out.println("Digite data de ingresso do funcionário (dd/mm/aaaa):");
        dataIngresso = in.next();

        System.out.println("Digite o setor do funcionário:");
        in.nextLine();
        setor = in.nextLine();

        System.out.println("Digite o login do funcionário:");
        login = in.nextLine();

        System.out.println("Digite o senha do funcionário:");
        senha = in.nextLine();

        this.funcionarios.add(new Funcionario(
                matricula,
                nome,
                end,
                dataIngresso,
                setor,
                login,
                senha));

        for (int i = 0; i < this.funcionarios.size(); i++) {
            profString += this.funcionarios.get(i).toDatabase() + "\n";
        }

        ManipulaArquivo.escritor("./funcionarios.csv", profString);

        System.out.println("Funcionario cadastrado com sucesso!");
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return this.funcionarios;
    }

    public boolean login() {
        String login;
        String senha;
        boolean autenticar = false;

        while (true) {
            System.out.println("Digite o login do funcionário:");
            login = in.nextLine();

            for (int i = 0; i < this.funcionarios.size(); i++) {
                if (this.funcionarios.get(i).getNome().equals(login)) {
                    System.out.println("Digite o senha do funcionário:");
                    senha = in.nextLine();
                    if (this.funcionarios.get(i).getSenha().equals(senha)) {
                        autenticar = true;
                    }
                }
            }
            break;
        }

        if (autenticar) {
            System.out.println("Autenticado!");
            return true;
        }
        System.out.println("Autenticação recusada!");
        return false;
    }
}
