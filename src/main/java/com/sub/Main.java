package com.sub;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage;
    public  Scene scene;

    @Override    public void start(Stage primaryStage) throws Exception{
        this.stage=primaryStage;
        mainWindow();
        System.out.println("done!!!");
    }
    public void mainWindow() {
        try {
            FXMLLoader loader = new FXMLLoader( Main.class.getResource( "/start.fxml" ));
            BorderPane pane = loader.load();
            scene = new Scene(pane);
            //scene.getStylesheets().add( Main.class.getResource("/style.css").toExternalForm());
            stage.setScene(scene);
          //  stage.initStyle( StageStyle.DECORATED.UNDECORATED );
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
