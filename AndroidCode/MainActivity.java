package com.example.markus.softwareprojektprototyp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements DataDisplay {

    TextView textView;
    Button startServer;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        startServer = (Button) findViewById(R.id.startServer);

    }

    public void connect(View view){
        MyServer server = new MyServer();
        server.setEventListener(this);
        server.startListening();
    }

    public void Display(String message){
        textView.setText(message);
    }
}
