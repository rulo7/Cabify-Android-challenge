package com.racobos.presentation.di.modules;

import android.content.Context;

import com.racobos.data.executor.JobExecutor;
import com.racobos.data.repositories.CabifyApiDataRepository;
import com.racobos.domain.executors.PostExecutionThread;
import com.racobos.domain.executors.ThreadExecutor;
import com.racobos.domain.repositories.CabifyApiRepository;
import com.racobos.presentation.threads.UIThread;
import com.racobos.presentation.ui.bases.android.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by raulcobos on 2/9/16.
 */
@Module
public class ApplicationModule {
    private final BaseApplication baseApplication;

    public ApplicationModule(BaseApplication baseApplication) {
        this.baseApplication = baseApplication;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return baseApplication.getApplicationContext();
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }


    @Provides
    @Singleton
    CabifyApiRepository provideCabifRepository(CabifyApiDataRepository cabifyApiDataRepository) {
        return cabifyApiDataRepository;
    }

}