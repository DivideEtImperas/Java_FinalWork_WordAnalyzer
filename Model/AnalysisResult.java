package Model;

import java.util.List;

// Вспомогательный класс для хранения результатов
public class AnalysisResult {
    private final int totalWords;
    private final int uniqueWordsCount;
    private final List<WordFrequency> topFrequentWords;
    private final String longestWord;

    public AnalysisResult(int totalWords, int uniqueWordsCount, List<WordFrequency> topFrequentWords,
            String longestWord) {
        this.totalWords = totalWords;
        this.uniqueWordsCount = uniqueWordsCount;
        this.topFrequentWords = topFrequentWords;
        this.longestWord = longestWord;
    }

    // Геттеры
    public int getTotalWords() {
        return totalWords;
    }

    public int getUniqueWordsCount() {
        return uniqueWordsCount;
    }

    public List<WordFrequency> getTopFrequentWords() {
        return topFrequentWords;
    }

    public String getLongestWord() {
        return longestWord;
    }

    public int getLongestWordLength() {
        return longestWord != null ? longestWord.length() : 0;
    }
}
