package com.example.retrofit.api;

import com.example.retrofit.modelo.Usuarios;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UsuariosApi {

@GET("posts")
    Call<List<Usuarios>> find(@Query("q") String q);



}
