package com.ethanco.simpleframesample.model;

import com.ethanco.simpleframesample.bean.OperResult;
import com.ethanco.simpleframesample.bean.User;

import rx.Observable;


/**
 * Created by EthanCo on 2016/4/1.
 */
public interface ILoginModel {
    Observable<OperResult<User>> login(User user);
}
