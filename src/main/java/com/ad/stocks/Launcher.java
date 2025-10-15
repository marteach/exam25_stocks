package com.ad.stocks;

import com.ad.interfaces.IControllerWithLifeCycle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Launcher extends Application {

    //Java entry point
    public static void main(String[] args) {
        Application.launch(Launcher.class, args);
    }

    //JavaFX entry point
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        //This gives you access to the controller after it has been loaded (after initialize method have been called)
        //Can be used to do additional setup or "late injections" ...
        //Use it if you need or feel like it!
        IControllerWithLifeCycle activeViewController = fxmlLoader.getController();

        stage.setTitle("Stock watcher!");
        stage.setScene(scene);
        stage.setOnCloseRequest((WindowEvent e) -> {
            Platform.exit();
            System.exit(0);
        });
        stage.show();
    }
}
