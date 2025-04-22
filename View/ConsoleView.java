package View;

import Model.AnalysisResult;
import Model.WordFrequency;

public class ConsoleView {

    public void displayAnalysisResults(AnalysisResult result) {
        System.out.println("Общее количество слов: " + result.getTotalWords());
        System.out.println("Количество уникальных слов: " + result.getUniqueWordsCount());
        System.out.println("\n20 самых частых слов:");

        for (WordFrequency wf : result.getTopFrequentWords()) {
            System.out.printf("%-15s: %d раз(a)%n", wf.getWord(), wf.getFrequency());
        }
    }

    public void displayError(String message) {
        System.err.println("Ошибка: " + message);
    }
}
