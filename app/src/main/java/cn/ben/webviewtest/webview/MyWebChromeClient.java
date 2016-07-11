package cn.ben.webviewtest.webview;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import cn.ben.webviewtest.MainActivity;

public class MyWebChromeClient extends WebChromeClient{
    @Override
    public View getVideoLoadingProgressView() {
        Log.d(MainActivity.TAG, "getVideoLoadingProgressView");
        return super.getVideoLoadingProgressView();
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        Log.d(MainActivity.TAG, "onReceivedTitle " + title);
        super.onReceivedTitle(view, title);
    }

    // 视频加载时的默认图
    @Override
    public Bitmap getDefaultVideoPoster() {
        Log.d(MainActivity.TAG, "getDefaultVideoPoster");
        return super.getDefaultVideoPoster();
    }

    // 加载进度回调
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        Log.d(MainActivity.TAG, "onProgressChanged " + newProgress);
        super.onProgressChanged(view, newProgress);
    }
}
