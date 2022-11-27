package biblioteca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LivroVet {
    ArrayList<Livro> livros = new ArrayList<Livro>();
    Scanner in = new Scanner(System.in);

    public void ler(List<String> linhas) {
        int numLinhas = linhas.size();
        String linha, valorComSplit[];

        for (int i = 0; i < numLinhas; i++) {
            linha = linhas.get(i);
            if (!linha.isEmpty()) {
                valorComSplit = linha.split(";");
                this.livros.add(
                        new Livro(
                                Integer.parseInt(valorComSplit[0]),
                                valorComSplit[1],
                                valorComSplit[2],
                                valorComSplit[3],
                                valorComSplit[4].charAt(0),
                                Integer.parseInt(valorComSplit[5]),
                                Integer.parseInt(valorComSplit[6])));
            }
        }
    }

    public void cadastrar(List<Periodico> periodicos) throws IOException {
        int codigo;
        String autores;
        String titulo;
        String editora;
        char tipo;
        int ano;
        int issn;

        if (periodicos.size() == 0 && this.livros.size() == 0) {
            codigo = 0;
        } else {
            if (this.livros.size() > periodicos.size()) {
                codigo = this.livros.get(this.livros.size() - 1).getCodigo() + 1;
            } else {
                codigo = periodicos.get(periodicos.size() - 1).getCodigo() + 1;
            }
        }

        System.out.println("Digite os autores do livro:");
        autores = in.nextLine();

        System.out.println("Digite o título do livro:");
        titulo = in.nextLine();

        System.out.println("Digite a editora do livro:");
        editora = in.nextLine();

        tipo = 'L';

        System.out.println("Digite o ano do livro:");
        if (in.hasNextInt()) {
            ano = in.nextInt();
        } else {
            ano = 0;
            System.out.println("Erro de IO");
            System.exit(0);
        }
        

        System.out.println("Digite o ISSN do livro:");
        if (in.hasNextInt()) {
            issn = in.nextInt();
        } else {
            issn = 0;
            System.out.println("Erro de IO");
            System.exit(0);
        }
        
        this.livros.add(new Livro(
                codigo,
                autores,
                titulo,
                editora,
                tipo,
                ano,
                issn));

        this.saveToDB(livros);
    }

    public void saveToDB(ArrayList<Livro> livros) throws IOException {
        String dbString = "";
        for (int i = 0; i < this.livros.size(); i++) {
            dbString += this.livros.get(i).toDatabase() + "\n";
        }
        ManipulaArquivo.escritor("./livros.csv", dbString);
    }

    public void relatar() {
        for (int i = 0; i < this.livros.size(); i++) {
            System.out.println(this.livros.get(i).toString());
        }
    }

    public ArrayList<Livro> getLivros() {
        return this.livros;
    }
}
