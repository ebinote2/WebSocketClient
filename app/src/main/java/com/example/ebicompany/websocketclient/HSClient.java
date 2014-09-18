package com.example.ebicompany.websocketclient;

import android.app.Activity;
import android.widget.EditText;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Map;

/**
 * Created by ebicompany on 9/18/14.
 */
public class HSClient extends WebSocketClient {

    Activity context;
    EditText serverMessageArea;

    public HSClient(URI serverURI, Activity ctx) {
        super(serverURI);
        this.context = ctx;
        this.serverMessageArea = (EditText)context.findViewById(R.id.editText);
    }

    public HSClient(URI serverUri, Draft draft, Activity ctx) {
        super(serverUri, draft);
        this.context = ctx;
        this.serverMessageArea = (EditText)context.findViewById(R.id.editText);
    }

    public HSClient(URI serverUri, Draft draft, Map<String, String> headers, Activity ctx) {
        super(serverUri, draft, headers);
        this.context = ctx;
        this.serverMessageArea = (EditText)context.findViewById(R.id.editText);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        int x = 100;
        x++;
    }

    @Override
    public void onMessage(String s) {
        final String message = s;
        if (s.equals("0")){
            this.close();
            return;
        }
        serverMessageArea.post(new Runnable() {
            @Override
            public void run() {
                serverMessageArea.setText(message);
            }
        });
    }

    @Override
    public void onClose(int i, String s, boolean b) {

    }

    @Override
    public void onError(Exception e) {

    }
}
