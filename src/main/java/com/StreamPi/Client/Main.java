package com.StreamPi.Client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class Main extends Application {

    static IO io;
    final static Platform buildPlatform = Platform.windows;

    @Override
    public void start(Stage primaryStage) throws Exception {
        io = new IO();

        Scene s;
        HashMap<String,String> config = io.readConfig();
        if(!buildPlatform.equals(Platform.ios) && !buildPlatform.equals(Platform.android))
            s = new Scene(new dashboard(config), Integer.parseInt(config.get("width")), Integer.parseInt(config.get("height")));
        else
            s = new Scene(new dashboard(config));
        primaryStage.setScene(s);
        primaryStage.show();
    }


    public static void main(String[] args) {
        System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
        System.setProperty("prism.allowhidpi", "true");
        launch(args);
    }
}
