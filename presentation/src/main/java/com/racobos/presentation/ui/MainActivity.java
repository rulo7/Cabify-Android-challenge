package com.racobos.presentation.ui;

import android.os.Bundle;

import com.racobos.presentation.navigation.Navigator;
import com.racobos.presentation.ui.bases.android.BaseActivity;

import javax.inject.Inject;

/**
 * Created by rulo7 on 07/10/2016.
 */
public class MainActivity extends BaseActivity {
    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigator.navigateToRateCalculator(this);
        finish();
    }
}
