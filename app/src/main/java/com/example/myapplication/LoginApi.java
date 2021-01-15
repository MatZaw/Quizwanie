package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LoginApi {

    @POST("login/{login}/{password}")
    Call<Void> login(@Path("login") String login, @Path("password") String password);
    @GET("logout/{login}")
    Call<Void> logout(@Path("login") String login);
    @POST("createUser/{login}/{password}")
    Call<Void> createUser(@Path("login") String login, @Path("password") String password);
    @POST("postResult/{username}/{title}/{score}/{max}")
    Call<Void> postResult(@Path("username") String username, @Path("title") String title, @Path("score") String score, @Path("max") String max);
    @GET("results")
    Call<List<ResultDto>> getResults();

}
