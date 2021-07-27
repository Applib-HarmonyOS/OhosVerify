package com.pchmn.ohosverify;

import ohos.app.Context;

/** A helper class to fetch application context.
 */
public class App {

    private static final App app = new App();
    Context mContext;

    private App() {

    }

    public void setContext(Context context) {
        mContext = context;
    }

    /**
     * Gets an instance of App class.
     */
    public static App getInstance() {
        return app;
    }

    public Context getContext() {
        return mContext;
    }

}
