package com.racobos.presentation.ui.components.views.progressbar;

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

    private View rootView;
    private ProgressBar progressBar;

    public ProgressBarComponent(View rootView) {
        this.rootView = rootView;
    }

    @Override
    public void initialize() {
        if (rootView != null) {
            ViewGroup holderView = (ViewGroup) rootView.findViewById(R.id.progress_bar_component);
            if (holderView != null) {
                progressBar = (ProgressBar) LayoutInflater.from(rootView.getContext())
                        .inflate(R.layout.progress_bar_component_view, holderView, false);
                holderView.addView(progressBar);
            }
        }
    }

    public void showProgress() {
        if (progressBar != null)
            progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        if (progressBar != null)
            progressBar.setVisibility(View.GONE);
    }

    public void invisibleProgress() {
        if (progressBar != null)
            progressBar.setVisibility(View.INVISIBLE);
    }
}
