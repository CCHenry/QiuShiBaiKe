package com.example.henryzheng.qiushibaike.M.bean.retrofitException;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by henryzheng on 2017/1/24.
 */
public class HttpResultFunc<T> implements Func1<Throwable, Observable<T>> {
    @Override
    public Observable<T> call(Throwable throwable) {
        //ExceptionEngine为处理异常的驱动器
        return Observable.error(ExceptionEngine.handleException(throwable));
    }
}