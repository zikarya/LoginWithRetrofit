package com.example.zikar.myorders.User;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface ApiInterface {
    @GET("/users/login")
    Call<ResponseBody> userLogin(@QueryMap Map<String, String> filters);
}
