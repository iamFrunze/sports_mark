package com.byfrunze.sportsball.activities.ui.webnews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.byfrunze.sportsball.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebInfo extends Fragment {
    @BindView(R.id.webView)
    WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (WebView) inflater.inflate(R.layout.fragment_web, webView, false);
        ButterKnife.bind(this, view);

        WebViewClient webViewClient = new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return super.shouldOverrideUrlLoading(view, request);
            }
        };
        webView.setWebViewClient(webViewClient);
        webView.loadUrl(getArguments().getString("URL"));
        return view;
    }
}
