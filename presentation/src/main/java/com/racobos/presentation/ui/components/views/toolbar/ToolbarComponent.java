package com.racobos.presentation.ui.components.views.toolbar;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.racobos.domain.R;
import com.racobos.presentation.ui.components.views.ViewComponent;
import com.txusballesteros.mara.Trait;

/**
 * Created by raulcobos on 5/9/16.
 */
@Trait
public class ToolbarComponent implements ViewComponent {
    private AppCompatActivity appCompatActivity;
    private Toolbar toolbar;

    public ToolbarComponent(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    public void initialize() {
        if (appCompatActivity != null) {
            ViewGroup holderView = (ViewGroup) appCompatActivity.findViewById(R.id.toolbar_component);
            if (holderView != null) {
                toolbar = (Toolbar) LayoutInflater.from(appCompatActivity)
                        .inflate(R.layout.toolbar_component_view, holderView, false);
                holderView.addView(toolbar);
                appCompatActivity.setSupportActionBar(toolbar);
            }
        }
    }

    public void enableHomeAsUp() {
        if (appCompatActivity != null) {
            appCompatActivity.getSupportActionBar().setDisplayShowTitleEnabled(true);
            appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }
}
