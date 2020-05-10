package com.StreamPi.Client;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;


public class Animate {

    Duration duration = Duration.millis(700);

    enum TransitionType{
        FADE_IN, FADE_OUT, SLIDE_DOWN, SLIDE_UP, SLIDE_RIGHT, SLIDE_LEFT
    }

    Node node;
    ParallelTransition parallelTransition;
    TransitionType transitionType;


    public Animate(Node node, TransitionType transitionType)
    {
        this.node = node;
        this.transitionType = transitionType;

        parallelTransition = new ParallelTransition();

        if(transitionType==TransitionType.FADE_IN || transitionType == TransitionType.FADE_OUT)
        {
            FadeTransition transition = new FadeTransition(duration);
            transition.setNode(node);
            if(transitionType == TransitionType.FADE_IN)
            {
                transition.setToValue(1.0);
                transition.setFromValue(0.0);
            }
            else {
                transition.setNode(node);
                transition.setToValue(0.0);
                transition.setFromValue(1.0);
            }
            parallelTransition.getChildren().add(transition);
        }
        else if(transitionType == TransitionType.SLIDE_UP || transitionType == TransitionType.SLIDE_DOWN)
        {
            TranslateTransition transition = new TranslateTransition(duration);
            transition.setNode(node);
            if(transitionType == TransitionType.SLIDE_UP)
            {
                transition.setToY(node.getScene().getHeight());
                transition.setFromY(0);
            }
            else {
                transition.setToY(0);
                transition.setFromY(node.getScene().getHeight());
            }
            parallelTransition.getChildren().add(transition);
        }
        else if(transitionType == TransitionType.SLIDE_LEFT || transitionType == TransitionType.SLIDE_RIGHT)
        {
            TranslateTransition transition = new TranslateTransition(duration);
            transition.setNode(node);
            if(transitionType == TransitionType.SLIDE_RIGHT)
            {
                transition.setToX(node.getScene().getWidth());
                transition.setFromX(0);
            }
            else {
                transition.setToX(0);
                transition.setFromX(node.getScene().getWidth());
            }
            parallelTransition.getChildren().add(transition);
        }

    }

    public void play()
    {
        parallelTransition.play();
    }

    public void setDelay(Duration duration)
    {
        parallelTransition.playFrom(duration);
    }

    public void setOnFinished(EventHandler<ActionEvent> actionEventEventHandler)
    {
        parallelTransition.setOnFinished(actionEventEventHandler);
    }
}
