package com.racobos.presentation.ui.components;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.racobos.presentation.ui.components.views.toolbar.ToolbarComponent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by rulo7 on 16/10/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class UnitTestToolbarComponent {

    private ToolbarComponent toolbarComponent;

    @Mock
    private AppCompatActivity appCompatActivity;

    @Before
    public void setUp() {
        toolbarComponent = new ToolbarComponent(appCompatActivity);
    }


    @Test
    public void enableHomeAsUp() {
        when(appCompatActivity.getSupportActionBar()).thenReturn(mock(ActionBar.class));
        toolbarComponent.enableHomeAsUp();

        verify(appCompatActivity, times(2)).getSupportActionBar();
        verify(appCompatActivity.getSupportActionBar()).setDisplayShowTitleEnabled(true);
        verify(appCompatActivity.getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    }
}
