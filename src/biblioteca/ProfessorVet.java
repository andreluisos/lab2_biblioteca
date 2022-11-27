package biblioteca;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProfessorVet {
    private ArrayList<Professor> professores = new ArrayList<Professor>();
    Scanner in = new Scanner(System.in);
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void ler(List<String> linhas) {
        int numLinhas = linhas.size();
        String linha, valorComSplit[];

        for (int i = 0; i < numLinhas; i++) {
            linha = linhas.get(i);
            if (!linha.isEmpty()) {
                valorComSplit = linha.split(";");
                this.professores.add(
                        new Professor(
                                Integer.parseInt(valorComSplit[0]),
                                valorComSplit[1],
                                valorComSplit[2],
                                valorComSplit[3],
                                valorComSplit[4]));
            }
        }
    }

    public void cadastrar(List<Aluno> alunos) throws IOException {
        int matricula;
        String nome;
        String end;
        String dataIngresso;
        String setor;

        if (this.professores.size() == 0 && alunos.size() == 0) {
            matricula = 0;
        } else {
            if (this.professores.size() > alunos.size()) {
                matricula = this.professores.get(this.professores.size() - 1).getMatricula() + 1;
            } else {
                matricula = alunos.get(alunos.size() - 1).getMatricula() + 1;
            }
        }

        System.out.println("Digite o nome do professor:");
        nome = in.nextLine();

        System.out.println("Digite endereço do professor:");
        end = in.nextLine();

        System.out.println("Digite data de ingresso do professor (dd/mm/aaaa):");
        dataIngresso = in.next();
        try {
            dateFormat.format(dataIngresso);
        } catch (Exception e) {
            System.out.println("Erro de IO");
            System.exit(0);
        }

        System.out.println("Digite o setor do professor:");
        in.nextLine();
        setor = in.nextLine();

        this.professores.add(new Professor(
                matricula,
                nome,
                end,
                dataIngresso,
                setor));

        this.saveToDB(professores);
    }

    public void saveToDB(ArrayList<Professor> professores) throws IOException {
        String dbString = "";
        for (int i = 0; i < this.professores.size(); i++) {
            dbString += this.professores.get(i).toDatabase() + "\n";
        }
        ManipulaArquivo.escritor("./professores.csv", dbString);
    }

    public Professor getProfessorByMatricula(int matricula) {
        for (int i = 0; i < this.professores.size(); i++) {
            if (this.professores.get(i).getMatricula() == matricula) {
                return this.professores.get(i);
            }
        }
        return null;
    }

    public void relatar() {
        for (int i = 0; i < this.professores.size(); i++) {
            System.out.println(this.professores.get(i).toString());
        }
    }

    public ArrayList<Professor> getProfessores() {
        return this.professores;
    }
}
