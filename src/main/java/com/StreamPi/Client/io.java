package com.StreamPi.Client;

import javafx.scene.image.Image;

import java.io.*;
import java.util.HashMap;

public class io {
    String location;
    public io() throws Exception
    {
        if(Main.buildPlatform == Main.platform.android)
        {
            location = "/storage/emulated/0/Android/in.dubbadhar.StreamPiClient/";
        }
        else
        {
            location = "";
        }

        File actionsFolder = new File(location+"actions/");
        if(!actionsFolder.exists())
        {
            if(!actionsFolder.mkdirs()) throw new Exception("Unable to create Actions Folder");
            File detailsFolder = new File(location+"actions/details/");
            if(!detailsFolder.mkdirs()) throw new Exception("Unable to create Details Folder");
            File iconsFolder = new File(location+"actions/icons/");
            if(!iconsFolder.mkdirs()) throw new Exception("Unable to create Icons Folder");


            if(!new File(location+"config").exists())
            {
                if(!new File(location+"config").createNewFile()) throw new Exception("Unable to Create Config");
                writeToFile("800::480::192.168.0.108::23::StreamPi Client::1::1::105::10::","config");
            }
        }
    }

    public String readFileRaw(String fileName) throws Exception
    {
        StringBuilder toBeReturned = new StringBuilder();
        FileReader fileReader = new FileReader(fileName);
        int c;
        while((c=fileReader.read())>-1)
        {
            toBeReturned.append((char) c);
        }
        fileReader.close();
        return toBeReturned.toString();
    }

    public boolean deleteFile(String loc)
    {
        return new File(location+loc).delete();
    }

    public String[] listFiles(String loc)
    {
        return new File(location+loc).list();
    }

    public Image returnImage(String loc)
    {
        return new Image(new File(location+loc).toURI().toString());
    }

    public String[] readFileArranged(String fileName, String s) throws Exception
    {
        return readFileRaw(fileName).split(s);
    }

    public byte[] returnBytesFromFile(String loc) throws Exception
    {
        FileInputStream fs = new FileInputStream(location+loc);
        byte[] imageB = fs.readAllBytes();
        fs.close();
        return imageB;
    }

    public void writeToFile(String content, String fileName) throws Exception
    {
        BufferedWriter bf = new BufferedWriter(new FileWriter(location+fileName));
        bf.write(content);
        bf.close();
    }

    public void writeToFileRaw(byte[] toWrite, String fileName) throws Exception
    {
        FileOutputStream fs = new FileOutputStream(location+fileName);
        fs.write(toWrite);
        fs.close();
    }

    public void pln(String txt)
    {
        System.out.println(txt);
    }

    String configPropFileLoc = io.class.getResource("config.properties").toExternalForm().substring(5);

    public HashMap<String,String> readConfig() throws Exception
    {
        HashMap<String,String> toReturn = new HashMap<>();

        String content = readFileRaw(configPropFileLoc);
        String splitChar = "\n";
        if(content.contains("\r\n")) splitChar = "\r\n";
        for(String eachLine : content.split(splitChar))
        {
            String[] confPart = eachLine.split(" = ");
            toReturn.put(confPart[0],confPart[1]);
            System.out.println("\""+confPart[0]+"\" : \""+confPart[1]+"\"");
        }

        return toReturn;
    }

    public void updateConfig(String key, String value) throws Exception
    {
        HashMap<String,String> config = readConfig();
        config.replace(key,value);
        StringBuilder toBeWritten = new StringBuilder();
        for(String configKey : config.keySet())
        {
            toBeWritten.append(configKey).append(" = ").append(config.get(configKey)).append("\n");
        }
        writeToFile(toBeWritten.toString(),configPropFileLoc);
    }
}
