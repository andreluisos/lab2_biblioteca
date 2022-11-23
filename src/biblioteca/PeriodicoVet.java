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

    public void cadastrar() throws IOException {
        int codigo;
        String autores;
        String titulo;
        char tipo;
        int fatorImpacto;
        int issn;
        String profString = "";

        System.out.println("Digite o código do periódico:");
        codigo = in.nextInt();

        System.out.println("Digite os autores do periódico:");
        in.nextLine();
        autores = in.nextLine();

        System.out.println("Digite título do periódico:");
        titulo = in.nextLine();

        System.out.println("Digite o tipo do periódico:");
        tipo = in.next().charAt(0);

        System.out.println("Digite o fator de impacto do periódico:");
        fatorImpacto = in.nextInt();

        System.out.println("Digite o ISSN do periódico:");
        issn = in.nextInt();

        this.periodicos.add(new Periodico(
                codigo,
                autores,
                titulo,
                tipo,
                fatorImpacto,
                issn));

        for (int i = 0; i < this.periodicos.size(); i++) {
            profString += this.periodicos.get(i).toDatabase() + "\n";
        }

        ManipulaArquivo.escritor("./periodicos.csv", profString);

        System.out.println("Periódico cadastrado com sucesso!");
    }

    public ArrayList<Periodico> getPeriodicos() {
        return this.periodicos;
    }
}
