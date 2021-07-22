package com.pchmn.ohosverify;

import ohos.aafwk.ability.AbilityPackage;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import ohos.app.Context;

/** A helper class to fetch application context.
 */
public class App extends AbilityPackage {

    private Context mContext;

    @Override
    public void onInitialize() {
        super.onInitialize();
        mContext = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
    }

    public Context getmContext() {
        return mContext;
    }
}
