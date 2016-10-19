package com.example.markus.softwareprojektprototyp;


import android.os.Handler;
import android.os.Message;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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

    public void startListening(){

        m_objThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    m_server = new ServerSocket(8080);
                    Socket connectedSocket = m_server.accept();

                    Message clientMessage = Message.obtain();

                    ObjectInputStream ois = new ObjectInputStream(connectedSocket.getInputStream());
                    String strMessage = (String) ois.readObject();
                    clientMessage.obj = strMessage;
                    mHandler.sendMessage(clientMessage);

                    ObjectOutputStream oos = new ObjectOutputStream(connectedSocket.getOutputStream());
                    oos.writeObject("Haifisch");
                    ois.close();
                    oos.close();
                    m_server.close();

                }
                catch(Exception e){
                    Message msg3 = Message.obtain();
                    msg3.obj=e.getMessage();
                    mHandler.sendMessage(msg3);
                }
            }
        });

        m_objThread.start();
    }
    Handler mHandler = new Handler(){
        public void handleMessage(Message status){
            m_DataDisplay.Display(status.obj.toString());
        }
    };

}
