package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.retrofit.api.UsuariosApi;
import com.example.retrofit.modelo.Usuarios;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


EditText editPalabra;
Button btnBuscar;
TextView txtResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editPalabra = findViewById(R.id.editPalabra);
        btnBuscar = findViewById(R.id.btnBuscar);
        txtResultado = findViewById(R.id.txtResultado);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultado(editPalabra.getText().toString());
            }
        });
    }
public void resultado(String q){
       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl("https://jsonplaceholder.typicode.com/")
               .addConverterFactory(GsonConverterFactory.create())
               .build();
       UsuariosApi usuariosApi = retrofit.create(UsuariosApi.class);
    Call<List<Usuarios>> call = usuariosApi.find(q);
    call.enqueue(new Callback<List<Usuarios>>() {
        @Override
        public void onResponse(Call<List<Usuarios>> call, Response<List<Usuarios>> response) {


            List<Usuarios> usuariosList = response.body();
            for (Usuarios p: usuariosList){
              String resultado= "";
              resultado+="UserId: " + p.getUserId() + "\n";
              resultado+="ID: " + p.getId() + "\n";
              resultado+="Title: " + p.getTitle() + "\n";
              resultado+="Body: " + p.getBody() + "\n\n";
              txtResultado.append(resultado);
            }
    }
        @Override
        public void onFailure(Call<List<Usuarios>> call, Throwable t) {
             txtResultado.setText(t.getMessage());
        }
    });
}

}