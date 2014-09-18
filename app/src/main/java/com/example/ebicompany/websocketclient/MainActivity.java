package com.example.ebicompany.websocketclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_17;

import java.net.URI;
import java.net.URISyntaxException;


public class MainActivity extends Activity {

    HSClient client = null;
    URI serverUri;// = new URI("http://10.0.2.2:8092");

    public MainActivity()
    {
        super();
        //java.lang.System.setProperty("java.net.preferIPv6Addresses", "false");
        //java.lang.System.setProperty("java.net.preferIPv4Stack", "true");
        try {
            serverUri = new URI("http://10.0.2.2:8092");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (client == null){
            client = new HSClient(serverUri, (Draft)new Draft_17(), this);
            client.connect();
        }
    }

    @Override
    protected  void onDestroy()
    {
        client.close();
        super.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
