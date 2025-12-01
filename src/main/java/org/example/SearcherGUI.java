  package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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
        parent.setAlignment(Pos.TOP_CENTER);
        parent.setPrefSize(400,200);
        parent.getChildren().add(MealTitle);
        Label urlArea = new Label("Enter Meal to Received the Recipe");
        urlArea.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        TextField textField = new TextField();
        textField.setAlignment(Pos.CENTER);
        parent.getChildren().add(urlArea);
        parent.getChildren().add(textField);


        //CUSTOM GUI
        BackgroundFill colors = new BackgroundFill(
                Color.GOLD,
                CornerRadii.EMPTY,
                Insets.EMPTY
        );
        Background background = new Background(colors);
        parent.setBackground(background);

        //Recipe Search Vbox
        Button button = new Button("Find Recipe");
        button.setPrefSize(150, 50);
        button.setOnAction(event -> {
            String input = textField.getText();
            VBox favoriteAddRoot = new VBox();
            ScrollPane ThescrollPane = new ScrollPane();
            ThescrollPane.setContent(favoriteAddRoot);
            ThescrollPane.setFitToWidth(true);
            Label TheIngredients = new Label(RecipeFormat.IngredientsGUI(input));
            TheIngredients.setFont(Font.font("Arial",FontWeight.LIGHT,12));
            TheIngredients.setWrapText(true);
            favoriteAddRoot.getChildren().add(TheIngredients);

            //CUSTOM GUI
            BackgroundFill AddColor = new BackgroundFill(
                    Color.ORANGE,
                    CornerRadii.EMPTY,
                    Insets.EMPTY
            );
            Background fillColor = new Background(AddColor);
            favoriteAddRoot.setBackground(fillColor);

            //Add Favorite Button
            Label Space = new Label("");
            favoriteAddRoot.getChildren().add(Space);
            Label AskFavorite = new Label("Would you like to add to favorites?");
            AskFavorite.setFont(Font.font("Arial",FontWeight.LIGHT,12));
            favoriteAddRoot.getChildren().add(AskFavorite);
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
            BorderPane ScrollFunction = new BorderPane();
            ScrollFunction.setCenter(ThescrollPane);
            Scene scene = new Scene(ScrollFunction, 600, 300);
            stage.setScene(scene);
            stage.show();
        });

        //Check Favorites Button
        Button button1 = new Button("Check Favorites");
        button1.setPrefSize(150, 50);
        button1.setOnAction(event2 -> {
            VBox FavoriteCheckRoot = new VBox();
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(FavoriteCheckRoot);
            scrollPane.setFitToWidth(true);

            //CUSTOM GUI
            BackgroundFill AddColor = new BackgroundFill(
                    Color.ORANGE,
                    CornerRadii.EMPTY,
                    Insets.EMPTY
            );
            Background fillColor = new Background(AddColor);
            FavoriteCheckRoot.setBackground(fillColor);

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
