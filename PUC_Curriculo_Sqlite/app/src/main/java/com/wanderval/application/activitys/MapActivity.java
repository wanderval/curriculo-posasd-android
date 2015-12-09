package com.wanderval.application.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wanderval.application.R;

public class MapActivity extends AppCompatActivity {
    WebView myWebView;

    //String mapPath = "https://maps.google.com/?ll=37.0625,-95.677068&spn=29.301969,56.513672&t=h&z=4";
    String mapPath = "http://maps.google.com/maps?q=loc:-22.222581,-45.938787";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Pouso Alegre, MG");
        setContentView(R.layout.activity_map);

        myWebView = (WebView)findViewById(R.id.mapview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());

        myWebView.loadUrl(mapPath);
    }
}

