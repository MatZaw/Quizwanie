package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuizApi {
    @GET("api/v1/questions")
    Call<List<Question>> getQuestions(@Query("apiKey")  String apiKey, @Query("category") String category);
}
