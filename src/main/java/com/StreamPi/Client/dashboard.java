package com.StreamPi.Client;

import java.util.HashMap;

public class dashboard extends dashboardBase{
    IO io;

    boolean isConnected = false;

    public dashboard(HashMap<String, String> config)
    {
        try {
            io = new IO();

            setConfig(config);

            //load nodes
            loadNodes();

            new Client(config, this).run();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void getActions()
    {
        
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
