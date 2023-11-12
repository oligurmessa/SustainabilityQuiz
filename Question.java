import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Question extends VBox {

    private boolean done = false;
    private boolean mode; // False for Multiple Choice Question (MCQ), True for True/False
    private int indexOfAnswer; // Index of the correct answer for MCQ
    private String topic;
    private Text question;
    private String[] possibleAnswers; // Array of possible answers for MCQ
    private String statement; // Additional statement or information about the question
    private boolean tfAnswer; // True/False answer for True/False question
    private ArrayList<RadioButton> buttons;
    private Button submit;

    // Constructor for Multiple Choice Question (MCQ)
    public Question(String topic, String question, String[] possAnwsers, int indexOfAnswer, String statement) {
        this.topic = topic;
        this.question = new Text(question);
        this.possibleAnswers = possAnwsers;
        this.statement = statement;
        this.mode = false; // MCQ mode
        this.submit = new Button("Submit");
        this.indexOfAnswer = indexOfAnswer;
        ToggleGroup group = new ToggleGroup();
        buttons = new ArrayList<RadioButton>();
        this.getChildren().add(this.question);
        for (int i = 0; i < 4; i++) {
            buttons.add(new RadioButton(possibleAnswers[i]));
            buttons.get(i).setToggleGroup(group);
            buttons.get(i).setOnAction(this::processRadioButtonAction);

            this.getChildren().add(buttons.get(i));
        }
        submit.setOnAction(this::processButtonAction);
        this.getChildren().add(submit);
    }

    // Constructor for True/False Question
    public Question(String topic, String question, boolean tfAnswer, String statement) {
        this.topic = topic;
        this.question = new Text(question);
        this.tfAnswer = tfAnswer;
        this.statement = statement;
        this.mode = true; // True/False mode
        this.submit = new Button("Submit");
        buttons = new ArrayList<RadioButton>();

        ToggleGroup group = new ToggleGroup();
        submit.setOnAction(this::processButtonAction);

        buttons.add(new RadioButton("True"));
        buttons.get(0).setToggleGroup(group);
        buttons.add(new RadioButton("False"));
        buttons.get(1).setToggleGroup(group);
        this.getChildren().addAll(this.question, buttons.get(0), buttons.get(1), submit);
    }

    // Process action when the submit button is clicked
    public void processButtonAction(ActionEvent event) {
        int tf = 2; // Default for True/False

        // Check if in true/false mode
        // then sets tf to a number to check which Radio button is pressed
        // tf is just an indicator for each radio button 0 = the true button, 1 = false button
        if (mode) {
            if (this.tfAnswer) {
                tf = 0; // Radio button True
            } else {
                tf = 1; // Radio button False
            }
        }

        // Check each RadioButton to determine if it's selected and compare the answer
        for (int i = 0; i < (mode ? 2 : 4); i++) {
            if (buttons.get(i).isSelected()) {
                if (mode ? (i == tf) : (i == this.indexOfAnswer)) {
                    System.out.println("Correct");
                    this.done = true;
                } else {
                    System.out.println("Wrong");
                }
            }
        }
    }

    // Process action when a RadioButton is clicked
    public void processRadioButtonAction(ActionEvent event) {
        // Add any specific behavior when a RadioButton is selected (if needed)
    }
}
