package cn.ben.webviewtest;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import cn.ben.webviewtest.webview.MyWebChromeClient;
import cn.ben.webviewtest.webview.MyWebViewClient;

/**
 * http://www.woaitqs.cc/android/2016/05/11/developing-with-webview#rd?sukey=3997c0719f1515203a565ebb377058c9352b0daadf592fddc1cdb506fe84a2d602b9d9e989dbeac01b8222ae249b9b5e
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "WebViewTest";
    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyWebViewClient webViewClient = new MyWebViewClient();
        MyWebChromeClient webChromeClient = new MyWebChromeClient();
        webView = (WebView) findViewById(R.id.webview);

        // 强大的调试工具
        /**
         * 打开相应的 WebView 界面
         * 打开相应的调试开关
         * 在 Chrome 中输入 chrome://inspect
         * 享受这个便捷功能吧，骚年！
         */
        if (BuildConfig.DEBUG && Build.VERSION.SDK_INT
                >= Build.VERSION_CODES.KITKAT) {
            Log.d(TAG, "Enable WebContents Debugging");
            WebView.setWebContentsDebuggingEnabled(true);
        }

        if (webView != null) {
            webView.setWebViewClient(webViewClient);
            webView.setWebChromeClient(webChromeClient);

            WebSettings webSettings = webView.getSettings();

            webSettings.setJavaScriptEnabled(true);

            // WebView 也需要对 PC 等宽的网页进行小的视频，不至于显示一塌糊涂
            // 通过设置这两个值，能够保证对 PC 等宽的页面也能良好显示
            webSettings.setUseWideViewPort(true);
            webSettings.setLoadWithOverviewMode(true);

            // 建议在实际开发的时候，使用LOAD_NO_CACHE，来避免一些缓存相关的bug
            webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

            // 在 5.0 系统上，无法显示Base64 的图片
//            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

            webView.loadUrl("http://www.html5tricks.com/demo/html5-flappy-text/index.html");

            // webview JS与本地交互
//            webView.addJavascriptInterface(jsPlugin, PLUGIN);

            // 判断网页是否可回滚
            Log.d(TAG, String.valueOf(webView.canGoBack()));

            // goBack 方法有问题。初始页面为A，点击某个链接跳转到B,B页面重定向到C页面。当调用webview.goBack()时，页面回退到B，然后接着会重定向回C页面
            // 依赖于 JS，通过 JS 的 API 可以实现对历史记录的管控
//            webView.loadUrl("javascript:window.history.back();");
        }
    }

    /**
     * 由于	WebView 本身占用着 plugins、 DOM 等资源，当它离开屏幕的时候，也应该释放其占有的 CPU 、网络资源。
     */
    @SuppressWarnings("unused")
    private void destroyWebView() {
        if (webView != null) {
            webView.removeAllViews();
            webView.destroy();
            webView = null;
        }
    }

    @SuppressWarnings("unused")
    private void pauseWebView() {
        if (webView == null) {
            return;
        }
        webView.onPause();
    }

    @SuppressWarnings("unused")
    private void resumeWebView() {
        if (webView == null) {
            return;
        }
        webView.onResume();
    }
}
