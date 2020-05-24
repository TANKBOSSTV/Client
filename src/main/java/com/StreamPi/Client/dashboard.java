package com.StreamPi.Client;

import javafx.application.Platform;
import javafx.concurrent.Task;

import java.util.HashMap;

public class dashboard extends dashboardBase{
    io io;

    public dashboard(HashMap<String, String> config)
    {
        try {
            io = new io();

            setConfig(config);

            //load nodes
            loadNodes();

            setProgress(false);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectDisconnectButtonClicked() {

    }

    @Override
    public void onApplySettingsButtonClicked() {

    }

    @Override
    public void onRestartButtonClicked() {

    }

    @Override
    public void onShutdownButtonClicked() {

    }

    @Override
    public void onQuitButtonClicked() {

    }
}
