import file.DictionaryFile;
import file.WordVectorFile;
import model.Dictionary;

public class Application {
    public static void main(String[] args) {
        String dictionaryFile = args[0];
        String vectorsFile = args[1];
        String targetFile = args[2];
        int wordsPerVectorLimit = new Integer(args[3]);

        Dictionary dictionary = DictionaryFile.readDictionary(dictionaryFile);
        WordVectorFile.parseWordVector(vectorsFile, targetFile, wordsPerVectorLimit, dictionary);
    }
}
