package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SearcherGUI extends Application {
    ArrayList<String> Favorite = new ArrayList();
    RecipeFormat format = new RecipeFormat();
    private Stage Mainstage;


    @Override
    public void start(Stage stage) throws Exception {

        //Opening Search
        VBox parent = new VBox();
        Label MealTitle = new Label("The Meal Database");
        MealTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 24));
        parent.getChildren().add(MealTitle);
        HBox urlArea = new HBox(new Label("Enter Meal to Received Recipe"));
        TextField textField = new TextField();
        urlArea.getChildren().add(textField);
        parent.getChildren().add(urlArea);
        Button button = new Button("Find Recipe");

        //Recipe Search Vbox
        button.setOnAction(event -> {
            String input = textField.getText();
            VBox favoriteAddRoot = new VBox();
            favoriteAddRoot.getChildren().add(new Label(RecipeFormat.IngredientsGUI(input)));

            //Add Favorite Button
            favoriteAddRoot.getChildren().add(new Label("Would you like to add to favorites?"));
            Button button2 = new Button("Yes");
            button2.setOnAction(event2 -> {Favorite.add(RecipeFormat.IngredientsGUI(input)); stage.setScene(parent.getScene()); stage.show();});
            favoriteAddRoot.getChildren().add(button2);
            Button button3 = new Button("No");
            button3.setOnAction(event3 -> {stage.setScene(parent.getScene()); stage.show();});
            favoriteAddRoot.getChildren().add(button3);

            //Instructions Button
            Button button4 = new Button("Check Instructions");
            button4.setOnAction(eventnew ->{
                VBox instructionRoot = new VBox();
                ScrollPane scrollPane = new ScrollPane();
                scrollPane.setContent(instructionRoot);
                scrollPane.setFitToWidth(true);
                Label InstructionLabel = new Label(RecipeFormat.Instructions(input));
                InstructionLabel.setWrapText(true);
                instructionRoot.getChildren().add(InstructionLabel);

                //Go Back
                Button buttonBack = new Button("Go Back");
                buttonBack.setOnAction(event3 -> {stage.setScene(parent.getScene()); stage.show();});
                instructionRoot.getChildren().add(buttonBack);
                BorderPane ScrollFunction = new BorderPane();
                ScrollFunction.setCenter(scrollPane);
                Scene scene = new Scene(ScrollFunction, 600, 300);
                stage.setScene(scene);
                stage.show();
            });
            favoriteAddRoot.getChildren().add(button4);
            Scene scene = new Scene(favoriteAddRoot, 400, 300);
            stage.setScene(scene);
            stage.show();
        });

        //Check Favorites Button
        Button button1 = new Button("Check Favorites");
        button1.setOnAction(event2 -> {
            VBox FavoriteCheckRoot = new VBox();
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(FavoriteCheckRoot);
            scrollPane.setFitToWidth(true);
            if (Favorite.isEmpty()){
                FavoriteCheckRoot.getChildren().add(new Label("Nothing has been added"));
                Button button3 = new Button("Go Back");
                button3.setOnAction(event3 -> {stage.setScene(parent.getScene()); stage.show();});
                FavoriteCheckRoot.getChildren().add(button3);
                Scene scene = new Scene(FavoriteCheckRoot, 400, 300);
                stage.setScene(scene);
                stage.show();
            } else {
               for (int i = 0; Favorite.get(i) != null; i++) {
                   FavoriteCheckRoot.getChildren().add(new Label(Favorite.get(i)));
                   Button button3 = new Button("Go Back");
                   button3.setOnAction(event3 -> {
                       stage.setScene(parent.getScene());
                       stage.show();
                   });
                   FavoriteCheckRoot.getChildren().add(button3);
                   BorderPane ScrollFunction = new BorderPane();
                   ScrollFunction.setCenter(scrollPane);
                   Scene scene = new Scene(ScrollFunction, 600, 600);
                   stage.setScene(scene);
                   stage.show();
               }
            }
        });


        StackPane root = new StackPane(button);
        parent.getChildren().add(button);
        parent.getChildren().add(button1);
        stage.setScene(new Scene(parent));
        stage.show();
        Mainstage = stage;


    }
}
