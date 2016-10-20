package com.racobos.presentation.ui;

import com.karumi.dexter.Dexter;
import com.racobos.presentation.ui.bases.android.BaseApplication;

/**
 * Created by rulo7 on 07/10/2016.
 */

public class CabifyAndroidChallengeApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        initDexter();
    }

    private void initDexter() {
        Dexter.initialize(this);
    }


}
