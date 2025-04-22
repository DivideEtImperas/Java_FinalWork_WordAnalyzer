package Model;

// Класс для хранения информации о частоте слова
public class WordFrequency {
    private final String word;
    private final int frequency;

    public WordFrequency(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    // Геттеры
    public String getWord() {
        return word;
    }

    public int getFrequency() {
        return frequency;
    }
}
