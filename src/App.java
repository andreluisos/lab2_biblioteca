import java.io.File;

import java.util.Scanner;

import biblioteca.ProfessorVet;
import biblioteca.AlunoVet;
import biblioteca.FuncionarioVet;
import biblioteca.ManipulaArquivo;
import biblioteca.PeriodicoVet;

public class App {
    static Scanner in = new Scanner(System.in);
    static File bancoProfessores = new File("./professores.csv");
    static File bancoAlunos = new File("./alunos.csv");
    static File bancoFuncionarios = new File("./funcionarios.csv");
    static File bancoPeriodicos = new File("./periodicos.csv");

    public static void main(String[] args) throws Exception {
        ProfessorVet professores = new ProfessorVet();
        AlunoVet alunos = new AlunoVet();
        FuncionarioVet funcionarios = new FuncionarioVet();
        PeriodicoVet periodicos = new PeriodicoVet();

        if (bancoProfessores.exists()) {
            professores.ler(
                    ManipulaArquivo.leitor("./professores.csv"));
        }
        if (bancoAlunos.exists()) {
            professores.ler(
                    ManipulaArquivo.leitor("./alunos.csv"));
        }
        if (bancoFuncionarios.exists()) {
            funcionarios.ler(
                    ManipulaArquivo.leitor("./funcionarios.csv"));
        }
        if (bancoPeriodicos.exists()) {
            periodicos.ler(
                    ManipulaArquivo.leitor("./periodicos.csv"));
        }

        int escPrincipal = -1;
        int escSecundaria = -1;

        while (escPrincipal != 0) {

            if (escPrincipal == -1) {
                System.out.println("INÍCIO\n" +
                        "1- LOGIN\n" +
                        "2- CADASTRAR NOVO FUNCIONÁRIO\n" +
                        "0- SAIR");
                escPrincipal = in.nextInt();
                if (escPrincipal == 0) {
                    continue;
                } else if (escPrincipal == 1) {
                    if (funcionarios.login()) {
                        escPrincipal = 1;
                    } else {
                        escPrincipal = -1;
                    };
                } else if (escPrincipal == 2) {
                    funcionarios.cadastrar();
                }
            }

            if (escPrincipal == 1) {
                System.out.println("MENU PRINCIPAL\n" +
                        "1- CADASTRAR\n" +
                        "2- EMPRÉSTIMO\n" +
                        "3- DEVOLUÇÃO\n" +
                        "4- AUTENTICAR USUÁRIO\n" +
                        "5- RELATÓRIO\n" +
                        "0- SAIR");
                escPrincipal = in.nextInt();
            }

            if (escPrincipal == 2) {
                System.out.println("MENU DE CADASTROS\n" +
                        "1- CADASTRAR PROFESSOR\n" +
                        "2- CADASTRAR ALUNO\n" +
                        "4- CADASTRAR PERIÓDICO\n" +
                        "5- CADASTRAR LIVRO\n" +
                        "0- VOLTAR AO MENU PRINCIPAL");
                escSecundaria = in.nextInt();
                if (escSecundaria == 0) {
                    escPrincipal = 1;
                    continue;
                } else if (escSecundaria == 1) {
                    professores.cadastrar();
                } else if (escSecundaria == 2) {
                    alunos.cadastrar();
                } else if (escSecundaria == 3) {
                    funcionarios.cadastrar();
                } else if (escSecundaria == 4) {
                    periodicos.cadastrar();
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
                escSecundaria = in.nextInt();
                if (escSecundaria == 0) {
                    escPrincipal = 1;
                    continue;
                } else if (escSecundaria == 2) {
                    System.out.println("Imprimento empréstimos realizados");
                }
            }
        }
        in.close();
    }
}
