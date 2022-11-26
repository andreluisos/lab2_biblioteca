import java.io.File;
import java.util.Scanner;

import biblioteca.ProfessorVet;
import biblioteca.AlunoVet;
import biblioteca.EmprestimoVet;
import biblioteca.FuncionarioVet;
import biblioteca.ItemEmprestimoVet;
import biblioteca.LivroVet;
import biblioteca.ManipulaArquivo;
import biblioteca.PeriodicoVet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App {
    static Scanner in = new Scanner(System.in);
    static File bancoProfessores = new File("./professores.csv");
    static File bancoAlunos = new File("./alunos.csv");
    static File bancoFuncionarios = new File("./funcionarios.csv");
    static File bancoPeriodicos = new File("./periodicos.csv");
    static File bancoLivros = new File("./livros.csv");
    static File bancoEmprestimos = new File("./emprestimos.csv");
    static File bancoItemEmprestimos = new File("./itemEmprestimos.csv");
    static SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) throws Exception {
        ProfessorVet professores = new ProfessorVet();
        AlunoVet alunos = new AlunoVet();
        FuncionarioVet funcionarios = new FuncionarioVet();
        PeriodicoVet periodicos = new PeriodicoVet();
        LivroVet livros = new LivroVet();
        EmprestimoVet emprestimos = new EmprestimoVet();
        ItemEmprestimoVet itemEmprestimos = new ItemEmprestimoVet();

        if (bancoProfessores.exists()) {
            professores.ler(
                    ManipulaArquivo.leitor("./professores.csv"));
        }
        if (bancoAlunos.exists()) {
            alunos.ler(
                    ManipulaArquivo.leitor("./alunos.csv"));
        }
        if (bancoFuncionarios.exists()) {
            funcionarios.ler(
                    ManipulaArquivo.leitor("./funcionarios.csv"));
        }
        if (bancoLivros.exists()) {
            livros.ler(
                    ManipulaArquivo.leitor("./livros.csv"));
        }
        if (bancoPeriodicos.exists()) {
            periodicos.ler(
                    ManipulaArquivo.leitor("./periodicos.csv"));
        }
        if (bancoEmprestimos.exists()) {
            emprestimos.ler(
                    ManipulaArquivo.leitor("./emprestimos.csv"));
        }
        if (bancoItemEmprestimos.exists()) {
            itemEmprestimos.ler(
                    ManipulaArquivo.leitor("./itemEmprestimos.csv"));
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
                        professores.cadastrar(alunos.getAlunos());
                    } else if (escSecundaria == 2) {
                        alunos.cadastrar(professores.getProfessores());
                    } else if (escSecundaria == 3) {
                        periodicos.cadastrar(livros.getLivros());
                    } else if (escSecundaria == 4) {
                        livros.cadastrar(periodicos.getPeriodicos());
                    }
                }

                if (escPrincipal == 2) {
                    if (professores.getProfessores().size() == 0 &&
                            alunos.getAlunos().size() == 0 &&
                            periodicos.getPeriodicos().size() == 0 &&
                            livros.getLivros().size() == 0) {
                        System.out.print("Não há cadastros de items ou usuários no banco de dados!");
                    } else {
                        emprestimos.cadastrar(codigoFuncionario, professores.getProfessores(), alunos.getAlunos());
                        itemEmprestimos.cadastrar(livros.getLivros(), periodicos.getPeriodicos(),
                                emprestimos.getEmprestimos());
                    }
                    escPrincipal = 1;
                }

                if (escPrincipal == 3) {
                    for (int i = 0; i < emprestimos.getEmprestimos().size(); i++) {
                        String cliente;
                        if (alunos.getAlunoByMatricula(i) != null) {
                            cliente = alunos.getAlunoByMatricula(i).getNome();
                        } else {
                            cliente = professores.getProfessorByMatricula(i).getNome();
                        }
                        System.out.println("=========\n" + "Código do empréstimo: "
                                + emprestimos.getEmprestimos().get(i).getCodigo() + "\n"
                                + "\tFuncionário responsável: " + funcionarios.getFuncionarioByMatricula(
                                        emprestimos.getEmprestimos().get(i).getMatriculaFuncionario()).getNome()
                                + "\n"
                                + "\tCliente: " + cliente + "\n"
                                + "\tData do empréstimo: " + emprestimos.getEmprestimos().get(i).getDataEmprestimo()
                                + "\n"
                                + "\tData da devolução: " + emprestimos.getEmprestimos().get(i).getDataDevolucao()
                                + "\n"
                                + "=========\n");
                    }
                    System.out.print("\nDigite o código do empréstimo: ");
                    int emprestimo = in.nextInt();
                    
                    for (int i = 0; i < emprestimos.getEmprestimos().size(); i++) {
                        if (emprestimo == emprestimos.getEmprestimos().get(i).getCodigo()) {
                            if (data.parse(emprestimos.getEmprestimos().get(i).getDataDevolucao()).before(new Date())) {
                                System.out.print(2);
                                    if (alunos.getAlunoByMatricula(emprestimos.getEmprestimos().get(i).getMatriculaCliente()) != null) {
                                        alunos.getAlunoByMatricula(emprestimos.getEmprestimos().get(i)
                                                .getMatriculaCliente())
                                                .setMulta(alunos.getAlunoByMatricula(emprestimos.getEmprestimos().get(i)
                                                        .getMatriculaCliente()).getMulta() + 2);
                                    }
                            }
                            emprestimos.getEmprestimos().remove(i);
                            itemEmprestimos.getItemEmprestimos().remove(i);
                        } else {
                            System.out.print("Código do empréstimo inválido!");
                        }
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
                        alunos.relatar();
                    } else if (escSecundaria == 2) {
                        professores.relatar();
                    } else if (escSecundaria == 3) {
                        funcionarios.relatar();
                    } else if (escSecundaria == 4) {
                        livros.relatar();
                    } else if (escSecundaria == 5) {
                        periodicos.relatar();
                    } else if (escSecundaria == 6) {
                        emprestimos.relatar();
                    }
                }
            }
        }
        emprestimos.saveToDB(emprestimos.getEmprestimos());
        alunos.saveToDB(alunos.getAlunos());
        professores.saveToDB(professores.getProfessores());
        funcionarios.saveToDB(funcionarios.getFuncionarios());
        itemEmprestimos.saveToDB(itemEmprestimos.getItemEmprestimos());
        livros.saveToDB(livros.getLivros());
        periodicos.saveToDB(periodicos.getPeriodicos());
        in.close();
    }
}
