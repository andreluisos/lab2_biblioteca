package biblioteca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProfessorVet {
    private ArrayList<Professor> professores = new ArrayList<Professor>();
    Scanner in = new Scanner(System.in);

    public void ler(ArrayList<Professor> professores, List<String> linhas) {
        int numLinhas = linhas.size();
        String linha, valorComSplit[];

        for (int i = 0; i < numLinhas; i++) {
            linha = linhas.get(i);
            valorComSplit = linha.split(";");
            professores.add(new Professor(
                    Integer.parseInt(valorComSplit[0]),
                    valorComSplit[1],
                    valorComSplit[2],
                    valorComSplit[3],
                    valorComSplit[4]));
        }
        this.professores = professores;
    }

    public void cadastrar() throws IOException {
        int matricula;
        String nome;
        String end;
        String dataIngresso;
        String setor;

        System.out.println("Digite a matrícula do professor:");
        matricula = in.nextInt();

        System.out.println("Digite o nome do professor:");
        in.nextLine();
        nome = in.nextLine();

        System.out.println("Digite endereço do professor:");
        end = in.nextLine();

        System.out.println("Digite data de ingresso do professor (dd/mm/aaaa):");
        dataIngresso = in.next();

        System.out.println("Digite o setor do professor:");
        in.nextLine();
        setor = in.nextLine();

        Professor professor = new Professor(
                matricula,
                nome,
                end,
                dataIngresso,
                setor);

        professores.add(professor);
        String profString = "";
        for (int i = 0; i < professores.size(); i++) {
            profString += professores.get(i).toString() + "\n";
        }

        ManipulaArquivo.escritor("professores.csv", profString);

        System.out.println("Professor cadastrado com sucesso!");
    }

    public ArrayList<Professor> getProfessores() {
        return professores;
    }
}