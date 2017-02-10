package io.intrepid.skotlinton.rest;

import io.intrepid.skotlinton.models.IpModel;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RestApi {
    @GET("/?format=json")
    Observable<IpModel> getMyIp();
}
