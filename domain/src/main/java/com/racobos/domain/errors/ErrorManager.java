package com.racobos.domain.errors;

/**
 * Created by rulo7 on 14/10/2016.
 */

public interface ErrorManager {
    void handleThrowableError(ErrorCallback errorCallback, Throwable exception);
}
