package file;

import com.sun.istack.internal.NotNull;
import model.Dictionary;
import model.VectorObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordVectorFile {

    public static void parseWordVector(@NotNull String fileName, @NotNull String targetFile, int limit, Dictionary dictionary) {
        try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.ISO_8859_1)) {
            stream
                    .map(line -> line.split(" -> "))
                    .forEach(splittedLine -> {
                        final List<VectorObject> vectorObjects = Arrays.stream(splittedLine[1].split("], \\["))
                                .map(vectorObject -> vectorObject.replaceAll("\\[|\\]", "").split(", "))
                                .map(splittedVectorObject -> new VectorObject(dictionary.getWord(new Long(splittedVectorObject[0])),
                                        new Long(splittedVectorObject[1])))
                                .sorted(Comparator.comparingLong(VectorObject::getValue))
                                .limit(limit)
                                .collect(Collectors.toList());

                        StringBuilder stringBuilder = new StringBuilder(splittedLine[0] + " -> ");
                        for (int i = 0; i < vectorObjects.size(); i++) {
                            stringBuilder.append(vectorObjects.get(i).toString());
                            if (i < vectorObjects.size() - 1) {
                                stringBuilder.append(", ");
                            } else {
                                stringBuilder.append("\n\n");
                            }
                        }

                        try {
                            //save vector to file
                            final Path path = Paths.get(targetFile);
                            Files.write(path, stringBuilder.toString().getBytes(),
                                    Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
