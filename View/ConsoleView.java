package View;

import Model.AnalysisResult;
import Model.WordFrequency;

public class ConsoleView {

    public void displayAnalysisResults(AnalysisResult result) {
        // Подсчет слов
        System.out.println("Общее количество слов: " + result.getTotalWords());
        System.out.println("Количество уникальных слов: " + result.getUniqueWordsCount());

        // Вывод информации о самом длинном слове
        if (result.getLongestWord() != null) {
            System.out.println("\nСамое длинное слово: '" + result.getLongestWord() + "'");
            System.out.println("Длина: " + result.getLongestWordLength() + " букв");
        }
        // Вывод информации о частоте слов
        System.out.println("\n20 самых частых слов:");

        for (WordFrequency wf : result.getTopFrequentWords()) {
            System.out.printf("%-15s: %d раз(a)%n", wf.getWord(), wf.getFrequency());
        }
    }

    public void displayError(String message) {
        System.err.println("Ошибка: " + message);
    }
}
