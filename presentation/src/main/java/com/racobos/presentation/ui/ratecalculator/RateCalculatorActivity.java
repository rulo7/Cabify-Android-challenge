package com.racobos.presentation.ui.ratecalculator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.racobos.domain.R;
import com.racobos.presentation.ui.bases.android.BaseActivity;

/**
 * Created by rulo7 on 08/10/2016.
 */

public class RateCalculatorActivity extends BaseActivity {

    private Mara_RateCalculatorComposer composer;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, RateCalculatorActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_calculator);
        setupComponents();
    }

    private void setupComponents() {
        composer = new Mara_RateCalculatorComposer.Builder().build();
        composer.initialize();
        composer.enableHomeAsUp();
    }
}