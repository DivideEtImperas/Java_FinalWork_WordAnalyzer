package Controller;

import java.io.IOException;

import Model.TextAnalyzerModel;
import View.ConsoleView;

public class TextAnalyzerController {
    private final TextAnalyzerModel model;
    private final ConsoleView view;

    public TextAnalyzerController(TextAnalyzerModel model, ConsoleView view) {
        this.model = model;
        this.view = view;
    }

    public void analyzeTextFromFile(String filePath) {
        try {
            model.loadTextFromFile(filePath);
            model.analyze();
            view.displayAnalysisResults(model.getAnalysisResult());
        } catch (IOException e) {
            view.displayError(e.getMessage());
        } catch (IllegalStateException e) {
            view.displayError(e.getMessage());
        }
    }
}
