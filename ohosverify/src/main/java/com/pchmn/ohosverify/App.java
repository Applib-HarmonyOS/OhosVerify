package com.pchmn.ohosverify;

import ohos.app.Context;

/** A helper class to fetch application context.
 */
public class App {

    Context mContext;
    private volatile static App app;

    private App() {

    }

    public void setContext(Context context) {
        mContext = context;
    }

    /**
     * Gets an instance of App class.
     */
    public static App getInstance() {
        if (app == null) {
            synchronized (App.class) {
                app = new App();
            }
        }
        return app;
    }

    public Context getContext() {
        return mContext;
    }

}
