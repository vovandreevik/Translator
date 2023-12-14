import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            Map<String, String> dict = Dictionary.load("src/dictionary.txt");
            Translator translator = new Translator(dict);
            String textForTranslation = new String(Files.readAllBytes(Paths.get("src/input.txt")));
            System.out.println("Original text:\n" + textForTranslation);
            String translated = translator.translate(textForTranslation);
            System.out.println("Translation:\n" + translated);
        } catch (InvalidFileFormatException | FileReadException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
