package com.StreamPi.Client;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public abstract class dashboardBase extends BorderPane {

    protected StackPane alertStackPane;
    protected VBox actionsVBox;
    protected VBox goodbyePane;
    protected Label label;
    protected VBox loadingPane;
    protected ProgressIndicator mainSpinner;
    //protected ImageView mainSpinner;
    protected VBox settingsPane;
    protected HBox hBox;
    protected Label label0;
    protected HBox extraButtonsBar;
    protected Region region;
    protected Button closeSettingsButton;
    protected ImageView imageView0;
    protected HBox hBox0;
    protected Label label1;
    protected TextField serverIPField;
    protected Label label3;
    protected TextField serverPortField;
    protected HBox hBox2;
    protected Label label5;
    protected ToggleButton animationsToggleButton;
    protected Label label6;
    protected ToggleButton extraButtonsToggleButton;
    protected Label currentStatusLabel;
    protected Label unableToConnectReasonLabel;
    protected HBox settingsButtonsBar;
    protected Button applySettingsAndRestartButton;
    protected TextField displayWidthTextField;
    protected TextField displayHeightTextField;
    protected StackPane mainPane;

    public dashboardBase() {

        mainPane = new StackPane();

        alertStackPane = new StackPane();
        actionsVBox = new VBox();
        goodbyePane = new VBox();
        label = new Label();
        loadingPane = new VBox();
        loadingPane.setAlignment(Pos.CENTER);
        mainSpinner = new ProgressIndicator();
        mainSpinner.setProgress(-1);
        mainSpinner.setPrefSize(50,50);
        settingsPane = new VBox();
        hBox = new HBox();
        label0 = new Label();
        region = new Region();
        closeSettingsButton = new Button();
        imageView0 = new ImageView();
        hBox0 = new HBox();
        label1 = new Label();
        serverIPField = new TextField();
        label3 = new Label();
        serverPortField = new TextField();
        hBox2 = new HBox();
        label5 = new Label();
        animationsToggleButton = new ToggleButton();
        label6 = new Label();
        extraButtonsToggleButton = new ToggleButton();
        currentStatusLabel = new Label();
        unableToConnectReasonLabel = new Label();
        settingsButtonsBar = new HBox();
        applySettingsAndRestartButton = new Button();

        Font.loadFont(getClass().getResource("Roboto.ttf").toExternalForm().replace("%20"," "), 13);

        getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        getStyleClass().add("pane");

        actionsVBox.setAlignment(javafx.geometry.Pos.CENTER);
        VBox.setVgrow(actionsVBox,Priority.ALWAYS);
        actionsVBox.setCache(true);
        actionsVBox.setCacheHint(javafx.scene.CacheHint.SPEED);
        actionsVBox.getStyleClass().add("pane");

        goodbyePane.setAlignment(javafx.geometry.Pos.CENTER);
        goodbyePane.getStyleClass().add("pane");
        goodbyePane.setVisible(false);

        label.setText("Goodbye");

        loadingPane.setCache(true);
        loadingPane.setCacheHint(javafx.scene.CacheHint.SPEED);
        loadingPane.getStyleClass().add("pane");

        settingsPane.setCache(true);
        settingsPane.setCacheHint(javafx.scene.CacheHint.SPEED);
        settingsPane.setSpacing(10.0);
        settingsPane.getStyleClass().add("pane");

        //StackPane.setMargin(this, new Insets(15));
        setPadding(new Insets(15));

        label0.setText("Settings");
        //HBox.setMargin(label0, new Insets(15,0,0,0));
        label0.getStyleClass().add("h3");
        label0.getStyleClass().add("HeaderLabel");

        HBox.setHgrow(region, Priority.ALWAYS);

        closeSettingsButton.setCache(true);
        closeSettingsButton.setCacheHint(javafx.scene.CacheHint.SPEED);
        closeSettingsButton.setOnAction(this::closeSettingsButtonClicked);
        closeSettingsButton.setPrefHeight(60.0);
        closeSettingsButton.setPrefWidth(55.0);
        closeSettingsButton.setText(" ");

        imageView0.setFitHeight(35.0);
        imageView0.setFitWidth(48.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("cancel.png").toExternalForm()));
        closeSettingsButton.setGraphic(imageView0);

        hBox0.setSpacing(10.0);

        label1.setText("Server IP");

        serverIPField.setCache(true);
        serverIPField.setCacheHint(javafx.scene.CacheHint.SPEED);

        label3.setText("Port");

        serverPortField.setCache(true);
        serverPortField.setCacheHint(javafx.scene.CacheHint.SPEED);

        hBox2.setSpacing(10.0);

        animationsToggleButton.setCache(true);
        animationsToggleButton.setCacheHint(javafx.scene.CacheHint.SPEED);
        animationsToggleButton.setOnAction(this::animationsToggleButtonClicked);

        label5.setText("Animations");

        label6.setText("Extra Buttons");

        extraButtonsToggleButton.setCache(true);
        extraButtonsToggleButton.setCacheHint(javafx.scene.CacheHint.SPEED);
        extraButtonsToggleButton.setOnAction(event -> extraButtonsToggleButtonClicked());

        currentStatusLabel.setText("Current Status : NOT CONNECTED");

        unableToConnectReasonLabel.setText("Unable to Connect (<Localised Message>)");

        settingsButtonsBar.setAlignment(javafx.geometry.Pos.CENTER);
        settingsButtonsBar.setSpacing(25.0);

        applySettingsAndRestartButton.setCache(true);
        applySettingsAndRestartButton.setCacheHint(javafx.scene.CacheHint.SPEED);
        applySettingsAndRestartButton.setOnAction(this::applySettingsAndRestartButtonClicked);
        applySettingsAndRestartButton.setTextFill(Color.GREEN);
        applySettingsAndRestartButton.setText("Apply Settings And Restart");

        setOnSwipeUp(event->openSettings());

        mainPane.getChildren().add(alertStackPane);
        mainPane.getChildren().add(actionsVBox);
        goodbyePane.getChildren().add(label);
        mainPane.getChildren().add(goodbyePane);
        loadingPane.getChildren().add(mainSpinner);
        mainPane.getChildren().add(loadingPane);
        hBox.getChildren().add(label0);
        hBox.getChildren().add(region);
        hBox.getChildren().add(closeSettingsButton);
        settingsPane.getChildren().add(hBox);

        Button openSettingsButton = new Button("Open/Close Settings");
        Button returnToParentLayoutButton = new Button("Return to parent layout");

        extraButtonsBar = new HBox(openSettingsButton, returnToParentLayoutButton);
        extraButtonsBar.setStyle("-fx-background-color:red;");
        extraButtonsBar.setSpacing(10);


        Region rx = new Region();
        HBox.setHgrow(rx, Priority.ALWAYS);
        hBox0.getChildren().addAll(label1, serverIPField, rx, label3, serverPortField);
        hBox0.setAlignment(Pos.CENTER_LEFT);
        settingsPane.getChildren().add(hBox0);

        HBox hBox1 = new HBox();
        hBox1.setSpacing(10);
        Label l1 = new Label("Display Width");
        displayWidthTextField = new TextField();
        Region rx2 = new Region();
        Label l2 = new Label("Display Height");
        displayHeightTextField = new TextField();
        HBox.setHgrow(rx2, Priority.ALWAYS);
        hBox1.getChildren().addAll(l1,displayWidthTextField, rx2, l2,displayHeightTextField);

        if(Main.buildPlatform != Main.platform.android && Main.buildPlatform != Main.platform.ios)
        {
            settingsPane.getChildren().add(hBox1);
        }



        Region rw = new Region();
        HBox.setHgrow(rw, Priority.ALWAYS);

        hBox2.setAlignment(Pos.CENTER_LEFT);
        hBox2.getChildren().addAll(label5, animationsToggleButton, rw, label6, extraButtonsToggleButton);

        settingsPane.getChildren().addAll(hBox2);
        settingsPane.getChildren().add(currentStatusLabel);
        settingsPane.getChildren().add(unableToConnectReasonLabel);
        settingsButtonsBar.getChildren().add(applySettingsAndRestartButton);
        settingsPane.getChildren().add(settingsButtonsBar);
        mainPane.getChildren().add(settingsPane);

        loadingPane.toFront();

        settingsPane.setOpacity(0);
        actionsVBox.setOpacity(0);

        setCenter(mainPane);
        setTop(extraButtonsBar);
        extraButtonsBar.setMaxHeight(20);
        extraButtonsBar.toFront();
    }

    protected abstract void returnToParentLayerButtonClicked();

    protected abstract void openSettings();

    protected abstract void closeSettingsButtonClicked(javafx.event.ActionEvent actionEvent);

    protected abstract void animationsToggleButtonClicked(javafx.event.ActionEvent actionEvent);

    protected abstract void extraButtonsToggleButtonClicked();

    protected abstract void applySettingsAndRestartButtonClicked(javafx.event.ActionEvent actionEvent);

}
