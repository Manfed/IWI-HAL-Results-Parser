package model;

public class VectorObject {

    private String word;
    private Long value;

    public VectorObject(String word, Long value) {
        this.word = word;
        this.value = value;
    }

    public String getWord() {
        return word;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "[" +
                 word +
                ", " + value +
                ']';
    }
}
