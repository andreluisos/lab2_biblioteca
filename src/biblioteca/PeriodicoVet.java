package biblioteca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PeriodicoVet {
    private ArrayList<Periodico> periodicos = new ArrayList<Periodico>();
    Scanner in = new Scanner(System.in);

    public void ler(List<String> linhas) {
        int numLinhas = linhas.size();
        String linha, valorComSplit[];

        for (int i = 0; i < numLinhas; i++) {
            linha = linhas.get(i);
            if (!linha.isEmpty()) {
                valorComSplit = linha.split(";");
                this.periodicos.add(
                        new Periodico(
                                Integer.parseInt(valorComSplit[0]),
                                valorComSplit[1],
                                valorComSplit[2],
                                valorComSplit[3].charAt(0),
                                Integer.parseInt(valorComSplit[4]),
                                Integer.parseInt(valorComSplit[5])));
            }
        }
    }

    public void cadastrar(List<Livro> livros) throws IOException {
        int codigo;
        String autores;
        String titulo;
        char tipo;
        int fatorImpacto;
        int issn;

        if (this.periodicos.size() == 0 && livros.size() == 0) {
            codigo = 0;
        } else {
            if (livros.size() > 0 && this.periodicos.size() > 0) {
                if (livros.get(livros.size() - 1).getCodigo() > this.periodicos.get(
                        periodicos.size() - 1)
                        .getCodigo()) {
                    codigo = livros.get(livros.size() - 1).getCodigo() + 1;
                } else {
                    codigo = this.periodicos.get(this.periodicos.size() - 1).getCodigo() + 1;
                }
            } else if (livros.size() > 0 && this.periodicos.size() == 0) {
                codigo = livros.get(livros.size() - 1).getCodigo() + 1;
            } else {
                codigo = this.periodicos.get(this.periodicos.size() - 1).getCodigo() + 1;
            }
        }

        System.out.println("Digite os autores do periódico:");
        autores = in.nextLine();

        System.out.println("Digite título do periódico:");
        titulo = in.nextLine();

        System.out.println("Tipos de periódico:\n1- Conferências\n2- Revistas\n3- Periódicos");
        int tipoInt = in.nextInt();
        if (tipoInt == 1) {
            tipo = 'C';
        } else if (tipoInt == 1) {
            tipo = 'R';
        } else {
            tipo = 'P';
        }

        System.out.println("Digite o fator de impacto do periódico:");
        if (in.hasNextInt()) {
            fatorImpacto = in.nextInt();
        } else {
            fatorImpacto = 0;
            System.out.println("Erro de IO");
            System.exit(0);
        }

        System.out.println("Digite o ISSN do periódico:");
        if (in.hasNextInt()) {
            issn = in.nextInt();
        } else {
            issn = 0;
            System.out.println("Erro de IO");
            System.exit(0);
        }

        in.nextLine();

        this.periodicos.add(new Periodico(
                codigo,
                autores,
                titulo,
                tipo,
                fatorImpacto,
                issn));

        this.saveToDB(periodicos);
    }

    public void saveToDB(ArrayList<Periodico> periodicos) throws IOException {
        String dbString = "";
        for (int i = 0; i < this.periodicos.size(); i++) {
            dbString += this.periodicos.get(i).toDatabase() + "\n";
        }
        ManipulaArquivo.escritor("./periodicos.csv", dbString);
    }

    public void relatar() {
        for (int i = 0; i < this.periodicos.size(); i++) {
            System.out.println(this.periodicos.get(i).toString());
        }
    }

    public ArrayList<Periodico> getPeriodicos() {
        return this.periodicos;
    }
}
