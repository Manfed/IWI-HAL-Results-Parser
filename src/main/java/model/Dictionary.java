package model;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {

    private Map<Long, String> words;

    public Dictionary() {
        words = new HashMap<Long, String>();
    }

    public Dictionary(Map<Long, String> words) {
        this.words = words;
    }

    public void addWord(Long id, String word) {
        words.put(id, word);
    }

    public String getWord(Long id) {
        return words.get(id);
    }
}
