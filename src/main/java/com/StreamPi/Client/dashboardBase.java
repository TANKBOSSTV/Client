/*
dashboardBase.java

UI Logic for StreamPi Client

Author(s) : Debayan Sutradhar (dubbadhar)

Last Edited on 24th May 2020
*/

package com.StreamPi.Client;

import animatefx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.HashMap;

public abstract class dashboardBase extends BorderPane {

    StackPane actionsPane;
    VBox settingsPane, goodbyePane, loadingPane;

    StackPane mainPane;
    private HashMap<String,String> config;
    private Button settingsButton;

    HBox bottomButtonBar;
    public void loadNodes()
    {
        //runs after config has been loaded

        //Load font
        Font.loadFont(getClass().getResource("Roboto.ttf").toExternalForm().replace("%20",""), 13);

        //apply stylesheet
        getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        //add style "pane" for every pane (dashboardBase itself is a pane with many panes)
        getStyleClass().add("pane");
        //apply current theme
        setStyle("bg-color: "+config.get("bg-color")+";font-color: "+config.get("font-color")+";");



        //init of base nodes

        //bottomButtonBar - will contain the Settings Button
        bottomButtonBar = new HBox();
        bottomButtonBar.getStyleClass().add("pane");
        bottomButtonBar.setAlignment(Pos.CENTER_RIGHT);
        settingsButton = new Button();
        settingsButton.setGraphic(getIcon("gmi-settings"));
        settingsButton.setOnMouseClicked(event -> {
            if(currentPane == PANE.settings)
            {
                closeSettings();
                currentPane = PANE.actions;
            }
            else
                switchPane(PANE.settings);
        });
        bottomButtonBar.getChildren().add(settingsButton);



        //mainPane - will contain actionsPane, settingsPane and also goodbyePane
        mainPane = new StackPane();
        mainPane.setPadding(new Insets(10,10,0,10));
        mainPane.getStyleClass().add("pane");

        //Init children of mainPane
        actionsPane = new StackPane(); // Where actions will be stored
        actionsPane.getStyleClass().add("pane"); //Adding stylesheet class to actionsPane

        settingsPane = getSettingsPane(); // Will contain Settings

        goodbyePane = getGoodbyePane(); // Will contain goodbye message

        loadingPane = getLoadingPane(); // Will contain loading spinner and a message

        //add children of mainPane to mainPane
        mainPane.getChildren().addAll(actionsPane, settingsPane, goodbyePane, loadingPane);



        //set all the children nodes opacity to 0
        //actionsPane.setOpacity(0);
        //settingsPane.setOpacity(0);
        //goodbyePane.setOpacity(0);
        //loadingPane.setOpacity(0);
        //settingsPane.toFront();

        //enable caching to improve performance (Especially on embedded and Mobile)
        actionsPane.setCache(true);
        actionsPane.setCacheHint(CacheHint.SPEED);

        settingsPane.setCache(true);
        settingsPane.setCacheHint(CacheHint.SPEED);

        loadingPane.setCache(true);
        loadingPane.setCacheHint(CacheHint.SPEED);

        goodbyePane.setCache(true);
        goodbyePane.setCacheHint(CacheHint.SPEED);

        settingsButton.setCache(true);
        settingsButton.setCacheHint(CacheHint.SPEED);

        setCache(true);
        setCacheHint(CacheHint.SPEED);

        //finally add base nodes to the base Scene
        setCenter(mainPane);
        setBottom(bottomButtonBar);
    }

    private TextField serverIPTextField, serverPortTextField, deviceNicknameTextField;
    private Button connectDisconnectButton;
    private VBox getSettingsPane()
    {
        //This returns the entire Settings Pane node

        //base pane for the nodes in settings pane
        VBox settingsVBox = new VBox();
        settingsVBox.getStyleClass().add("pane");
        settingsVBox.setSpacing(5);

        //1st row, contains settings heading and the close button
        HBox row0 = new HBox();
        Label headingLabel = new Label("Settings");
        headingLabel.getStyleClass().add("h2");
        headingLabel.setPadding(new Insets(0,0,10,0));
        Region r0 = new Region();
        HBox.setHgrow(r0, Priority.ALWAYS);
        Button settingsCloseButton = new Button();
        settingsCloseButton.setGraphic(new ImageView());
        settingsCloseButton.setOnMouseClicked(event -> closeSettings());

        row0.getChildren().addAll(headingLabel, r0, settingsCloseButton);

        //2nd row, contains server ip text field
        HBox row1 = new HBox();
        row1.setSpacing(5);
        Label serverIPLabel = new Label("Server IP");
        Region r1 = new Region();
        HBox.setHgrow(r1,Priority.ALWAYS);
        serverIPTextField = new TextField();
        row1.getChildren().addAll(serverIPLabel, r1, serverIPTextField);

        //3rd row, contains server port text field
        HBox row2 = new HBox();
        row2.setSpacing(5);
        Label serverPortLabel = new Label("Server Port");
        Region r2 = new Region();
        HBox.setHgrow(r2,Priority.ALWAYS);
        serverPortTextField = new TextField();
        row2.getChildren().addAll(serverPortLabel, r2, serverPortTextField);

        //4th row, contains device nickname text field
        HBox row3 = new HBox();
        row3.setSpacing(5);
        Label deviceNicknameLabel = new Label("Device Nickname");
        Region r3 = new Region();
        HBox.setHgrow(r3, Priority.ALWAYS);
        deviceNicknameTextField = new TextField();
        row3.getChildren().addAll(deviceNicknameLabel, r3, deviceNicknameTextField);

        //5th row, contains the buttons for settings
        HBox row4 = new HBox();
        row4.setAlignment(Pos.CENTER);
        row4.setSpacing(10);

        connectDisconnectButton = new Button("Connect");
        connectDisconnectButton.getStyleClass().add("btn-green");
        //assign onclick to the button
        connectDisconnectButton.setOnMouseClicked(event -> onConnectDisconnectButtonClicked());

        Button applySettingsButton = new Button("Apply Settings");
        applySettingsButton.getStyleClass().add("btn-green");
        //assign onclick to the button
        applySettingsButton.setOnMouseClicked(event -> onApplySettingsButtonClicked());

        row4.getChildren().addAll(connectDisconnectButton, applySettingsButton);

        //6th row, contains the buttons for settings
        HBox row5 = new HBox();
        row5.setAlignment(Pos.CENTER);
        row5.setSpacing(10);
        Button restartButton = new Button("Restart");
        restartButton.getStyleClass().add("btn-orange");
        restartButton.setOnMouseClicked(event -> onRestartButtonClicked());

        Button shutdownButton = new Button("Shutdown");
        shutdownButton.getStyleClass().add("btn-orange");
        shutdownButton.setOnMouseClicked(event -> onShutdownButtonClicked());

        Button quitButton = new Button("Quit");
        quitButton.getStyleClass().add("btn-red");
        quitButton.setOnMouseClicked(event -> onQuitButtonClicked());

        row5.getChildren().addAll(restartButton, shutdownButton, quitButton);

        //add the children to the settings vbox, and return it
        settingsVBox.getChildren().addAll(row0, row1, row2, row3, row4, row5);
        return settingsVBox;
    }

    private FontIcon getIcon(String iconLiteral)
    {
        //Gets icons from ikonli library. Icon cheat sheet : https://kordamp.org/ikonli/cheat-sheet-material.html
        FontIcon fontIcon = new FontIcon();
        fontIcon.setIconLiteral(iconLiteral);
        fontIcon.setIconColor(Paint.valueOf(config.get("font-color")));
        fontIcon.setFont(Font.font(30));
        return fontIcon;
    }

    private VBox getGoodbyePane()
    {
        //This returns the entire goodbye pane node

        //Base pane
        VBox goodbyePane = new VBox();
        goodbyePane.setAlignment(Pos.CENTER);
        goodbyePane.getStyleClass().add("pane");

        //big goodbye label (Thanks to windows phone)
        Label goodbyeLabel = new Label("Goodbye");
        goodbyeLabel.setFont(Font.font(20));

        //add them and return the node
        goodbyePane.getChildren().add(goodbyeLabel);
        return goodbyePane;
    }

    private ProgressIndicator loadingIndicator;
    private Label loadingInfoLabel;
    private VBox getLoadingPane()
    {
        //This returns the loading screen

        //Base pane
        VBox loadingPane = new VBox();
        loadingPane.setSpacing(5);
        loadingPane.setAlignment(Pos.CENTER);

        //Loading Indicator (will be customised to match with the design language later)
        loadingIndicator = new ProgressIndicator(0);
        //enable caching to improve performance
        loadingIndicator.setCache(true);
        loadingIndicator.setCacheHint(CacheHint.SPEED);

        //Loading info Label
        loadingInfoLabel = new Label();

        //Add them and return the node
        loadingPane.getChildren().addAll(loadingIndicator, loadingInfoLabel);
        return loadingPane;
    }

    public enum PANE
    {
        actions, settings, goodbye, loading
    }

    private PANE currentPane = null;

    public void switchPane(PANE requestedPane)
    {
        //This switches the current Pane

        //Perform check if the current pane is already the same as the requested pane
        if(currentPane == requestedPane)
            io.pln("Already switched to "+requestedPane+"!");
        else
        {
            io.pln("Switching from "+currentPane+" to "+requestedPane+" ...");

            // If Settings open, close it
            if (currentPane == PANE.settings) {
                closeSettings();
            }

            //Now perform required animations
            switch (requestedPane)
            {
                case actions:
                    new FadeIn(actionsPane).play(); // Fade In Animation
                    actionsPane.toFront();
                    break;
                case goodbye:
                    new FadeIn(goodbyePane).play(); // Fade In Animation
                    goodbyePane.toFront();
                    break;
                case loading:
                    new FadeIn(loadingPane).play(); // Fade In Animation
                    loadingPane.toFront();
                    break;
                case settings:
                    new SlideInUp(settingsPane).play(); //Sliding Animation for Settings Pane
                    settingsButton.setGraphic(getIcon("gmi-close"));
                    settingsPane.toFront();
            }

            //Finally, set current pane as requested pane
            currentPane = requestedPane;

            io.pln("... Done!");
        }
    }

    private void closeSettings()
    {
        //Set Button Icon to normal
        settingsButton.setGraphic(getIcon("gmi-settings"));

        //Closes settings with Slide Down Animation
        SlideOutDown d = new SlideOutDown(settingsPane);
        //d.setOnFinished(event -> settingsPane.toBack());
        d.play();
    }

    //setProgress Methods for setting the required value and info
    public void setProgress(String info, boolean isLoading)
    {
        if(isLoading) loadingIndicator.setProgress(-1);
        else loadingIndicator.setProgress(0);

        loadingInfoLabel.setText(info);
    }

    public void setProgress(boolean isLoading)
    {
        if(isLoading) loadingIndicator.setProgress(-1);
        else loadingIndicator.setProgress(0);
    }

    public void setProgress(String info)
    {
        loadingInfoLabel.setText(info);
    }

    public void setConnectDisConnectButtonStatus(boolean isConnected)
    {
        //This method changes font colour of connectDisconnectButton when connected/disconnected
        if(isConnected)
        {
            connectDisconnectButton.setText("Disconnect");
            connectDisconnectButton.getStyleClass().remove("btn-green");
            connectDisconnectButton.getStyleClass().add("btn-red");
        }
        else
        {
            connectDisconnectButton.setText("Connect");
            connectDisconnectButton.getStyleClass().remove("btn-red");
            connectDisconnectButton.getStyleClass().add("btn-green");
        }
    }

    private boolean currentSettingsButtonStatus = true;
    public void setSettingsButtonStatus(boolean show)
    {
        //This method hides/shows the Settings Button in the bottom left
        if(currentSettingsButtonStatus!=show)
        {
            currentSettingsButtonStatus = show;
            AnimationFX s;
            if(show)
            {
                s = new SlideInUp(settingsButton);
                s.setOnFinished(event -> settingsButton.setDisable(false));
            }
            else
            {
                s = new SlideOutDown(settingsButton);
                s.setOnFinished(event -> settingsButton.setDisable(true));
            }
            s.play();
        }
    }

    //Getter methods for text fields
    public String getServerIPFieldText()
    {
        return serverIPTextField.getText();
    }

    public String getServerPortFieldText()
    {
        return serverPortTextField.getText();
    }

    public String getDeviceNicknameFieldText()
    {
        return deviceNicknameTextField.getText();
    }

    //getter for config
    public String getConfigValue(String key)
    {
        return config.get(key);
    }

    //setters for config
    public void setConfigValue(String key, String value)
    {
        config.put(key, value);
    }

    public void setConfig(HashMap<String, String> config)
    {
        this.config = config;
    }

    //Abstract on clicked methods for the main dashboard Class
    public abstract void onConnectDisconnectButtonClicked();
    public abstract void onApplySettingsButtonClicked();
    public abstract void onRestartButtonClicked();
    public abstract void onShutdownButtonClicked();
    public abstract void onQuitButtonClicked();
}
