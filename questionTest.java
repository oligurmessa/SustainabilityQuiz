import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class questionTest extends Application {

	public void start(Stage primaryStage)  {
		//MCQ Test
		//Constructor used: public Question (String topic, String question, boolean tfAnswer, String statement )
		//MCQ START
		String topic = "TEST";
		String question = "When does class start?";
		int index = 3;
		String statement = "TEST";
		
		String[] a = {"8 AM", "9:35 AM", "10:55 AM", "12:15 PM"};
		Question mcq = new Question(topic,question,a,index,statement);
		Scene scene = new Scene(mcq, 300, 400);
		//End of MCQ
		
		//True False TEST
		//Constructor used: public Question (String topic, String question, boolean tfAnswer, String statement )
		/*String topic = "TEST";
		String question = "Class starts at 12:15 PM";
		boolean tfAnwser = true;
		String statement = "TEST";
		Question tf = new Question(topic,question,tfAnwser,statement);
		Scene scene = new Scene(tf,300,400);*/
		//END of True False
		
		
		
		primaryStage.setTitle("Question Test");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
