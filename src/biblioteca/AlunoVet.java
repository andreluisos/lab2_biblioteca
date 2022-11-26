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

    public void cadastrar(List<Professor> professores) throws IOException {
        int matricula;
        String nome;
        String end;
        String dataIngresso;
        String curso;
        double multa = 0;
        String dbString = "";

        if (professores.size() == 0 && this.alunos.size() == 0) {
            matricula = 0;
        } else {
            if (professores.size() > this.alunos.size()) {
                matricula = professores.get(professores.size() - 1).getMatricula() + 1;
            } else {
                matricula = this.alunos.get(this.alunos.size() - 1).getMatricula() + 1;
            }
        }

        System.out.println("Digite o nome do aluno:");
        nome = in.nextLine();

        System.out.println("Digite endereço do aluno:");
        end = in.nextLine();

        System.out.println("Digite data de ingresso do aluno (dd/mm/aaaa):");
        dataIngresso = in.next();

        System.out.println("Digite o curso do aluno:");
        in.nextLine();
        curso = in.nextLine();

        this.alunos.add(new Aluno(
                matricula,
                nome,
                end,
                dataIngresso,
                curso,
                multa));

        this.saveToDB(alunos);
    }

    public void saveToDB(ArrayList<Aluno> alunos) throws IOException {
        String dbString = "";
        for (int i = 0; i < this.alunos.size(); i++) {
            dbString += this.alunos.get(i).toDatabase() + "\n";
        }
        ManipulaArquivo.escritor("./alunos.csv", dbString);
    }

    public void aplicarMulta(int matricula) {
        for (int i = 0; i < this.alunos.size(); i++) {
            if (this.alunos.get(i).getMatricula() == matricula) {
                this.alunos.get(i).setMulta(this.alunos.get(i).getMulta() + 2);
            }
        }
    }

    public Aluno getAlunoByMatricula(int matricula) {
        for (int i = 0; i < this.alunos.size(); i++) {
            if (this.alunos.get(i).getMatricula() == matricula) {
                return this.alunos.get(i);
            }
        }
        return null;
    }

    public void relatar() {
        for (int i = 0; i < this.alunos.size(); i++) {
            System.out.println(this.alunos.get(i).toString());
        }
    }

    public ArrayList<Aluno> getAlunos() {
        return this.alunos;
    }
}
