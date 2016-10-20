package com.racobos.data.net;

import com.racobos.data.BuildConfig;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by raulcobos on 11/10/16.
 */
@Singleton
public class CabifyApiServices {

    private static final String AUTHORIZATION_KEY_PARAM = "Authorization";
    private static final int CONNECTION_TIMEOUT = 5;
    private static final int READ_TIMEOUT = 10;
    private static final int WRITE_TIMEOUT = 15;

    private CabifyApi byeWalletApi;

    @Inject
    public CabifyApiServices() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(getLoggerInterceptor());
        }
        clientBuilder.addInterceptor(getTokenInterceptor())
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BuildConfig.CABIFY_API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();
        byeWalletApi = retrofit.create(CabifyApi.class);
    }

    public CabifyApi getApi() {
        return byeWalletApi;
    }

    private Interceptor getTokenInterceptor() {
        return chain -> {
            Request originalRequest = chain.request();
            String token = BuildConfig.CABIFY_API_TOKEN;
            Request newRequest = originalRequest.newBuilder().addHeader(AUTHORIZATION_KEY_PARAM, token).build();
            return chain.proceed(newRequest);
        };
    }

    private Interceptor getLoggerInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }
}
