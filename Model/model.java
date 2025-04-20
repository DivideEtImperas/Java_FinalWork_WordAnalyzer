package Model;

import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class model {
    private List<String> words;
    private AnalysisResult result;

    public void loadTextFromFile(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        this.words = processLines(lines);
    }

    private List<String> processLines(List<String> lines) {
        return lines.stream()
                .flatMap(line -> Arrays.stream(line.split("[\\s\\p{Punct}]+")))
                .filter(word -> !word.isEmpty() && word.matches("[a-zA-Zа-яА-ЯёЁ]+"))
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }

    public void analyze() {
        if (words == null || words.isEmpty()) {
            throw new IllegalStateException("Текст не загружен");
        }

        int totalWords = words.size();
        int uniqueWordsCount = calculateUniqueWords();
        List<WordFrequency> topFrequentWords = calculateTopFrequentWords(20);

        this.result = new AnalysisResult(totalWords, uniqueWordsCount, topFrequentWords);
    }

    private int calculateUniqueWords() {
        return new HashSet<>(words).size();
    }

    private List<WordFrequency> calculateTopFrequentWords(int limit) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        return frequencyMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(limit)
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public AnalysisResult getAnalysisResult() {
        return result;
    }

    // Вспомогательный класс для хранения результатов
    public class AnalysisResult {
        private final int totalWords;
        private final int uniqueWordsCount;
        private final List<WordFrequency> topFrequentWords;

        public AnalysisResult(int totalWords, int uniqueWordsCount, List<WordFrequency> topFrequentWords) {
            this.totalWords = totalWords;
            this.uniqueWordsCount = uniqueWordsCount;
            this.topFrequentWords = topFrequentWords;
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

        // Класс для хранения информации о частоте слов
        class WordFrequency {
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

    }

}
