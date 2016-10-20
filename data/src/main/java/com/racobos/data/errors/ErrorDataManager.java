package com.racobos.data.errors;

import com.google.gson.Gson;
import com.racobos.domain.errors.ErrorCallback;
import com.racobos.domain.errors.ErrorManager;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by rulo7 on 14/10/2016.
 */
@Singleton
public class ErrorDataManager implements ErrorManager {

    @Inject
    public ErrorDataManager() {
    }

    @Override
    public void handleThrowableError(ErrorCallback errorCallback, Throwable exception) {
        if (exception instanceof HttpException) {
            HttpException httpException = (HttpException) exception;
            String message;
            try {
                CabifyErrorEntity cabifyErrorEntity = new Gson().fromJson(httpException.response().errorBody().string(), CabifyErrorEntity.class);
                message = cabifyErrorEntity.getMessage();
            } catch (IOException e) {
                message = "unknown";
            }
            errorCallback.onHttpError(httpException.code(), message);
        } else if (exception instanceof UnknownHostException) {
            UnknownHostException unknownHostException = (UnknownHostException) exception;
            errorCallback.onNetworkError(unknownHostException.getMessage());
        } else {
            errorCallback.unknownError(exception.getMessage());
        }
    }
}
