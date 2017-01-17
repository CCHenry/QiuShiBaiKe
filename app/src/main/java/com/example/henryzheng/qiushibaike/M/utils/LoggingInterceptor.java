package com.example.henryzheng.qiushibaike.M.utils;

import java.io.IOException;

public class LoggingInterceptor implements  okhttp3.Interceptor {






    @Override
    public okhttp3.Response intercept(okhttp3.Interceptor.Chain chain) throws IOException {
        okhttp3.Request request = chain.request();

        long t1 = System.nanoTime();
        CCLog.print(String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));

        okhttp3.Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        CCLog.print(String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}