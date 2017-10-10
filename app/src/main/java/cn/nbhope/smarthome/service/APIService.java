package cn.nbhope.smarthome.service;


import com.nbhope.hopelauncher.lib.network.bean.request.CmdRequest;
import com.nbhope.hopelauncher.lib.network.bean.response.TimeResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by EthanCo on 2015/6/13.
 */
public interface APIService {
    /**
     * 获取服务器信息 (之后调用了该方法后，才可调用login方法登录)
     *
     * @param cmd
     * @return
     */
    @POST("api")
    Observable<TimeResponse> getServerTime(@Body CmdRequest cmd);

    @POST("api")
    Call<TimeResponse> getServerTime2(@Body CmdRequest cmd);

    @POST("experience")
    Observable<String> audition(@Body String body);


}
