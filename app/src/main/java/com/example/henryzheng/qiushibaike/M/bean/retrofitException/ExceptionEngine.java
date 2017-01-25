package com.example.henryzheng.qiushibaike.M.bean.retrofitException;

import com.example.henryzheng.qiushibaike.M.utils.CCLog;
import com.example.henryzheng.qiushibaike.M.utils.ToastUtil;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.text.ParseException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by henryzheng on 2017/1/24.
 */
public class ExceptionEngine {
    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ApiException handleException(Throwable e){
        ApiException ex = null;
        if (e instanceof HttpException) {             //HTTP错误
            final HttpException httpException = (HttpException) e;
            ex = new ApiException(e, httpException.code());
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.setDisplayMessage("网络错误");  //均视为网络错误
                    break;
            }
            CCLog.print("newWork find error" + ex.getCause().toString());
            final ApiException finalEx = ex;
            ToastUtil.showNomalText("网络异常:" + httpException.code());

        }
//        } else if (e instanceof ServerException){    //服务器返回的错误
//            ServerException resultException = (ServerException) e;
//            ex = new ApiException(resultException, resultException.getCode());
//            ex.setDisplayMessage(resultException.getMsg());
//            return ex;
//        }
 else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException){
            ToastUtil.showNomalText("网络异常:" + e.getCause().toString());
            ex.setDisplayMessage("解析错误");            //均视为解析错误
            return ex;

// else if(e instanceof ConnectException){
//            ex = new ApiException(e, ERROR.NETWORD_ERROR);
//            ex.setDisplayMessage("连接失败");  //均视为网络错误
//            return ex;
//        }else {
//            ex = new ApiException(e, ERROR.UNKNOWN);
//            ex.setDisplayMessage("未知错误");          //未知错误
//            return ex;
//        }
    }
        return ex;
    }
}
