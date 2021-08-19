import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
//import javafx.scene.control.Label;
//import javafx.scene.layout.Pane;
//import javafx.scene.shape.Rectangle;
//import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
//import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
//import javafx.scene.paint.Color;
//import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;
import java.util.ArrayList;
import java.io.File;

/**
 * A color game where player has to pick the correct color.
 * @author pduong7
 * @version 1.0
 */
public class ColorGame extends Application {
    private static String ans;
    private static int scoreT = 0;
    private static Text score;
    private static Text description;
    private static String status = "Choose an answer to begin!";
    private static Text name;
    private static String input = "None";
    private static TextField entry;
    private static ArrayList<String> colorW;
    private static Button o1;
    private static Button o2;
    private static Button o3;
    private static Button none;
    private static String rand1;
    private static String rand2;
    private static String rand3;
    private static Button ques;
    private static AudioClip correct;
    private static AudioClip wrong;
    private static Image imageSource;

    /**
     * Create a stage to contains different nodes.
     * @param primaryStage stage.
     */
    @Override
    public void start(Stage primaryStage) {
        o1 = new Button();
        o2 = new Button();
        o3 = new Button();
        none = new Button("None");
        ques = new Button();
        correct = new AudioClip(new File("correct.mp3").toURI().toString());
        wrong = new AudioClip(new File("wrong.wav").toURI().toString());
        imageSource = new Image("brain.jpeg");

        BorderPane pane = new BorderPane();
        HBox hbox = addHBox();
        pane.setTop(hbox);
        pane.setLeft(addVBox());
        pane.setCenter(addGridPane());

        Scene scene = new Scene(pane, 500, 300);
        primaryStage.setTitle("Color Game!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Create 3 answer buttons at the top of the scene.
     * @return 3 answer buttons.
     */
    public HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 15, 15, 15));
        hbox.setSpacing(20);

        /*o1 = new Button();
        o2 = new Button();
        o3 = new Button();
        none = new Button("None");*/
        nextAns();

        o1.setOnMouseClicked(e -> {
            if (rand1.equals(ans)) {
                scoreT++;
                status = "Correct!";
                score.setText("Score: " + scoreT);
                description.setText(status);
                correct.play();
            } else {
                status = "Incorrect!";
                description.setText(status);
                scoreT = 0;
                score.setText("Score: " + scoreT);
                wrong.play();
            }
            nextAns();
            nextQues();
        });

        o2.setOnMouseClicked(e -> {
            if (rand2.equals(ans)) {
                scoreT++;
                status = "Correct!";
                score.setText("Score: " + scoreT);
                description.setText(status);
                correct.play();
            } else {
                status = "Incorrect!";
                description.setText(status);
                scoreT = 0;
                score.setText("Score: " + scoreT);
                wrong.play();
            }
            nextAns();
            nextQues();
        });

        o3.setOnMouseClicked(e -> {
            if (rand3.equals(ans)) {
                scoreT++;
                status = "Correct!";
                score.setText("Score: " + scoreT);
                description.setText(status);
                correct.play();
            } else {
                status = "Incorrect!";
                description.setText(status);
                scoreT = 0;
                score.setText("Score: " + scoreT);
                wrong.play();
            }
            nextAns();
            nextQues();
        });

        none.setOnMouseClicked(e -> {
            if (!rand3.equals(ans) && !rand2.equals(ans) && !rand1.equals(ans)) {
                scoreT++;
                status = "Correct! (none)";
                score.setText("Score: " + scoreT);
                description.setText(status);
                correct.play();
            } else {
                status = "Incorrect!";
                description.setText(status);
                scoreT = 0;
                score.setText("Score: " + scoreT);
                wrong.play();
            }
            nextAns();
            nextQues();
        });

        hbox.getChildren().addAll(o1, o2, o3, none);

        return hbox;
    }

    /**
     * Create a reset and a question buttons to the left of the stage.
     * @return a reset and a question buttons.
     */
    public VBox addVBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(15);

        Button reset = new Button("Reset");
        reset.setOnMouseClicked(e -> {
            status = "Choose an answer to begin!";
            description.setText(status);
            input = "None";
            name.setText("Name: " + input);
            scoreT = 0;
            score.setText("Score: " + scoreT);
            entry.setText("");
            entry.setPromptText("Enter your name here");
            nextAns();
            nextQues();
        });

        //ques = new Button();
        nextQues();

        vbox.getChildren().addAll(reset, ques);

        return vbox;
    }

    /**
     * Show the game and player information buttons at the center of the scene.
     * @return the game and player information buttons.
     */
    public GridPane addGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        entry = new TextField();
        entry.setPromptText("Enter your name here");
        grid.add(entry, 1, 1);

        name = new Text("Name: " + input);
        grid.add(name, 1, 0);

        Button enter = new Button("Enter");
        grid.add(enter, 2, 1);
        enter.setOnMouseClicked(e -> {
            input = entry.getText();
            name.setText("Name: " + input);
        });

        ImageView imageView = new ImageView(imageSource);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        grid.add(imageView, 3, 1);

        score = new Text("Score: " + scoreT);
        grid.add(score, 1, 2);

        description = new Text(status);
        grid.add(description, 1, 4);

        return grid;
    }

    /**
     * Allow for 3 answer buttons to be randomized each turn.
     */
    public static void nextAns() {
        colorW = new ArrayList<>();
        colorW.add("Red");
        colorW.add("Orange");
        colorW.add("Yellow");
        colorW.add("Green");
        colorW.add("Purple");
        Random rand = new Random();

        int choice1 = rand.nextInt(colorW.size());
        int choiceW1 = rand.nextInt(colorW.size());
        rand1 = colorW.get(choice1);
        String randW1 = colorW.get(choiceW1);
        o1.setStyle("-fx-background-color: " + rand1);
        o1.setText(randW1);

        int choice2 = rand.nextInt(colorW.size());
        int choiceW2 = rand.nextInt(colorW.size());
        rand2 = colorW.get(choice2);
        String randW2 = colorW.get(choiceW2);
        o2.setStyle("-fx-background-color: " + rand2);
        o2.setText(randW2);

        int choice3 = rand.nextInt(colorW.size());
        int choiceW3 = rand.nextInt(colorW.size());
        rand3 = colorW.get(choice3);
        String randW3 = colorW.get(choiceW3);
        o3.setStyle("-fx-background-color: " + rand3);
        o3.setText(randW3);
    }

    /**
     * Allow for the question button to be randomized each turn.
     */
    public static void nextQues() {
        /*colorW = new ArrayList<>();
        colorW.add("Red");
        colorW.add("Orange");
        colorW.add("Yellow");
        colorW.add("Green");
        colorW.add("Purple");*/
        Random rand = new Random();

        int choiceW = rand.nextInt(colorW.size());
        int choiceW2 = rand.nextInt(colorW.size());
        String randW = colorW.get(choiceW);
        String randW2 = colorW.get(choiceW2);
        ques.setStyle("-fx-background-color: " + randW);
        ques.setText(randW2);
        ans = randW2;
    }

    /**
     * Lauch the game.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}