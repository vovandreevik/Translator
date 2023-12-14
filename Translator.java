import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Translator {
    private static final Pattern WORD_PATTERN = Pattern.compile("[\\w']+|[.,!?;]");
    private static final Pattern PUNCTUATION_PATTERN = Pattern.compile("[.,!?;]");

    private final Map<String, String> dictionary;

    public Translator(Map<String, String> dictionary) {
        this.dictionary = dictionary;
    }

    public String removeSpace(String text) {
        return text.replaceAll("\\s+([.,!?;])", "$1");
    }

    public String translate(String textForTranslation) {
        List<String> symbols = extractSymbols(textForTranslation);
        StringBuilder translation = new StringBuilder();
        int pos = 0;

        while (pos < symbols.size()) {
            String current = "";
            String best = null;
            int used = 0;

            for (int i = pos; i < symbols.size(); i++) {
                String symbol = symbols.get(i).toLowerCase();
                String word = PUNCTUATION_PATTERN.matcher(symbol).replaceAll("");
                current += (i > pos ? " " : "") + word;
                if (dictionary.containsKey(current)) {
                    best = dictionary.get(current);
                    used = i - pos + 1;
                }
            }

            if (best != null) {
                translation.append(best);
                pos += used;
            } else {
                translation.append(symbols.get(pos));
                pos++;
            }

            if (pos < symbols.size()) {
                translation.append(" ");
            }
        }
        return removeSpace(translation.toString());
    }

    private List<String> extractSymbols(String txt) {
        Matcher matcher = WORD_PATTERN.matcher(txt);
        List<String> symbols = new ArrayList<>();
        while (matcher.find()) {
            symbols.add(matcher.group());
        }
        return symbols;
    }
}
