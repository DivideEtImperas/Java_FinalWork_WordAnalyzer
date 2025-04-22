
import Controller.TextAnalyzerController;
import Model.TextAnalyzerModel;
import View.ConsoleView;

public class WordAnalyzerMVC {
    public static void main(String[] args) {
        try {
            String filePath = "input.txt";

            // Инициализация компонентов MVC
            TextAnalyzerModel model = new TextAnalyzerModel();
            ConsoleView view = new ConsoleView();
            TextAnalyzerController controller = new TextAnalyzerController(model, view);

            // Запуск программы
            controller.analyzeTextFromFile(filePath);
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}