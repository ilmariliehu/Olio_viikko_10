package com.example.week10;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {

    WebView web;
    EditText search;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web = findViewById(R.id.webview);
        search = findViewById(R.id.editText);


        web.setWebViewClient(new WebViewClient());
        web.getSettings().setJavaScriptEnabled(true);

        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_ENTER:
                            url = search.getText().toString();
                            if(url.equals("index.html")){
                                web.loadUrl("file:///android_asset/" + url);
                                return true;
                            }else {
                                web.loadUrl("http://" + url);
                                return true;
                            }
                        default:
                            break;
                    }
                }
                return false;
            }
        });
    }

    public void refresh(View v){
        web.reload();

    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void muutos1(View v){
        web.evaluateJavascript("javascript:shoutOut()", null);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void muutos2(View v){
        web.evaluateJavascript("javascript:initialize()", null);
    }
    public void previous(View v){
        if (web.canGoBack()) {
            web.goBack();
        }
    }
    public void next(View v){
        if (web.canGoForward()) {
            web.goForward();
        }
    }


}
