package biblioteca;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ManipulaArquivo {

    public static List<String> leitor(String localArquivo) throws IOException {
        Path path = Paths.get(localArquivo);
        List<String> linhas = Files.readAllLines(path);
        return linhas;
    }

    public static void escritor(String localArquivo, String linha) throws IOException {
        FileWriter arquivo = new FileWriter(String.valueOf(localArquivo));
        arquivo.write(linha + "\n");
        arquivo.close();
    }
}
