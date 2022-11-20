import java.io.File;

import java.util.Scanner;

import biblioteca.ProfessorVet;
import biblioteca.ManipulaArquivo;

public class App {
    static Scanner in = new Scanner(System.in);
    static File bancoProfessores = new File("./professores.csv");

    public static void main(String[] args) throws Exception {
        ProfessorVet professores = new ProfessorVet();
        if (bancoProfessores.exists()) {
            professores.ler(
                    professores.getProfessores(),
                    ManipulaArquivo.leitor("./professores.csv"));
        }

        int escPrincipal = -1;
        int escCadastros = -1;
        int escEmprestimos = -1;

        while (escPrincipal != 0) {

            if (escPrincipal == -1) {
                System.out.println("MENU PRINCIPAL\n" +
                        "1- CADASTRAR\n" +
                        "2- EMPRÉSTIMO\n" +
                        "3- DEVOLUÇÃO\n" +
                        "4- AUTENTICAR USUÁRIO\n" +
                        "5- RELATÓRIO\n" +
                        "0- SAIR");
                escPrincipal = in.nextInt();
            }

            if (escPrincipal == 1) {
                System.out.println("MENU DE CADASTROS\n" +
                        "1- CADASTRAR PROFESSOR\n" +
                        "2- CADASTRAR ALUNO\n" +
                        "3- CADASTRAR FUNCIONÁRIO\n" +
                        "4- CADASTRAR PERIÓDICO\n" +
                        "5- CADASTRAR LIVRO\n" +
                        "0- VOLTAR AO MENU PRINCIPAL");
                escCadastros = in.nextInt();
                if (escCadastros == 0) {
                    escPrincipal = -1;
                    continue;
                } else if (escCadastros == 1) {
                    professores.cadastrar();
                }
            }

            if (escPrincipal == 2) {
                System.out.println("MENU DE EMPRÉSTIMOS\n" +
                        "1- LIVROS CADASTRADOS\n" +
                        "2- EMPRÉSTIMOS REALIZADOS\n" +
                        "3- FUNCIONÁRIOS CADASTRADOS\n" +
                        "4- ALUNOS CADASTRADOS\n" +
                        "5- IMPRIMIR MULTA INDIVIDUAL\n" +
                        "0- VOLTAR AO MENU PRINCIPAL");
                escEmprestimos = in.nextInt();
                if (escEmprestimos == 0) {
                    escPrincipal = -1;
                    continue;
                } else if (escEmprestimos == 2) {
                    System.out.println("Imprimento empréstimos realizados");
                }
            }
        }
        in.close();
    }
}
