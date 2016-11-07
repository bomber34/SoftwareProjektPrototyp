package com.example.markus.softwareprojektprototyp;


import android.os.Handler;
import android.os.Message;
import android.util.Log;


import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import static android.R.id.message;

public class MyServer {
    Thread m_objThread;
    ServerSocket m_server;
    String m_strMessage;
    DataDisplay m_DataDisplay;
    Object m_connected;
    String output;
    AccelData data = new AccelData();
    static Boolean isRunning = false;


    public MyServer(){

    }

    public void setEventListener(DataDisplay dataDisplay){
        m_DataDisplay = dataDisplay;
    }

    public void startListening(final Handler handler){

        m_objThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{

                    isRunning = true;
                    boolean showTime = true;
                    m_server = new ServerSocket(1337);



                    while(isRunning) {
                        Socket connectedSocket = m_server.accept();
                        if(showTime) {
                            handler.post(new CustomRunnable(String.valueOf(new Date().getTime())));
                            showTime = false;
                        }
                        //handler.post(new CustomRunnable("Connection successful"));

                       /* ObjectOutputStream oos = new ObjectOutputStream(connectedSocket.getOutputStream());
                        oos.writeObject(data.mSensorData);

                        oos.close(); */

                        Writer writer = new BufferedWriter(new OutputStreamWriter(connectedSocket.getOutputStream()));
                        writer.write(data.toString());

                        writer.close();

                        connectedSocket.close();


                    }

                    m_server.close();

                }
                catch(final Exception e){
                    handler.post(new CustomRunnable("Error occured: " + e.getMessage()));
                }
            }
        });

        m_objThread.start();
    }


    class CustomRunnable implements Runnable {
        private String msg;
        CustomRunnable(String msg){
            this.msg = msg;
        }

        @Override
        public void run() {
            m_DataDisplay.Display(msg);
        }
    }
}
