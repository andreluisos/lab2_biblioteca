package biblioteca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlunoVet {
    private ArrayList<Aluno> alunos = new ArrayList<Aluno>();
    Scanner in = new Scanner(System.in);

    public void ler(List<String> linhas) {
        int numLinhas = linhas.size();
        String linha, valorComSplit[];

        for (int i = 0; i < numLinhas; i++) {
            linha = linhas.get(i);
            if (!linha.isEmpty()) {
                valorComSplit = linha.split(";");
                this.alunos.add(
                        new Aluno(
                                Integer.parseInt(valorComSplit[0]),
                                valorComSplit[1],
                                valorComSplit[2],
                                valorComSplit[3],
                                valorComSplit[4],
                                Double.parseDouble(valorComSplit[5])));
            }
        }
    }

    public void cadastrar() throws IOException {
        int matricula;
        String nome;
        String end;
        String dataIngresso;
        String curso;
        double multa = 0;
        String profString = "";

        System.out.println("Digite a matrícula do aluno:");
        matricula = in.nextInt();

        System.out.println("Digite o nome do aluno:");
        in.nextLine();
        nome = in.nextLine();

        System.out.println("Digite endereço do aluno:");
        end = in.nextLine();

        System.out.println("Digite data de ingresso do aluno (dd/mm/aaaa):");
        dataIngresso = in.next();

        System.out.println("Digite o curso do aluno:");
        in.nextLine();
        curso = in.nextLine();

        System.out.println("Digite a multa do aluno:");
        multa = in.nextDouble();

        this.alunos.add(new Aluno(
                matricula,
                nome,
                end,
                dataIngresso,
                curso,
                multa));

        for (int i = 0; i < this.alunos.size(); i++) {
            profString += this.alunos.get(i).toString() + "\n";
        }

        ManipulaArquivo.escritor("./alunos.csv", profString);

        System.out.println("Aluno cadastrado com sucesso!");
    }

    public ArrayList<Aluno> getProfessores() {
        return this.alunos;
    }
}
