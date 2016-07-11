package cn.ben.webviewtest.webview;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.ben.webviewtest.MainActivity;

/**
 * 负责处理网页相关的事件和异常
 */
public class MyWebViewClient extends WebViewClient {
    //几个重要的回调接口
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        Log.d(MainActivity.TAG, "onPageFinished " + url);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        Log.d(MainActivity.TAG, "onReceivedError " + request + " " + error);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        Log.d(MainActivity.TAG, "onPageStarted " + url + " " + favicon);
    }

    // 在这里可以忽略一些证书引起的错误，强制执行
    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        super.onReceivedSslError(view, handler, error);
        Log.d(MainActivity.TAG, "onReceivedSslError " + handler + " " + error);
    }
}
