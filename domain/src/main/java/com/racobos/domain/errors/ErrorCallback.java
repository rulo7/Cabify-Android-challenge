package com.racobos.domain.errors;

/**
 * Created by rulo7 on 14/10/2016.
 */

public interface ErrorCallback {
    void onHttpError(int status, String message);

    void onNetworkError(String message);

    void unknownError(String message);
}
