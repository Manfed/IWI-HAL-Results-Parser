package file;

import model.Dictionary;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DictionaryFile {

    public static Dictionary readDictionary(String fileName) {
        Map<Long, String> dictionary = new HashMap<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.ISO_8859_1)) {
            dictionary = stream
                    .map(line -> line.split("\\s"))
                    .collect(Collectors.toMap(splittedValue -> new Long(splittedValue[1]),
                            splittedValue -> splittedValue[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Dictionary(dictionary);
    }
}
