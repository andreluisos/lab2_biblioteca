//TODO: Aparentemente, não está lendo do banco de dados corretamente.
import java.io.File;

import java.util.Scanner;

import biblioteca.ProfessorVet;
import biblioteca.AlunoVet;
import biblioteca.EmprestimoVet;
import biblioteca.FuncionarioVet;
import biblioteca.LivroVet;
import biblioteca.ManipulaArquivo;
import biblioteca.PeriodicoVet;

public class App {
    static Scanner in = new Scanner(System.in);
    static File bancoProfessores = new File("./professores.csv");
    static File bancoAlunos = new File("./alunos.csv");
    static File bancoFuncionarios = new File("./funcionarios.csv");
    static File bancoPeriodicos = new File("./periodicos.csv");
    static File bancoLivros = new File("./livros.csv");
    static File bancoEmprestimos = new File("./emprestimos.csv");

    public static void main(String[] args) throws Exception {
        ProfessorVet professores = new ProfessorVet();
        AlunoVet alunos = new AlunoVet();
        FuncionarioVet funcionarios = new FuncionarioVet();
        PeriodicoVet periodicos = new PeriodicoVet();
        LivroVet livros = new LivroVet();
        EmprestimoVet emprestimos = new EmprestimoVet();

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
        if (bancoEmprestimos.exists()) {
            emprestimos.ler(
                    ManipulaArquivo.leitor("./periodicos.csv"));
        }

        boolean loggedIn = false;
        int escPrincipal = -1;
        int escSecundaria = -1;
        int codigoFuncionario = 0;

        while (true) {

            if (!loggedIn) {
                System.out.println("INÍCIO\n" +
                        "1- LOGIN\n" +
                        "2- CADASTRAR NOVO FUNCIONÁRIO\n" +
                        "0- SAIR");
                escPrincipal = in.nextInt();
                if (escPrincipal == 0) {
                    break;
                } else if (escPrincipal == 1) {
                    codigoFuncionario = funcionarios.login();
                    if (codigoFuncionario >= 0) {
                        loggedIn = true;
                    } else {
                        loggedIn = false;
                    }
                    ;
                } else if (escPrincipal == 2) {
                    funcionarios.cadastrar();
                }
                escPrincipal = -1;
            } else {
                if (escPrincipal == -1) {
                    System.out.println("MENU PRINCIPAL\n" +
                            "1- CADASTRAR\n" +
                            "2- EMPRÉSTIMO\n" +
                            "3- DEVOLUÇÃO\n" +
                            "4- RELATÓRIOS\n" +
                            "0- SAIR");
                    escPrincipal = in.nextInt();
                }

                if (escPrincipal == 0) {
                    break;
                }

                if (escPrincipal == 1) {
                    System.out.println("MENU DE CADASTROS\n" +
                            "1- CADASTRAR PROFESSOR\n" +
                            "2- CADASTRAR ALUNO\n" +
                            "3- CADASTRAR PERIÓDICO\n" +
                            "4- CADASTRAR LIVRO\n" +
                            "0- VOLTAR AO MENU PRINCIPAL");
                    escSecundaria = in.nextInt();
                    if (escSecundaria == 0) {
                        escPrincipal = -1;
                        continue;
                    } else if (escSecundaria == 1) {
                        professores.cadastrar();
                    } else if (escSecundaria == 2) {
                        alunos.cadastrar();
                    } else if (escSecundaria == 3) {
                        periodicos.cadastrar();
                    } else if (escSecundaria == 4) {
                        livros.cadastrar();
                    }
                }

                if (escPrincipal == 2) {
                    if (professores.getProfessores().size() == 0 &&
                            alunos.getAlunos().size() == 0 &&
                            periodicos.getPeriodicos().size() == 0 &&
                            livros.getLivros().size() == 0) {
                        System.out.print("Não há cadastros de items ou usuários no banco de dados!");
                    } else {
                        emprestimos.cadastrar(codigoFuncionario);
                    }
                    escPrincipal = 1;
                }

                if (escPrincipal == 4) {
                    System.out.println("MENU DE RELATÓRIOS\n" +
                            "1- ALUNOS CADASTRADOS\n" +
                            "2- PROFESSORES CADASTRADOS\n" +
                            "3- FUNCIONÁRIOS CADASTRADOS\n" +
                            "4- LIVROS CADASTRADOS\n" +
                            "5- PERIÓDICOS CADASTRADOS\n" +
                            "6- EMPRÉSTIMOS REALIZADOS\n" +
                            "7- CONSULTAR MULTA INDIVIDUAL\n" +
                            "0- VOLTAR AO MENU PRINCIPAL");
                    escSecundaria = in.nextInt();
                    if (escSecundaria == 0) {
                        escPrincipal = -1;
                        continue;
                    } else if (escSecundaria == 1) {
                        for (int i = 0; i < alunos.getAlunos().size(); i++) {
                            System.out.print(alunos.getAlunos().get(i));
                            System.out.println(alunos.getAlunos().get(i).toString());
                        }
                    } else if (escSecundaria == 2) {
                        for (int i = 0; i < professores.getProfessores().size(); i++) {
                            System.out.println(professores.getProfessores().get(i).toString());
                        }
                    } else if (escSecundaria == 3) {
                        for (int i = 0; i < funcionarios.getFuncionarios().size(); i++) {
                            System.out.println(funcionarios.getFuncionarios().get(i).toString());
                        }
                    } else if (escSecundaria == 4) {
                        for (int i = 0; i < livros.getLivros().size(); i++) {
                            System.out.println(livros.getLivros().get(i).toString());
                        }
                    } else if (escSecundaria == 5) {
                        for (int i = 0; i < periodicos.getPeriodicos().size(); i++) {
                            System.out.println(periodicos.getPeriodicos().get(i).toString());
                        }
                    } else if (escSecundaria == 6) {
                        for (int i = 0; i < emprestimos.getEmprestimos().size(); i++) {
                            System.out.println(emprestimos.getEmprestimos().get(i).toString());
                        }
                    }
                }                
            }
        }
        in.close();
    }
}
