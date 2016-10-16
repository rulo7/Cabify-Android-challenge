package com.racobos.presentation.ui.components;

import android.view.View;
import android.widget.ProgressBar;

import com.racobos.presentation.ui.components.views.progressbar.ProgressBarComponent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by rulo7 on 16/10/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class UnitTestProgressBarComponent {
    private ProgressBarComponent progressBarComponent;
    @Mock
    private View rootView;
    @Mock
    private ProgressBar progressBar;

    @Before
    public void setUp() {
        progressBarComponent = new ProgressBarComponent(rootView);
        progressBarComponent.setProgressBar(progressBar);
    }

    @Test
    public void showProgressTest() {
        progressBarComponent.showProgress();
        verify(progressBar).setVisibility(View.VISIBLE);
    }

    @Test
    public void hideProgressTest() {
        progressBarComponent.hideProgress();
        verify(progressBar).setVisibility(View.GONE);
    }

    @Test
    public void invisibleProgressTest() {
        progressBarComponent.invisibleProgress();
        verify(progressBar).setVisibility(View.INVISIBLE);
    }
}
