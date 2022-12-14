import java.io.File;
import java.util.Scanner;

import biblioteca.ProfessorVet;
import biblioteca.AlunoVet;
import biblioteca.EmprestimoVet;
import biblioteca.LivroVet;
import biblioteca.ManipulaArquivo;
import biblioteca.PeriodicoVet;
import biblioteca.FuncionarioVet;
import biblioteca.ItemEmprestimoVet;

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
                System.out.println("IN??CIO\n" +
                        "1- LOGIN\n" +
                        "2- CADASTRAR NOVO FUNCION??RIO\n" +
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
                            "2- EMPR??STIMO\n" +
                            "3- DEVOLU????O\n" +
                            "4- RELAT??RIOS\n" +
                            "0- SAIR");
                    escPrincipal = in.nextInt();
                }

                if (escPrincipal == 0) {
                    break;
                } else if (escPrincipal == 1) {
                    System.out.println("MENU DE CADASTROS\n" +
                            "1- CADASTRAR PROFESSOR\n" +
                            "2- CADASTRAR ALUNO\n" +
                            "3- CADASTRAR LIVRO\n" +
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
                        System.out.println("1- Livro\n2- Peri??dico");
                        System.out.println("Digite o tipo de item a ser cadastrado: ");
                        if (in.nextInt() == 1) {
                            livros.cadastrar(periodicos.getPeriodicos());
                        } else {
                            periodicos.cadastrar(livros.getLivros());
                        }
                    }
                } else if (escPrincipal == 2) {
                    if (professores.getProfessores().size() == 0 &&
                            alunos.getAlunos().size() == 0 &&
                            periodicos.getPeriodicos().size() == 0 &&
                            livros.getLivros().size() == 0) {
                        System.out.print("N??o h?? cadastros de items ou usu??rios no banco de dados!");
                    } else {
                        boolean emprestimoCadastrado = emprestimos.cadastrar(
                                codigoFuncionario, professores.getProfessores(), alunos.getAlunos());
                        if (emprestimoCadastrado) {
                            itemEmprestimos.cadastrar(livros.getLivros(), periodicos.getPeriodicos(),
                                    emprestimos.getEmprestimos());
                        } else {
                            escPrincipal = -1;
                            continue;
                        }
                    }
                    escPrincipal = 1;
                } else if (escPrincipal == 3) {
                    for (int i = 0; i < emprestimos.getEmprestimos().size(); i++) {
                        String cliente = "";
                        if (alunos.getAlunoByMatricula(emprestimos.getEmprestimos().get(i).getMatriculaCliente()) != null) {
                            cliente = alunos.getAlunoByMatricula(
                                    emprestimos.getEmprestimos().get(i).getMatriculaCliente()).getNome();
                        } else {
                            cliente = professores.getProfessorByMatricula(
                                    emprestimos.getEmprestimos().get(i).getMatriculaCliente()).getNome();
                        }
                        System.out.println("=========\n" + "C??digo do empr??stimo: "
                                + emprestimos.getEmprestimos().get(i).getCodigo() + "\n"
                                + "\tFuncion??rio respons??vel: " + funcionarios.getFuncionarioByMatricula(
                                        emprestimos.getEmprestimos().get(i).getMatriculaFuncionario()).getNome()
                                + "\n"
                                + "\tCliente: " + cliente + "\n"
                                + "\tData do empr??stimo: " + emprestimos.getEmprestimos().get(i).getDataEmprestimo()
                                + "\n"
                                + "\tData da devolu????o: " + emprestimos.getEmprestimos().get(i).getDataDevolucao()
                                + "\n"
                                + "=========\n");
                    }
                    System.out.print("\nDigite o c??digo do empr??stimo: ");
                    int emprestimo = in.nextInt();

                    for (int i = 0; i < emprestimos.getEmprestimos().size(); i++) {
                        if (emprestimo == emprestimos.getEmprestimos().get(i).getCodigo()) {
                            if (data.parse(emprestimos.getEmprestimos().get(i).getDataDevolucao()).before(new Date())) {
                                if (alunos.getAlunoByMatricula(
                                        emprestimos.getEmprestimos().get(i).getMatriculaCliente()) != null) {
                                    int matriculaAluno = emprestimos.getEmprestimos().get(i)
                                            .getMatriculaCliente();
                                    alunos.getAlunoByMatricula(matriculaAluno)
                                            .setMulta(alunos.getAlunoByMatricula(matriculaAluno).getMulta() + 2);
                                }
                            }
                            emprestimos.getEmprestimos().remove(i);
                            itemEmprestimos.getItemEmprestimos().remove(i);
                        } else {
                            System.out.print("C??digo do empr??stimo inv??lido!");
                        }
                    }
                    escPrincipal = 1;
                } else if (escPrincipal == 4) {
                    System.out.println("MENU DE RELAT??RIOS\n" +
                            "1- ALUNOS CADASTRADOS\n" +
                            "2- PROFESSORES CADASTRADOS\n" +
                            "3- FUNCION??RIOS CADASTRADOS\n" +
                            "4- LIVROS CADASTRADOS\n" +
                            "5- PERI??DICOS CADASTRADOS\n" +
                            "6- EMPR??STIMOS REALIZADOS\n" +
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
                    } else if (escSecundaria == 7) {
                        System.out.print("Digite a matr??cula do aluno: ");
                        int matricula = in.nextInt();
                        if (alunos.getAlunoByMatricula(matricula) != null) {
                            System.out.println(alunos.getAlunoByMatricula(
                                    matricula).getNome() + " possui uma multa de " + alunos
                                    .getAlunoByMatricula(matricula).getMulta() + " reais.");
                        }
                    }
                } else {
                    escPrincipal = -1;
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
