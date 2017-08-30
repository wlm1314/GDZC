package com.gdzc.api;

import android.support.annotation.NonNull;

import com.gdzc.api.entity.HttpResult;
import com.gdzc.moudle.base.BaseActivity;
import com.orhanobut.logger.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

import base.lib.util.ActivityManager;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 王少岩 在 2017/3/10 创建了它
 */

public class RetrofitService {

    /**
     * 打印返回的json数据拦截器
     */
    public static final Interceptor sLoggingInterceptor = chain -> {
        final Request request = chain.request();
        Buffer requestBuffer = new Buffer();
        if (request.body() != null) {
            request.body().writeTo(requestBuffer);
        } else {
            Logger.d("LogTAG", "request.body() == null");
        }
        //打印url信息
        Logger.i(request.url() + (request.body() != null ? "?" + parseParams(request.body(), requestBuffer) : ""));
        final Response response = chain.proceed(request);
        //the response data
        ResponseBody body = response.body();

        BufferedSource source = body.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        Charset charset = Charset.defaultCharset();
        MediaType contentType = body.contentType();
        if (contentType != null) {
            charset = contentType.charset(charset);
        }
        String bodyString = buffer.clone().readString(charset);
        Logger.i(bodyString);
        return response;
    };

    @NonNull
    private static String parseParams(RequestBody body, Buffer requestBuffer) throws UnsupportedEncodingException {
        if (body.contentType() != null && !body.contentType().toString().contains("multipart")) {
            return URLDecoder.decode(requestBuffer.readUtf8(), "UTF-8");
        }
        return "null";
    }

    /**
     * 添加公用参数
     */
    public static final Interceptor sParamInterceptor = chain -> {
        Request original = chain.request();
//        //添加通用请求参数
//        String time = System.currentTimeMillis() + "";
//        String timeCheckValue = MD5Utils.md5(time + Constants.sTime);
//        String token = PreferencesUtils.getString(Constants.sUser_token, "");
//        String tokenCheckValue = MD5Utils.md5(token + Constants.sToken);

        HttpUrl.Builder builder = original.url().newBuilder();
//        if (PreferencesUtils.getBoolean(Constants.sUser_loginFlag, false)) {
//            builder.addQueryParameter(Constants.sRequest_params_time, time)
//                    .addQueryParameter(Constants.sRequest_params_timeCheckValue, timeCheckValue)
//                    .addQueryParameter(Constants.sRequest_params_token, token)
//                    .addQueryParameter(Constants.sRequest_params_tokenCheckValue, tokenCheckValue)
//                    .addQueryParameter(Constants.sRequest_params_sourceType, Constants.sSourceType_android)
//                    .addQueryParameter(Constants.sRequest_params_projectId, Constants.sProjectId);
//        } else {
//            builder.addQueryParameter(Constants.sRequest_params_sourceType, Constants.sSourceType_android)
//                    .addQueryParameter(Constants.sRequest_params_projectId, Constants.sProjectId);
//        }
        HttpUrl url = builder.build();
        Request request = original.newBuilder()
                .url(url)
                .build();
        return chain.proceed(request);
    };

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    public static class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            if (!httpResult.isSuccess()) {
                throw new ApiException(httpResult.getCode(), httpResult.getInfo());
            }
            return httpResult.getData();
        }
    }

    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(),true)
                .compose(((BaseActivity) ActivityManager.getActivity()).bindToLife());
    }
}
