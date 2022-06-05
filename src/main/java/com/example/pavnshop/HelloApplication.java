package com.example.pavnshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Класс HelloApplication
 * <p>
 * Данный класс запускает программу.
 * В дальнейшем все методы могут быть изменены.
 * @author Автор Львова Ксения
 * @version 1.3
 */
public class HelloApplication extends Application {
    public Path path1;
    /** Текущее окно*/
    private static Stage primaryStage;
    /** Окно для загрузки*/
    public static Stage stage;
    /**
     * Метод запускает приложение.
     * @param stage окно запуска
     */
    @Override
    public void start(Stage stage) throws IOException {
        String path =  new File("").getAbsolutePath();


        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}