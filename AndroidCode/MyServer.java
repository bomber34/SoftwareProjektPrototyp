package com.example.markus.softwareprojektprototyp;


import android.os.Handler;
import android.os.Message;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static android.R.id.message;

public class MyServer {
    Thread m_objThread;
    ServerSocket m_server;
    String m_strMessage;
    DataDisplay m_DataDisplay;
    Object m_connected;

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


                    m_server = new ServerSocket(8080);
                    Socket connectedSocket = m_server.accept();

                    handler.post(new CustomRunnable("Connection successful"));

                    ObjectOutputStream oos = new ObjectOutputStream(connectedSocket.getOutputStream());
                    oos.writeObject("Haifisch");
                    oos.close();
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
