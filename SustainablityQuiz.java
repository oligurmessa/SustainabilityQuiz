import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuizApp extends Application {

    private List<Question> questions;
    private Random random = new Random();
    private Label questionLabel;
    private VBox choicesBox;
    private Button submitButton;
    private Label resultLabel;
    private Question currentQuestion;

    @Override
    public void start(Stage primaryStage) {
        initializeQuestions();
        currentQuestion = getRandomQuestion();

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        questionLabel = new Label();
        choicesBox = new VBox(5);
        submitButton = new Button("Submit");
        resultLabel = new Label("");

        displayQuestion(currentQuestion);

        submitButton.setOnAction(e -> checkAnswer());

        root.getChildren().addAll(questionLabel, choicesBox, submitButton, resultLabel);
        Scene scene = new Scene(root, 350, 250);

        primaryStage.setTitle("Quiz App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeQuestions() {
        questions = new ArrayList<>();
        // Add questions here
        questions.add(new Question("What is the capital of France?", new String[]{"Paris", "London", "Berlin", "Madrid"}, "Paris"));
        // Add more questions in a similar way
    }

    private Question getRandomQuestion() {
        return questions.get(random.nextInt(questions.size()));
    }

    private void displayQuestion(Question question) {
        questionLabel.setText(question.getQuestionText());
        choicesBox.getChildren().clear();
        ToggleGroup choicesGroup = new ToggleGroup();

        for (String choice : question.getChoices()) {
            RadioButton choiceButton = new RadioButton(choice);
            choiceButton.setToggleGroup(choicesGroup);
            choicesBox.getChildren().add(choiceButton);
        }
    }

    private void checkAnswer() {
        RadioButton selectedRadioButton = (RadioButton) ((ToggleGroup) choicesBox.getChildren().get(0).getToggleGroup()).getSelectedToggle();
        if (selectedRadioButton != null) {
            String userAnswer = selectedRadioButton.getText();
            if (userAnswer.equals(currentQuestion.getCorrectAnswer())) {
                resultLabel.setText("Correct!");
            } else {
                resultLabel.setText("Incorrect. The correct answer is: " + currentQuestion.getCorrectAnswer());
            }
            currentQuestion = getRandomQuestion();
            displayQuestion(currentQuestion);
        } else {
            resultLabel.setText("Please select an answer.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
