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
                                valorComSplit[5],
                                Integer.parseInt(valorComSplit[6])));
            }
        }
    }

    public void cadastrar() throws IOException {
        int codigo;
        String autores;
        String titulo;
        String editora;
        char tipo;
        String ano;
        int issn;
        String dbString = "";

        System.out.println("Digite o código do livro:");
        codigo = in.nextInt();

        System.out.println("Digite os autores do livro:");
        in.nextLine();
        autores = in.nextLine();

        System.out.println("Digite o título do livro:");
        titulo = in.nextLine();

        System.out.println("Digite a editora do livro:");
        editora = in.nextLine();

        System.out.println("Digite o tipo do livro:");
        tipo = in.next().charAt(0);

        System.out.println("Digite o ano do livro:");
        ano = in.next();

        System.out.println("Digite o ISSN do livro:");
        issn = in.nextInt();

        this.livros.add(new Livro(
                codigo,
                autores,
                titulo,
                editora,
                tipo,
                ano,
                issn));

        for (int i = 0; i < this.livros.size(); i++) {
            dbString += this.livros.get(i).toDatabase() + "\n";
        }

        ManipulaArquivo.escritor("./livros.csv", dbString);

        System.out.println("Livro cadastrado com sucesso!");
    }

    public ArrayList<Livro> getLivros() {
        return this.livros;
    }
}
