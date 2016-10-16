package com.racobos.data.errors;

import com.google.gson.Gson;
import com.racobos.domain.errors.ErrorCallback;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.UnknownHostException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by rulo7 on 15/10/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class UnitTestErrorDataManager {
    @InjectMocks
    ErrorDataManager errorDataManager;

    @Test
    public void testHandleHttpError() {
        CabifyErrorEntity cabifyErrorEntity = CabifyErrorEntity.getMockObject();
        Response response = Response.error(404,
                ResponseBody.create(MediaType.parse("application/json"),
                        new Gson().toJson(cabifyErrorEntity)));
        errorDataManager.handleThrowableError(new ErrorCallback() {
            @Override
            public void onHttpError(int status, String message) {
                assertEquals(status, 404);
                assertEquals(message, cabifyErrorEntity.getMessage());
            }

            @Override
            public void onNetworkError(String message) {
                assert (false);
            }

            @Override
            public void unknownError(String message) {
                assert (false);
            }
        }, new HttpException(response));
    }


    @Test
    public void testHandleNetworkError() {
        errorDataManager.handleThrowableError(new ErrorCallback() {
            @Override
            public void onHttpError(int status, String message) {
                assert (false);
            }

            @Override
            public void onNetworkError(String message) {
                assert (true);
            }

            @Override
            public void unknownError(String message) {
                assert (false);
            }
        }, mock(UnknownHostException.class));
    }

    @Test
    public void testHandleUnknownError() {
        errorDataManager.handleThrowableError(new ErrorCallback() {
            @Override
            public void onHttpError(int status, String message) {
                assert (false);
            }

            @Override
            public void onNetworkError(String message) {
                assert (false);
            }

            @Override
            public void unknownError(String message) {
                assert (true);
            }
        }, mock(Exception.class));
    }
}
