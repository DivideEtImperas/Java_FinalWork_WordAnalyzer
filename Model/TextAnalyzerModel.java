package Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class TextAnalyzerModel {
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

    public String findLongestWord() {
        if (words == null || words.isEmpty()) {
            return null;
        }
        return words.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse(null);
    }

    public void analyze() {
        if (words == null || words.isEmpty()) {
            throw new IllegalStateException("Текст не загружен");
        }

        int totalWords = words.size();
        int uniqueWordsCount = calculateUniqueWords();
        List<WordFrequency> topFrequentWords = calculateTopFrequentWords(20);
        String longestWord = findLongestWord();

        this.result = new AnalysisResult(totalWords, uniqueWordsCount, topFrequentWords, longestWord);
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
}
