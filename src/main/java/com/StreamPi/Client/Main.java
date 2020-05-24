package com.StreamPi.Client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        io io = new io();
        Scene s;
        HashMap<String,String> config = io.readConfig();
        if(!buildPlatform.equals(platform.ios) && !buildPlatform.equals(platform.android))
            s = new Scene(new dashboard(config), Integer.parseInt(config.get("width")), Integer.parseInt(config.get("height")));
        else
            s = new Scene(new dashboard(config));
        primaryStage.setScene(s);
        primaryStage.show();
    }

    enum platform{
        ios, android, raspberrypi, windows, mac, linux;
    }

    static platform buildPlatform = platform.windows;

    public static void main(String[] args) {
        System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
        System.setProperty("prism.allowhidpi", "true");
        launch(args);
    }
}
