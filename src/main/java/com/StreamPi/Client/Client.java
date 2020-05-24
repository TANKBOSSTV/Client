package com.StreamPi.Client;

import javafx.application.Platform;
import javafx.scene.paint.Paint;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client implements Runnable{

    dashOld dash;
    Socket s;
    BufferedReader br;
    BufferedWriter bw;

    int port;
    String ip;
    public Client(int port, String ip, dashOld dash)
    {
        this.dash = dash;
        this.port = port;
        this.ip = ip;


    }


    @Override
    public void run() {
        //Starting the socket and init input and output streams
        try
        {
            s = new Socket();
            s.connect(new InetSocketAddress(ip, port), 2500);
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

            Platform.runLater(()->{
                dash.currentStatusLabel.setTextFill(Paint.valueOf("#008000"));
                dash.currentStatusLabel.setText("Current Status :  CONNECTED to " + ip + ":" + port);
                dash.unableToConnectReasonLabel.setText("");
            });
        }
        catch (Exception e)
        {

            e.printStackTrace();
        }

        //Connected successfully
    }
}
