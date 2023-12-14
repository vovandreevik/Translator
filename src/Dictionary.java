import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dictionary {
    public static Map<String, String> load(String path) throws FileReadException, InvalidFileFormatException {
        Map<String, String> dictionary = new HashMap<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (String line : lines) {
                String[] parts = line.split("\\|");
                if (parts.length != 2) {
                    throw new InvalidFileFormatException("Invalid line: " + line);
                }
                dictionary.put(parts[0].trim().toLowerCase(), parts[1].trim());
            }
        } catch (IOException e) {
            throw new FileReadException("Error reading dictionary file: " + path, e);
        }
        return dictionary;
    }
}
