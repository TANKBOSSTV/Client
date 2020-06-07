package com.StreamPi.Client;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;

public class Client implements Runnable{

    dashboard dash;
    Socket s;
    DataInputStream dis;
    DataOutputStream dos;

    String deviceNickName;
    int clientVersion;

    int port;
    String ip;
    public Client(HashMap<String,String> config, dashboard dash)
    {
        this.dash = dash;
        port = Integer.parseInt(config.get("server-port"));
        ip = config.get("server-ip");
        deviceNickName = config.get(config.get("device-nickname"));
        clientVersion = Integer.parseInt(config.get(config.get("client-version")));
    }


    @Override
    public void run()
    {
        //Starting the socket and init input and output streams
        connectToServer();

        if(connectionStatus == ConnectionStatus.NOT_CONNECTED)
            return;

        //Connected successfully
        sendClientInformation();
    }

    ConnectionStatus connectionStatus = ConnectionStatus.NOT_CONNECTED;

    public void connectToServer()
    {
        try
        {
            s = new Socket();
            s.connect(new InetSocketAddress(ip, port), 2500);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
            connectionStatus = ConnectionStatus.CONNECTED;
            Platform.runLater(()-> dash.setCurrentStatus("Connected to "+ip+":"+port, connectionStatus));
        }
        catch (Exception e)
        {
            connectionErrorOccured(e);
        }
    }

    public void sendClientInformation()
    {
        dos.writeUTF(deviceNickName+"::"+clientVersion+"::");
    }

    public void connectionErrorOccured(Exception e)
    {
        connectionStatus = ConnectionStatus.NOT_CONNECTED;
        System.out.println("Error occurred!");
        Platform.runLater(()-> dash.setCurrentStatus("Unable to connect. "+e.getLocalizedMessage(), ConnectionStatus.CONNECTED));
        e.printStackTrace();
    }
}
