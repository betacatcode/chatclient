package com.ruin.window;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author ruin
 * @date 2020/1/24-11:05
 */
public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root= FXMLLoader.load(getClass().getResource("/main.fxml"));
        Scene scene=new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
