package com.racobos.presentation.ui.components.views.progressbar;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.racobos.domain.R;
import com.racobos.presentation.ui.components.views.ViewComponent;
import com.txusballesteros.mara.Trait;

/**
 * Created by raulcobos on 13/9/16.
 */
@Trait
public class ProgressBarComponent implements ViewComponent {

    private Context context;
    private ProgressBar progressBar;

    public ProgressBarComponent(Context context) {
        this.context = context;
    }

    @Override
    public void initialize() {
        if (context instanceof AppCompatActivity) {
            AppCompatActivity rootActivity = (AppCompatActivity) context;
            ViewGroup holderView = (ViewGroup) rootActivity.findViewById(R.id.progress_bar_component);
            if (holderView != null) {
                progressBar = (ProgressBar) LayoutInflater.from(context)
                        .inflate(R.layout.progress_bar_component_view, holderView, false);
                holderView.addView(progressBar);
            }
        }
    }

    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    public void invisibleProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
